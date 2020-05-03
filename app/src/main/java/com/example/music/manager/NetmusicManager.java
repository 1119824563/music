package com.example.music.manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.alibaba.fastjson.JSONObject;
import com.example.music.model.NETurl;
import com.example.music.model.netmusic;
import com.pili.pldroid.player.PLMediaPlayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetmusicManager {

    private static NetmusicManager mInstance = null;
    private PLMediaPlayer mMediaPlayer;
    public List<netmusic> mnetmusicList = new ArrayList<>();;
    private Handler handler;
    public boolean iftrue=false;
    public String musicurl;

    //---------------------------------------

    //播放状态
    public final int MEDIA_STATUS_PLAY = 0; //播放
    public final int MEDIA_STATUS_PAUSE = 1;//暂停
    public final int MEDIA_STATUS_STOP = 2; //停止

    public int MEDIA_STATUS = MEDIA_STATUS_STOP;

    public final static int MEDIA_PLAY_MODE_ORDER = 3;//顺序
    public final static int MEDIA_PLAY_MODE_RANDOM = 4;//随机
    public final static int MEDIA_PLAY_MODE_SINGLE = 5;//单曲

    public static int MEDIA_PLAY_MODE=MEDIA_PLAY_MODE_ORDER;//默认是顺序


    private NetmusicManager(){
        WorkThread wt=new WorkThread();
        wt.start();
    }

    //双重枷锁单例
    public static NetmusicManager getInstance() {
        if (mInstance == null) {
            synchronized (NetmusicManager.class) {
                if (mInstance == null) {
                    mInstance = new NetmusicManager();
                }
            }
        }
        return mInstance;
    }


    public void getList(int position,int musicid){
        //判断是否有版权,有就获取url
        Message m=handler.obtainMessage();//获取事件
        Bundle b=new Bundle();
        b.putInt("key",musicid);//以键值对形式放进 Bundle中
        m.setData(b);
        handler.sendMessage(m);//把信息放到通道中
    }

    //获取版权验证的json
    private String getDataByid(int keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/check/music?id="+keyword)
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }

    //获取音乐url的json
    private String getUrlByid(int keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/song/url?id="+keyword)
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }

    //验证该音乐是否有版权，有就获取url
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
                    int keyword = b.getInt("key");//根据键取值
                    String json;
                    String jsonurl;
                    NETurl jsonObjecturl;
                    try {
                        json=getDataByid(keyword);
                        JSONObject jsonObject=JSONObject.parseObject(json);
                        iftrue=(boolean)jsonObject.get("success");
                        if(iftrue){
                            jsonurl=getUrlByid(keyword);
                            jsonObjecturl = JSONObject.parseObject(jsonurl, NETurl.class);
                            musicurl=jsonObjecturl.getData().get(0).getUrl();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
}
