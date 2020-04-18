package com.example.music.impl;


import com.example.music.model.Music;

import java.util.List;

public interface OnMusicClient {

    //获取音乐列表
    List<Music> getMusicList();

    //搜索音乐
    void startScanMusic();

    //是否在搜索
    boolean isScan();
}
