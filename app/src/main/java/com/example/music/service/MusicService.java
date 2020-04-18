package com.example.music.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;

import com.example.music.impl.OnMusicClient;
import com.example.music.impl.OnMusicService;
import com.example.music.model.Music;
import com.example.music.utils.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MusicService extends Service{

    private static final String TAG="MusicService";
    private Handler mHandler=new Handler();
    //线程池
    private ExecutorService mExecutorService;
    //歌曲解析类
    private MediaMetadataRetriever mmr;
    //内存卡根目录
    private static final String PATH_SDCARD = "/mnt/sdcard";
    //是否在搜索
    private boolean isScan = false;
    //数据源
    private List<Music> mMusicList= new ArrayList<>();
    //服务端接口
    private OnMusicService serviceImpl;
    //客户端接口
    private OnMusicClient clientImpl = new OnMusicClient() {
        @Override
        public List<Music> getMusicList() {
            return mMusicList;
        }

        @Override
        public void startScanMusic() {
           mExecutorService.execute(new Runnable() {
                @Override
                public void run() {
                    //已经开始搜索
                    isScan=true;
                    if(mMusicList.size()>0) {
                        mMusicList.clear();
                    }
                    mmr=new MediaMetadataRetriever();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            serviceImpl.scanMusicStart();
                        }
                    });
                    //正在搜索
                    scanMusic(PATH_SDCARD);
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            serviceImpl.scanMusicStop();
                        }
                    });
                    if (mmr != null) {
                        mmr.release();
                    }
                    isScan = false;
                }
            });
        }

        @Override
        public boolean isScan() {
            return isScan;
        }
    };

    //内部类
    public class MusicBinder extends Binder{

        //绑定服务的回调 ——> Activity绑定之后可以接收Service的接口
        public void bindServiceImpl(OnMusicService service) {
            serviceImpl = service;
        }

        //绑定客户端回调 ——> Service拿到Activity的接口对象可以调用并通知Activity
        public OnMusicClient bindClientImple() {

            return clientImpl;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mExecutorService = Executors.newFixedThreadPool(3);
    }


    //搜索音乐
    private void scanMusic(String path) {
        File file = new File(path+"/");
        if (file == null) {
            return;
        }
        File[] files = file.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                File f=files[i];
                //是否是文件夹
                if (f.isDirectory()) {
                    scanMusic(f.getPath());
                } else {
                    //判断是否是mp3文件
                    if (f.getName().endsWith(".mp3")) {
                        parsingMediaMusic(f.getAbsolutePath());
                    }
                }
            }
        }
    }

    //解析音乐
    private void parsingMediaMusic(String path) {
        mmr.setDataSource(path);
        //歌曲名
        String title = "";
        //专辑
        String album = "";
        //歌手
        String artist = "";
        //时长
        String duration = "";
        //封面
        Bitmap mBitmap = null;
        try {
            title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            byte[] pic = mmr.getEmbeddedPicture();
            if (null != pic && pic.length > 0) {
                mBitmap = BitmapFactory.decodeByteArray(pic, 0, pic.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            //解決中文乱码
            if (title.equals(new String(title.getBytes("iso8859-1"), "iso8859-1"))) {
                title = new String(title.getBytes("iso8859-1"), "gb2312");
            }
            if (artist.equals(new String(artist.getBytes("iso8859-1"), "iso8859-1"))) {
                artist = new String(artist.getBytes("iso8859-1"), "gb2312");
            }
            if (album.equals(new String(album.getBytes("iso8859-1"), "iso8859-1"))) {
                album = new String(album.getBytes("iso8859-1"), "gb2312");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //如果没有歌曲名，用.MP3文件的文件名当歌曲名
        if (title == null || "".equals(title) || "null".equals(title)) {
            title = new File(path).getName();
            if (title.contains(".mp3")) {
                title = title.split(".mp3")[0];
            }
        }

        //最终过滤
        if (title == null || "".equals(title) || "null".equals(title)) {
            title = "unknown";
        }
        if (artist == null || "".equals(artist) || "null".equals(artist)) {
            artist = "unknown";
        }
        if (album == null || "".equals(album) || "null".equals(album)) {
            album = "unknown";
        }

        Music music = new Music();

        music.setMusicName(title);
        music.setMusicSinger(artist);
        music.setAlbum(album);
        //转化为分；秒
        music.setDuration(TimeUtils.formatDuring(Long.parseLong(duration)));
        music.setPath(path);

        String lrcPath=path.substring(0,path.length() - 3);
        music.setLrcPath(lrcPath+"lrc");

        music.setMusicCover(mBitmap);
        //去掉手机中的一些无用的短促的mp3后缀文件，大于1分钟的基本是歌曲
        if(Long.parseLong(duration)>60000){
        mMusicList.add(music);
        }
    }

}
