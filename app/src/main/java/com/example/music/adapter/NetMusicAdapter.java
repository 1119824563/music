package com.example.music.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.model.netmusic;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//网络音乐适配器
public class NetMusicAdapter extends RecyclerView.Adapter<NetMusicAdapter.ViewHolder>{
    private List<netmusic> mnetmusicList = new ArrayList<>();;
    private LayoutInflater inflater;
    public Context mContext;
    private Handler handler;
    boolean iftrue=false;
    public netmusic mnetmusic;



    public NetMusicAdapter(Context mContext,List<netmusic>mnetmusicList){
        this.mContext=mContext;
        this.mnetmusicList=mnetmusicList;
        WorkThread wt=new WorkThread();
        wt.start();
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public NetMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_netmusic,null);
        NetMusicAdapter.ViewHolder viewHolder = new NetMusicAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NetMusicAdapter.ViewHolder holder, final int position) {

        final netmusic mnetmusic =  mnetmusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());

        //列表点击
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int musicid=mnetmusicList.get(position).getMusicid();//获取对应的音乐id
                //Toast.makeText(mContext, "点击"+musicid, Toast.LENGTH_SHORT).show();
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putInt("key",musicid);//以键值对形式放进 Bundle中
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中
            }
        });

    }

    @Override
    public int getItemCount() {
        return mnetmusicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_netmusic_name;
        private TextView tv_netmusic_singer;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_netmusic_name = itemView.findViewById(R.id.tv_netmusic_name);
            tv_netmusic_singer = itemView.findViewById(R.id.tv_netmusic_singer);
        }
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
    //验证该音乐是否有版权
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
                    try {
                        json=getDataByid(keyword);
                        //Toast.makeText(mContext, json, Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject=JSONObject.parseObject(json);
                        iftrue=(boolean)jsonObject.get("success");
                        if(iftrue){
                            Toast.makeText(mContext, "可以播放", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mContext, "亲，暂无版权无法播放", Toast.LENGTH_SHORT).show();
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
