package com.example.music.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.music.R;
import com.example.music.adapter.NetMusicAdapter;
import com.example.music.impl.OnNetMusicService;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.NET;
import com.example.music.model.netmusic;
import com.example.music.utils.DBUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class netmusicActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private NetMusicAdapter mNetMusicAdapter;
    private TextView searchtext;
    private Button search;
    private Handler handler;
    private Handler handler2;
    private PopupWindow popupWindow = null;
    private AlertDialog alertDialog2;
    private int themeType=0;
    private boolean repeat=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_network);
        setTitle("网络歌曲搜索");

        searchtext=findViewById(R.id.serachtext);
        search=findViewById(R.id.serach);
        search.setOnClickListener(getsearch());

        NetmusicManager.getInstance().bindNetMusicService(this, new OnNetMusicService() {
            @Override
            public void bindSucceed() {
                //Toast.makeText(netmusicActivity.this, "123", Toast.LENGTH_SHORT).show();
                //NetmusicManager.getInstance().getClientImpl().bindSucceed();
            }
        });
        //列表初始化显示
        mRecyclerView=(RecyclerView) findViewById(R.id.netmusiclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNetMusicAdapter = new NetMusicAdapter(this,NetmusicManager.getInstance().mnetmusicList);
        mRecyclerView.setAdapter(mNetMusicAdapter);

        WorkThread wt=new WorkThread();
        wt.start();
        WorkThread2 wt2=new WorkThread2();
        wt2.start();
    }

    //建立右上角的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.net_menu, menu);
        return true;
    }

    //右上角菜单的点击
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lovemusic:
                startActivity(new Intent(netmusicActivity.this, LoveMusicActivity.class));
                break;
            case R.id.Recommend:
                startActivity(new Intent(netmusicActivity.this, RecMusicActivity.class));
                break;
            case R.id.more:
                showPopupWindow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //更多弹窗
    private void showPopupWindow() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupWindowView = inflater.inflate(R.layout.netpup, null, false);
        popupWindow = new
                PopupWindow(popupWindowView, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //引入依附的布局
        View parentView = LayoutInflater.from(netmusicActivity.this).inflate(R.layout.activity_main, null);
        //相对于父控件的位置
        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 1550);
        //获取焦点，否则无法点击
        popupWindow.setFocusable(true);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));

        //改变皮肤
        Button skin = popupWindowView.findViewById(R.id.skin);
        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeskin();
            }
        });

        //存放到数据库
        Button load=popupWindowView.findViewById(R.id.loadnet);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i< NetmusicManager.getInstance().mnetmusicList.size(); i++){
                    int mid=NetmusicManager.getInstance().mnetmusicList.get(i).getMusicid();//id
                    String mname = NetmusicManager.getInstance().mnetmusicList.get(i).getSongname();//歌名
                    String msingername = NetmusicManager.getInstance().mnetmusicList.get(i).getSingername();//歌手
                    String mImg1v1Url = NetmusicManager.getInstance().mnetmusicList.get(i).getImg1v1Url();//封面url
                    Message m=handler2.obtainMessage();//获取事件
                    Bundle b=new Bundle();
                    b.putInt("mid",mid);
                    b.putString("mname",mname);
                    b.putString("msingername",msingername);
                    b.putString("mlrcpath",mImg1v1Url);
                    m.setData(b);
                    handler2.sendMessage(m);//把信息放到通道中
                }
                if(repeat){
                    Toast.makeText(netmusicActivity.this, "存放完成", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(netmusicActivity.this, "数据已存放", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //返回
        Button signout=popupWindowView.findViewById(R.id.signout);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    //搜索
    private View.OnClickListener getsearch(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NetmusicManager.getInstance().mnetmusicList.clear();//清空上一次的搜索列表
                String keyword = searchtext.getText().toString();
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putString("key",keyword);//以键值对形式放进 Bundle中
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中
                mNetMusicAdapter.notifyDataSetChanged();//更新列表
            }
        };
    }

    //获取json
    private String getDataBySong(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/search?keywords="+keyword)
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }

    //解析json获取音乐
    class WorkThread extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler=new Handler(){
                @Override
                public  void handleMessage(Message m){
                    super.handleMessage(m);
                    Bundle b = m.getData();//得到与信息对用的Bundle
                    String keyword = b.getString("key");//根据键取值
                    //Toast.makeText(netmusicActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    String json;
                    NET jsonObject;
                    try {
                        json=getDataBySong(keyword);
                        //Toast.makeText(netmusicActivity.this, json, Toast.LENGTH_SHORT).show();
                        jsonObject = JSONObject.parseObject(json,NET.class);
                        for(int i=0;i<30;i++){
                            netmusic mnetmusic=new netmusic();
                            mnetmusic.setSongname(jsonObject.getResult().getSongs().get(i).getName());//歌名
                            mnetmusic.setMusicid(jsonObject.getResult().getSongs().get(i).getId());//歌曲id
                            mnetmusic.setSingername(jsonObject.getResult().getSongs().get(i).getArtists().get(0).getName());//歌手
                            mnetmusic.setImg1v1Url(jsonObject.getResult().getSongs().get(i).getArtists().get(0).getImg1v1Url());//封面
                            mnetmusic.setAlbum(jsonObject.getResult().getSongs().get(i).getAlbum().getName());//专辑
                            mnetmusic.setSingerid(jsonObject.getResult().getSongs().get(i).getArtists().get(0).getId());//歌手id
                            NetmusicManager.getInstance().mnetmusicList.add(mnetmusic);
                       }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(netmusicActivity.this, "获取失败，请再次搜索", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    class WorkThread2 extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler2=new Handler(){
                @Override
                public  void handleMessage(Message m){
                    super.handleMessage(m);
                    Bundle b = m.getData();//得到与信息对用的Bundle
                    int mid = b.getInt("mid");//根据键取值
                    String mname = b.getString("mname");
                    String msingername = b.getString("msingername");
                    String mlrcpath = b.getString("mlrcpath");
                    DBUtil db= new DBUtil();
                    String ret = db.storenet(mid,mname,msingername,mlrcpath);//得到返回值
                    repeat=ret.equals("1");
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetmusicManager.getInstance().unbindNetMusicService();
    }
}
