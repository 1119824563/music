package com.example.music.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.adapter.loveMusicAdapter;
import com.example.music.manager.NetmusicManager;
import com.example.music.utils.DBUtil;

import static android.os.SystemClock.sleep;

public class LoveMusicActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private loveMusicAdapter mNetMusicAdapter;
    private Handler handler;
    private Handler handler2;
    private boolean repeat=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lovemusic);
        setTitle("收藏列表");
        //列表初始化显示
        mRecyclerView=(RecyclerView) findViewById(R.id.netmusiclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNetMusicAdapter = new loveMusicAdapter(this, NetmusicManager.getInstance().lovemusicList);
        mRecyclerView.setAdapter(mNetMusicAdapter);

        WorkThread wt=new WorkThread();
        wt.start();
        WorkThread2 wt2=new WorkThread2();
        wt2.start();
    }

    //建立右上角的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.music_menu, menu);
        return true;
    }

    //右上角菜单的点击
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getsql:
                Message mm=handler2.obtainMessage();//获取事件
                handler2.sendMessage(mm);//把信息放到通道中
                mNetMusicAdapter.notifyDataSetChanged();//更新列表
                Toast.makeText(this, "从数据库获取数据较慢，可以返回后重进查看", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearall:
                NetmusicManager.getInstance().lovemusicList.clear();
                mNetMusicAdapter.notifyDataSetChanged();//更新列表
                Toast.makeText(this, "收藏列表已清空", Toast.LENGTH_SHORT).show();
                break;
            case R.id.download:
                for(int i = 0; i< NetmusicManager.getInstance().lovemusicList.size(); i++){
                    int mid=NetmusicManager.getInstance().lovemusicList.get(i).getMusicid();
                    String mname = NetmusicManager.getInstance().lovemusicList.get(i).getSongname();//歌名
                    String msingername = NetmusicManager.getInstance().lovemusicList.get(i).getSingername();
                    Message m=handler.obtainMessage();//获取事件
                    Bundle b=new Bundle();
                    b.putInt("mid",mid);
                    b.putString("mname",mname);
                    b.putString("msingername",msingername);
                    m.setData(b);
                    handler.sendMessage(m);//把信息放到通道中
                }
                if(repeat){
                    Toast.makeText(this, "存放成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "数据已存放", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnback:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //存放收藏列表
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
                    int mid = b.getInt("mid");//根据键取值
                    String mname = b.getString("mname");
                    String msingername=b.getString("msingername");
                    DBUtil db= new DBUtil();
                    String ret = db.storelove(mid,mname,msingername);//得到返回值
                    repeat=ret.equals("1");
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
    //存放收藏列表
    class WorkThread2 extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler2=new Handler(){
                @Override
                public  void handleMessage(Message mm){
                    super.handleMessage(mm);
                    DBUtil db= new DBUtil();
                    String ret = db.getlove();
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }


}
