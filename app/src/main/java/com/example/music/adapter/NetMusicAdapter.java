package com.example.music.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.netmusic;
import com.example.music.ui.NetPlayActivity;
import com.example.music.utils.Downloader;

import java.util.List;

import static android.os.SystemClock.sleep;

//网络音乐适配器
public class NetMusicAdapter extends RecyclerView.Adapter<NetMusicAdapter.ViewHolder>{
    private LayoutInflater inflater;
    public Context mContext;
    private Handler handler;
    boolean love=true;
    boolean c=true;
    String url=null;
    String urllrc=null;


    public NetMusicAdapter(Context mContext,List<netmusic>mnetmusicList){
        this.mContext=mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        WorkThread wt=new WorkThread();
        wt.start();
    }

    @Override
    public NetMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_netmusic,null);
        NetMusicAdapter.ViewHolder viewHolder = new NetMusicAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final NetMusicAdapter.ViewHolder holder, final int position) {

        final netmusic mnetmusic =  NetmusicManager.getInstance().mnetmusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());
        holder.tv_netmusic_album.setText(mnetmusic.getAlbum());
        final String name=mnetmusic.getSongname();
        //下载
        holder.down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetmusicManager.getInstance().getList(position);
                Toast.makeText(mContext, "正在判断版权获取音乐", Toast.LENGTH_SHORT).show();
                sleep(3000);
                url=NetmusicManager.getInstance().musicurl;
                urllrc=NetmusicManager.getInstance().musiclrc;
                if(NetmusicManager.getInstance().iftrue){
                    if(url!=null){
                        Message m=handler.obtainMessage();//获取事件
                        Bundle b=new Bundle();
                        b.putString("name",name);//以键值对形式放进 Bundle中
                        m.setData(b);
                        handler.sendMessage(m);//把信息放到通道中
                    }else{
                        Toast.makeText(mContext, "未获取到歌曲url，请再次点击", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(mContext, "暂无版权，无法下载", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //列表点击
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetmusicManager.getInstance().getList(position);
                Toast.makeText(mContext, "接口数据获取较慢，请勿重复点击", Toast.LENGTH_SHORT).show();
                sleep(2000);
                if(NetmusicManager.getInstance().iftrue){
                    Intent intent = new Intent(mContext, NetPlayActivity.class);
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, "暂无版权，无法播放", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //长按
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
           @Override
           public boolean onLongClick(View view) {
               AlertDialog isExit = new AlertDialog.Builder(mContext).create();
               isExit.setTitle("提示");
               isExit.setMessage("确定要收藏这首歌吗");
               isExit.setButton("确定",listener);
               isExit.setButton2("取消", listener);
               isExit.show();
               while (love){
                   if(c){
                       c=false;
                       netmusic mlovemusic=new netmusic();
                       mlovemusic.setSongname(mnetmusic.getSongname());
                       mlovemusic.setSingername(mnetmusic.getSingername());
                       mlovemusic.setMusicid(mnetmusic.getMusicid());
                       mlovemusic.setAlbum(mnetmusic.getAlbum());
                       NetmusicManager.getInstance().lovemusicList.add(mlovemusic);
                       break;
                   }
                   break;
               }
               return true;
           }
       });
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()
    {
        public void onClick(DialogInterface dialog, int which)
        {
            switch (which)
            {
                case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                    c=true;
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public int getItemCount() {
        return NetmusicManager.getInstance().mnetmusicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_netmusic_name;
        private TextView tv_netmusic_singer;
        private TextView tv_netmusic_album;
        private Button down;

        public ViewHolder(View itemView) {
            super(itemView);
            down=itemView.findViewById(R.id.downmusic);
            tv_netmusic_name = itemView.findViewById(R.id.tv_netmusic_name);
            tv_netmusic_singer = itemView.findViewById(R.id.tv_netmusic_singer);
            tv_netmusic_album=itemView.findViewById(R.id.tv_netmusic_album);
        }
    }

    //下载
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
                    String name = b.getString("name");//根据键取值
                    Downloader http = new Downloader();
                    String result = http.download(
                            url, "/music",
                            name+".mp3");
                    String resultlrc=http.download(urllrc,"/music",
                            name+".lrc");
                    Toast.makeText(mContext, result, Toast.LENGTH_SHORT).show();
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
}

