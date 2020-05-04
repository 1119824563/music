package com.example.music.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.music.impl.OnNetMusicClient;
import com.example.music.impl.OnNetMusicService;
import com.example.music.model.NETurl;
import com.example.music.model.netmusic;
import com.example.music.service.NetMusicService;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnCompletionListener;
import com.pili.pldroid.player.PLOnPreparedListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetmusicManager {

    private Context mContext;
    private static NetmusicManager mInstance = null;
    //是否绑定服务
    private boolean isBindService = false;

    private PLMediaPlayer mMediaPlayer;
    public List<netmusic> mnetmusicList = new ArrayList<>();;
    private Handler handler;
    public boolean iftrue=false;//是否有版权进行播放
    public String musicurl;//音乐url
    private OnMusicProgressListener listener;

    //Binder对象
    private NetMusicService.NetMusicBinder mMusicBinder;
    //服务端接口
    private OnNetMusicService serviceImpl;
    //客户端接口
    private OnNetMusicClient clientImpl;

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

    private static final int H_PROGRESS = 1001;

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            try {
                isBindService=true;
                mMusicBinder=(NetMusicService.NetMusicBinder) iBinder;
                clientImpl = mMusicBinder.bindClientImple();
                mMusicBinder.bindServiceImpl(serviceImpl);
                serviceImpl.bindSucceed();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBindService=false;
            clientImpl=null;
            serviceImpl=null;
        }
    };


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

    //绑定服务
    public void bindNetMusicService(Context mContext,OnNetMusicService serviceImpl){
        this.mContext=mContext;
        this.serviceImpl = serviceImpl;
        mMediaPlayer=new PLMediaPlayer(mContext);
        Intent intent = new Intent(mContext, NetMusicService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    //解绑
    public void unbindNetMusicService(){
        if (null == mContext) {
            throw new UnsupportedOperationException("No Run bindMusicService");
        }
        mContext.unbindService(mConnection);
    }

    //是否绑定服务
    public boolean isBindService() {
        return isBindService;
    }

    //获取客户端对象
    public OnNetMusicClient getClientImpl() {
        return clientImpl;
    }

    //获取音乐url
    public void getList(int position){
        int musicid=NetmusicManager.getInstance().mnetmusicList.get(position).getMusicid();//获取对应的音乐id
        //判断是否有版权,有就获取url
        Message m=handler.obtainMessage();//获取事件
        Bundle b=new Bundle();
        b.putInt("key",musicid);//以键值对形式放进 Bundle中
        m.setData(b);
        handler.sendMessage(m);//把信息放到通道中
        mnetmusicList.get(position).setSongurl(musicurl);
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
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case H_PROGRESS:
                    if (listener != null) {
                        //获取当前时长
                        listener.onProgress(getCurrentPosition(),getDuration());
                        //每隔1s发送出去
                        mHandler.sendEmptyMessageDelayed(H_PROGRESS, 1000);
                    }
                    break;
            }
            return false;
        }
    });


    //开始播放
    public void startPlay(String path) {
        try {
            if (!TextUtils.isEmpty(path)) {
                mMediaPlayer.setDataSource(path);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.setOnPreparedListener(new PLOnPreparedListener() {
                    @Override
                    public void onPrepared(int i) {
                        mMediaPlayer.start();
                    }
                });
                MEDIA_STATUS = MEDIA_STATUS_PLAY;
                mHandler.sendEmptyMessage(H_PROGRESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //判断是否在播放
    public boolean isPlay(){
        if (mMediaPlayer==null){
            return false;
        }
        return mMediaPlayer.isPlaying();
    }

    //暂停
    public void pausePlay(){
        if(isPlay()){
            mMediaPlayer.pause();
            MEDIA_STATUS = MEDIA_STATUS_PAUSE;
        }
    }

    //继续
    public void continuePlay(){
        if(!isPlay()){
            mMediaPlayer.start();
            MEDIA_STATUS = MEDIA_STATUS_PLAY;
        }
    }
    //停止播放
    public void stopPlay(){
        mMediaPlayer.stop();
        mMediaPlayer.release();
        MEDIA_STATUS = MEDIA_STATUS_STOP;
        mHandler.removeMessages(H_PROGRESS);
    }

    //获取当前的位置
    public long getCurrentPosition(){
        return mMediaPlayer.getCurrentPosition();
    }


    //跳转到某一个位置播放
    public void setCurrentPosition(int msc){
        mMediaPlayer.seekTo(msc);
    }

    // 获取总时长
    public long getDuration(){
        return mMediaPlayer.getDuration();
    }


    //监听进度
    public void setOnMusicProgress(OnMusicProgressListener listener) {
        this.listener = listener;
    }

    // 监听播放结束
    public void setOnCompletionListener(PLOnCompletionListener listener) {
        mMediaPlayer.setOnCompletionListener(listener);
    }


    public interface OnMusicProgressListener {
        void onProgress(long progress,long allProgress);
    }
}
