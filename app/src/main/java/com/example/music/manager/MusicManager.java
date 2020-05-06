package com.example.music.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;

import com.example.music.impl.OnMusicClient;
import com.example.music.impl.OnMusicService;
import com.example.music.service.MusicService;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.PLOnCompletionListener;


public class MusicManager {


    private Context mContext;
    private static MusicManager mInstance = null;
    //是否绑定服务
    private boolean isBindService = false;
    //Binder对象
    private MusicService.MusicBinder mMusicBinder;
    //客户端对象
    private OnMusicClient clientImpl;
    //服务端对象
    private OnMusicService serviceImpl;

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

    private PLMediaPlayer mMediaPlayer;
    //private SimpleExoPlayer mSimpleExoPlayer;

    private OnMusicProgressListener listener;

    private static final int H_PROGRESS = 1001;

    private ServiceConnection mConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder iBinder) {
            try {
                isBindService=true;
                mMusicBinder = (MusicService.MusicBinder) iBinder;
                clientImpl = mMusicBinder.bindClientImple();
                mMusicBinder.bindServiceImpl(serviceImpl);
                serviceImpl.bindSucceed();
            }catch (Exception e){
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

    private MusicManager(){
    }
    //双重枷锁单例
    public static MusicManager getInstance() {
        if (mInstance == null) {
            synchronized (MusicManager.class) {
                if (mInstance == null) {
                    mInstance = new MusicManager();
                }
            }
        }
        return mInstance;
    }

    //绑定服务
    public  void bindMusicService(Context mContext,OnMusicService serviceImpl){
        this.mContext = mContext;
        this.serviceImpl = serviceImpl;
        mMediaPlayer=new PLMediaPlayer(mContext);
        Intent intent = new Intent(mContext, MusicService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        //mSimpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mContext);
    }

    //解绑
    public void unBindService() {
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
    public OnMusicClient getClientImpl() {
        return clientImpl;
    }

    //-----------------------------------------

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
                //mMediaPlayer.reset();
                mMediaPlayer.setDataSource(path);
                mMediaPlayer.prepareAsync();
                mMediaPlayer.start();

                /*DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(mContext,
                        Util.getUserAgent(mContext, mContext.getPackageName()));
                MediaSource videoSource =
                        new ProgressiveMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(Uri.fromFile(new File(path)));
                mSimpleExoPlayer.prepare(videoSource);
                mSimpleExoPlayer.setPlayWhenReady(true);*/
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
