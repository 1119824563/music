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
import com.example.music.model.NETlrc;
import com.example.music.model.NETsingermusic;
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
    public List<netmusic> mnetmusicList = new ArrayList<>();//搜索列表
    public List<netmusic> lovemusicList = new ArrayList<>();//我的最爱/收藏
    public List<netmusic> recmusicList = new ArrayList<>();//推荐音乐
    public List<netmusic> singermusicList =new ArrayList<>();//歌手热门音乐

    public String singername;
    public String singermore;
    public String getSingername() {
        return singername;
    }
    public void setSingername(String singername) {
        this.singername = singername;
    }
    public String getSingermore() {
        return singermore;
    }
    public void setSingermore(String singermore) {
        this.singermore = singermore;
    }

    private Handler handler;
    private Handler handler2;
    private Handler handler3;
    public boolean iftrue=false;//是否有版权进行播放
    public String musicurl;//音乐url
    public String musiclrc;//歌词
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

    public static int MEDIA_PLAY_MODE=MEDIA_PLAY_MODE_SINGLE;//默认是顺序

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
        WorkThread2 wt2=new WorkThread2();
        wt2.start();
        WorkThread3 wt3=new WorkThread3();
        wt3.start();
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
        mnetmusicList.get(position).setMusiclrc(musiclrc);
    }
    //获取音乐url
    public void getrecList(int position){
        int musicid=NetmusicManager.getInstance().recmusicList.get(position).getMusicid();//获取对应的音乐id
        //判断是否有版权,有就获取url
        Message m=handler.obtainMessage();//获取事件
        Bundle b=new Bundle();
        b.putInt("key",musicid);//以键值对形式放进 Bundle中
        m.setData(b);
        handler.sendMessage(m);//把信息放到通道中
        recmusicList.get(position).setSongurl(musicurl);
        recmusicList.get(position).setMusiclrc(musiclrc);
    }
    //获取歌手热门歌曲
    public void getsinger(int positon){
        String singerid=NetmusicManager.getInstance().mnetmusicList.get(positon).getSingerid();//获取对应的歌手id
        Message m=handler2.obtainMessage();//获取事件
        Bundle b=new Bundle();
        b.putString("key",singerid);//以键值对形式放进 Bundle中
        m.setData(b);
        handler2.sendMessage(m);//把信息放到通道中
        NetmusicManager.getInstance().setSingermore(singermore);
    }
    //获取歌手热门歌曲
    public void getsingerrec(int positon){
        int singerid=NetmusicManager.getInstance().recmusicList.get(positon).getSingid();//获取对应的歌手id
        Message m=handler3.obtainMessage();//获取事件
        Bundle b=new Bundle();
        b.putInt("key",singerid);//以键值对形式放进 Bundle中
        m.setData(b);
        handler3.sendMessage(m);//把信息放到通道中
        NetmusicManager.getInstance().setSingermore(singermore);
    }

    //-----------------------------------------
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
    //获取歌词url的json
    private String getlrcByid(int keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/lyric?id="+keyword)
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }
    //获取歌手热门歌曲json
    private String getsingermusicByid(String keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/artists?id="+keyword)
                .build();
        Response response = client.newCall(request).execute();
        String message=response.body().string();
        return message;
    }
    //获取歌手热门歌曲json
    private String getsingmusicByid(int keyword) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://13.94.60.177:3000/artists?id="+keyword)
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
                    String jsonlrc;
                    NETurl jsonObjecturl;
                    NETlrc jsonObjectlrc;
                    try {
                        json=getDataByid(keyword);
                        JSONObject jsonObject=JSONObject.parseObject(json);
                        iftrue=(boolean)jsonObject.get("success");
                        if(iftrue){
                            jsonurl=getUrlByid(keyword);
                            jsonObjecturl = JSONObject.parseObject(jsonurl, NETurl.class);
                            musicurl=jsonObjecturl.getData().get(0).getUrl();
                            jsonlrc=getlrcByid(keyword);
                            jsonObjectlrc =JSONObject.parseObject(jsonlrc, NETlrc.class);
                            musiclrc=jsonObjectlrc.getLrc().getLyric();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    //获取歌手信息及热门歌曲
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
                    String keyword = b.getString("key");//根据键取值
                    String jsonmusic;
                    NETsingermusic jsonObjectmusic;
                    try {
                        jsonmusic=getsingermusicByid(keyword);
                        jsonObjectmusic=JSONObject.parseObject(jsonmusic,NETsingermusic.class);
                        singermore=jsonObjectmusic.getArtist().getBriefDesc();
                        //Toast.makeText(mContext, singermore, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<20;i++){
                            netmusic mnetmusic=new netmusic();
                            mnetmusic.setSongname(jsonObjectmusic.getHotSongs().get(i).getName());//歌名
                            mnetmusic.setMusicid(jsonObjectmusic.getHotSongs().get(i).getId());//歌曲id
                            mnetmusic.setSingername(jsonObjectmusic.getArtist().getName());//歌手
                            NetmusicManager.getInstance().singermusicList.add(mnetmusic);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
    //获取歌手信息及热门歌曲
    class WorkThread3 extends  Thread{
        @Override
        public  void run(){
            super.run();
            Looper.prepare();
            handler3=new Handler(){
                @Override
                public  void handleMessage(Message m){
                    super.handleMessage(m);
                    Bundle b = m.getData();//得到与信息对用的Bundle
                    int keyword = b.getInt("key");//根据键取值
                    String jsonmusic;
                    NETsingermusic jsonObjectmusic;
                    try {
                        jsonmusic=getsingmusicByid(keyword);
                        jsonObjectmusic=JSONObject.parseObject(jsonmusic,NETsingermusic.class);
                        singermore=jsonObjectmusic.getArtist().getBriefDesc();
                        //Toast.makeText(mContext, singermore, Toast.LENGTH_SHORT).show();
                        for(int i=0;i<20;i++){
                            netmusic mnetmusic=new netmusic();
                            mnetmusic.setSongname(jsonObjectmusic.getHotSongs().get(i).getName());//歌名
                            mnetmusic.setMusicid(jsonObjectmusic.getHotSongs().get(i).getId());//歌曲id
                            mnetmusic.setSingername(jsonObjectmusic.getArtist().getName());//歌手
                            NetmusicManager.getInstance().singermusicList.add(mnetmusic);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    //------------------------------------------------------
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
