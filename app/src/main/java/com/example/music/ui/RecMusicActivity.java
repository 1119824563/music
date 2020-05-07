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

import com.alibaba.fastjson.JSONObject;
import com.example.music.R;
import com.example.music.adapter.recMusicAdapter;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.NETnewsong;
import com.example.music.model.netmusic;
import com.example.music.utils.DBUtil;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.SystemClock.sleep;

public class RecMusicActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    private recMusicAdapter mrecMusicAdapter;
    private Handler handler;
    private Handler handler2;
    private boolean repeat=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recmusic);
        setTitle("推荐最新音乐");

        //列表初始化显示
        mRecyclerView=(RecyclerView) findViewById(R.id.netmusiclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mrecMusicAdapter = new recMusicAdapter(this, NetmusicManager.getInstance().recmusicList);
        mRecyclerView.setAdapter(mrecMusicAdapter);

        WorkThread wt=new WorkThread();
        wt.start();
        WorkThread2 wt2=new WorkThread2();
        wt2.start();
    }

    //建立右上角的菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recmusic_menu, menu);
        return true;
    }

    //右上角菜单的点击
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.getmusic://获取推荐
                NetmusicManager.getInstance().recmusicList.clear();//清空上一次的搜索列表
                Message m=handler.obtainMessage();//获取事件
                handler.sendMessage(m);//把信息放到通道中
                sleep(2000);
                mrecMusicAdapter.notifyDataSetChanged();//更新列表
                Toast.makeText(this, "音乐已推荐", Toast.LENGTH_SHORT).show();
                break;
            case R.id.clearall:
                NetmusicManager.getInstance().recmusicList.clear();
                mrecMusicAdapter.notifyDataSetChanged();//更新列表
                Toast.makeText(this, "推荐列表已清空", Toast.LENGTH_SHORT).show();
                break;
            case R.id.downloadrec://存放到数据库
                for(int i = 0; i< NetmusicManager.getInstance().recmusicList.size(); i++){
                    int mid=NetmusicManager.getInstance().recmusicList.get(i).getMusicid();
                    String mname = NetmusicManager.getInstance().recmusicList.get(i).getSongname();//歌名
                    String msingername = NetmusicManager.getInstance().recmusicList.get(i).getSingername();
                    Message mm=handler2.obtainMessage();//获取事件
                    Bundle b=new Bundle();
                    b.putInt("mid",mid);
                    b.putString("mname",mname);
                    b.putString("msingername",msingername);
                    mm.setData(b);
                    handler2.sendMessage(mm);//把信息放到通道中
                }
                if(repeat){
                    Toast.makeText(this, "存放成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "数据已存放", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnbackrec:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //获取推荐音乐json
    private String getDataBySong() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/personalized/newsong")
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }

    //解析json获取推荐音乐
    class WorkThread extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler=new Handler(){
                @Override
                public  void handleMessage(Message m){
                    super.handleMessage(m);
                    //Toast.makeText(netmusicActivity.this, keyword, Toast.LENGTH_SHORT).show();
                    String json;
                    NETnewsong jsonObject;
                    try {
                        json=getDataBySong();
                        //Toast.makeText(RecMusicActivity.this, json, Toast.LENGTH_SHORT).show();
                        jsonObject = JSONObject.parseObject(json,NETnewsong.class);
                        for(int i=0;i<10;i++){
                            netmusic mnetmusic=new netmusic();
                            mnetmusic.setSongname(jsonObject.getResult().get(i).getSong().getName());//歌名
                            mnetmusic.setMusicid(jsonObject.getResult().get(i).getSong().getId());//歌曲id
                            mnetmusic.setSingername(jsonObject.getResult().get(i).getSong().getArtists().get(0).getName());//歌手
                            mnetmusic.setAlbum(jsonObject.getResult().get(i).getSong().getAlbum().getName());//专辑
                            mnetmusic.setImg1v1Url(jsonObject.getResult().get(i).getSong().getArtists().get(0).getImg1v1Url());//封面
                            mnetmusic.setSingid(jsonObject.getResult().get(i).getSong().getArtists().get(0).getId());
                            NetmusicManager.getInstance().recmusicList.add(mnetmusic);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(RecMusicActivity.this, "异常，返回重进就好", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    //存放推荐列表
    class WorkThread2 extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler2=new Handler(){
                @Override
                public  void handleMessage(Message mm){
                    super.handleMessage(mm);
                    Bundle b = mm.getData();//得到与信息对用的Bundle
                    int mid = b.getInt("mid");//根据键取值
                    String mname = b.getString("mname");
                    String msingername=b.getString("msingername");
                    DBUtil db= new DBUtil();
                    String ret = db.storerec(mid,mname,msingername);//得到返回值
                    repeat=ret.equals("1");
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

}
