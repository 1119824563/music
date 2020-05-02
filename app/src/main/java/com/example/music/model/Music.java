package com.example.music.model;

import android.graphics.Bitmap;

//本地音乐模型
public class Music {

    //歌名
    private String musicName;
    //歌手
    private String musicSinger;
    //专辑
    private String album;
    //时长
    private String duration;
    //封面
    private Bitmap musicCover;
    //路径
    private String path;
    //歌词
    private String lrcPath;

    public void setLrcPath(String lrcPath){
        this.lrcPath=lrcPath;
    }

    public String getLrcPath(){
        return lrcPath;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicSinger() {
        return musicSinger;
    }

    public void setMusicSinger(String musicSinger) {
        this.musicSinger = musicSinger;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Bitmap getMusicCover() {
        return musicCover;
    }

    public void setMusicCover(Bitmap musicCover) {
        this.musicCover = musicCover;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
