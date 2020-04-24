package com.example.music;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.adapter.MainListAdapter;
import com.example.music.impl.OnMusicService;
import com.example.music.manager.MusicManager;
import com.example.music.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //数据源
    private List<Music> mMusicList= new ArrayList<>();
    private RecyclerView mMainListView;
    private MainListAdapter mMainListAdapter;

    private ProgressDialog mProgressDialog;

    private AlertDialog alertDialog2;
    private int themeType=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeType = getSharedPreferences("theme", MODE_PRIVATE).getInt("themeType", 0);
        if(themeType == 0){
            setTheme(R.style.AppTheme);
        }
        else if(themeType == 1){
            setTheme(R.style.AppTheme1);
        }else if(themeType == 2){
            setTheme(R.style.AppTheme2);
        }else if(themeType == 3){
            setTheme(R.style.AppTheme3);
        }
        setContentView(R.layout.activity_main);
        initView();
    }
    public void initView(){
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.text_main_re_scan_muisc));
        //屏幕外点击无效
        mProgressDialog.setCancelable(false);

        mMainListView = (RecyclerView) findViewById(R.id.mMainListView);
        /**
         * ListView:列表 GridView:宫格
         * RecyclerView: LayoutManager;
         */
        mMainListView.setLayoutManager(new LinearLayoutManager(this));
        /**
         * 适配器
         * ListView的优化手段 ： ViewHolder 来复用View
         */
        mMainListAdapter = new MainListAdapter(this,mMusicList);
        mMainListView.setAdapter(mMainListAdapter);

        checkPremission();
    }

    private void checkPremission(){
        //申请权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int isCheck = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int IsCheck = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            //允许：0   没有允许：-1
            //Toast.makeText(this, "isCheck:" + isCheck, Toast.LENGTH_SHORT).show();
            //你没有同意这个权限
            if (isCheck != PackageManager.PERMISSION_GRANTED||IsCheck!=PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE}, 10001);
            } else {
                bindService();
            }
        } else {
            bindService();
        }
    }
    private void bindService() {

        //Service的启动方式
        MusicManager.getInstance().bindMusicService(this, new OnMusicService() {
            @Override
            public void bindSucceed() {
                //获取当前的歌曲列表
                mMusicList.addAll(MusicManager.getInstance().getClientImpl().getMusicList());
                if (mMusicList.size() <= 0) {
                    //说明你没有数据
                    MusicManager.getInstance().getClientImpl().startScanMusic();
                } else {
                    //刷新数据源
                    mMainListAdapter.notifyDataSetChanged();
                    }
            }

            @Override
            public void scanMusicStart() {
                mProgressDialog.show();
            }

            @Override
            public void scanMusicStop() {
                mProgressDialog.dismiss();
                mMusicList.addAll(MusicManager.getInstance().getClientImpl().getMusicList());
                //搜索了一遍还没有数据
                if (mMusicList.size() <= 0) {
                    //说明没有数据
                    Toast.makeText(MainActivity.this, getString(R.string.text_main_null_muisc), Toast.LENGTH_SHORT).show();
                } else {
                    //刷新数据源
                    mMainListAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MusicManager.getInstance().unBindService();
    }

    //建立右上角的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //右上角菜单的点击
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_scan:
                if (!MusicManager.getInstance().getClientImpl().isScan()) {
                    if (mMusicList.size() > 0) {
                        mMusicList.clear();
                    }
                    MusicManager.getInstance().getClientImpl().startScanMusic();
                }
                break;
            case R.id.menu_skin:
                changeskin();
                break;
            case R.id.network:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //改变颜色
    public void changeskin(){
        final String[] items = {"红色", "蓝色", "绿色", "粉色"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("选择颜色");
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                themeType=i;
                getSharedPreferences("theme", MODE_PRIVATE).edit().putInt("themeType", themeType).commit();
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                recreate();
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });

        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 10001) {
            //是我们本次的权限申请回调
            boolean isresult = true;
            for (int i = 0;i<grantResults.length;i++) {
                if(grantResults[i]==PackageManager.PERMISSION_DENIED){
                    isresult=false;
                }
            }
            if(isresult){
                bindService();
            }else{
                checkPremission();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
