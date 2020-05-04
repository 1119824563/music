package com.example.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.music.impl.OnNetMusicClient;
import com.example.music.impl.OnNetMusicService;

public class NetMusicService extends Service {


    //服务端接口
    private OnNetMusicService serviceImpl;
    //客户端接口
    private OnNetMusicClient clientImpl=new OnNetMusicClient() {
        @Override
        public void bindSucceed() {
            //Toast.makeText(NetMusicService.this, "OK", Toast.LENGTH_SHORT).show();
        }
    };

    //内部类
    public class NetMusicBinder extends Binder {

        //绑定服务的回调 ——> Activity绑定之后可以接收Service的接口
        public void bindServiceImpl(OnNetMusicService service) {
            serviceImpl = service;
        }

        //绑定客户端回调 ——> Service拿到Activity的接口对象可以调用并通知Activity
        public OnNetMusicClient bindClientImple() {
            return clientImpl;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new NetMusicBinder();
    }
}
