package com.example.music.model;

import android.graphics.Bitmap;

//网络歌曲模型
public class netmusic {

    //歌曲id
    private int musicid;
    //歌名
    private String songname;
    //歌手
    private String singername;
    //歌手id
    private String singerid;
    private int singid;

    public int getSingid() {
        return singid;
    }

    public void setSingid(int singid) {
        this.singid = singid;
    }

    //歌曲url
    private String songurl;
    //封面url
    private String img1v1Url;
    //封面
    private Bitmap bitmap;
    //专辑
    private String album;
    //歌词
    private String musiclrc;
    //歌手介绍
    private String singermore;

    public String getSingermore() {
        return singermore;
    }

    public void setSingermore(String singermore) {
        this.singermore = singermore;
    }

    public String getSingerid() {
        return singerid;
    }

    public void setSingerid(String singerid) {
        this.singerid = singerid;
    }

    public String getMusiclrc() {
        return musiclrc;
    }

    public void setMusiclrc(String musiclrc) {
        this.musiclrc = musiclrc;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getMusicid() {
        return musicid;
    }

    public void setMusicid(int musicid) {
        this.musicid = musicid;
    }

    public String getSongname() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname = songname;
    }

    public String getSingername() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername = singername;
    }

    public String getSongurl() {
        return songurl;
    }

    public void setSongurl(String songurl) {
        this.songurl = songurl;
    }

    public String getImg1v1Url() {
        return img1v1Url;
    }

    public void setImg1v1Url(String img1v1Url) {
        this.img1v1Url = img1v1Url;
    }

}
