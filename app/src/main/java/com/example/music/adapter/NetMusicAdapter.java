package com.example.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.netmusic;
import com.example.music.ui.NetPlayActivity;

import java.util.List;

import static android.os.SystemClock.sleep;

//网络音乐适配器
public class NetMusicAdapter extends RecyclerView.Adapter<NetMusicAdapter.ViewHolder>{
    private LayoutInflater inflater;
    public Context mContext;
    private Handler handler;

    public NetMusicAdapter(Context mContext,List<netmusic>mnetmusicList){
        this.mContext=mContext;
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

        final netmusic mnetmusic =  NetmusicManager.getInstance().mnetmusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());

        //列表点击
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int p=position;
                int musicid=NetmusicManager.getInstance().mnetmusicList.get(position).getMusicid();//获取对应的音乐id
                //Toast.makeText(mContext, "点击"+musicid, Toast.LENGTH_SHORT).show();
                NetmusicManager.getInstance().getList(p,musicid);
                sleep(1000);
                if(NetmusicManager.getInstance().iftrue){
                    Toast.makeText(mContext, "接口数据获取较慢，如果歌曲有误，返回再点一次", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, NetPlayActivity.class);
                    intent.putExtra("position",position);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext, "暂无版权，无法播放", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return NetmusicManager.getInstance().mnetmusicList.size();
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

}
