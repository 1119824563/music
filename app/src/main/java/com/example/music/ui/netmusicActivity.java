package com.example.music.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSONObject;
import com.example.music.R;
import com.example.music.adapter.NetMusicAdapter;
import com.example.music.model.NET;
import com.example.music.model.netmusic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class netmusicActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    private List<netmusic> mMusicList= new ArrayList<>();
    private NetMusicAdapter mNetMusicAdapter;
    private TextView searchtext;
    private Button search;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        searchtext=findViewById(R.id.serachtext);
        search=findViewById(R.id.serach);
        search.setOnClickListener(getsearch());

        //a();
        mRecyclerView=(RecyclerView) findViewById(R.id.netmusiclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mNetMusicAdapter = new NetMusicAdapter(this,mMusicList);
        mRecyclerView.setAdapter(mNetMusicAdapter);

        WorkThread wt=new WorkThread();
        wt.start();
    }

    //搜索
    private View.OnClickListener getsearch(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String keyword = searchtext.getText().toString();
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putString("key",keyword);//以键值对形式放进 Bundle中
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中

            }
        };
    }
    /*//测试用
    public void a(){
        netmusic mnetmusic=new netmusic();
        mnetmusic.setSingername("周杰伦");
        mnetmusic.setSongname("青花瓷");
        mMusicList.add(mnetmusic);
        mnetmusic.setSingername("周杰伦");
        mnetmusic.setSongname("菊花台");
        mMusicList.add(mnetmusic);
    }*/

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
    //需要网络的必须放在子线程中
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
                    String a;
                    NET jsonObject;
                    try {
                        json=getDataBySong(keyword);
                        //Toast.makeText(netmusicActivity.this, json, Toast.LENGTH_SHORT).show();
                        jsonObject = JSONObject.parseObject(json,NET.class);
                        a=jsonObject.getResult().getSongs().get(1).getName();
                        Toast.makeText(netmusicActivity.this, "歌名="+a,Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(netmusicActivity.this, "11111", Toast.LENGTH_SHORT).show();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
}
