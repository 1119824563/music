package com.example.music.adapter;

import android.content.Context;
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

import java.util.List;

public class loveMusicAdapter extends RecyclerView.Adapter<loveMusicAdapter.ViewHolder>{
    private LayoutInflater inflater;
    public Context mContext;
    boolean love=false;

    public loveMusicAdapter(Context mContext, List<netmusic> mnetmusicList){
        this.mContext=mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public loveMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_netmusic,null);
        loveMusicAdapter.ViewHolder viewHolder = new loveMusicAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final loveMusicAdapter.ViewHolder holder, final int position) {

        final netmusic mnetmusic =  NetmusicManager.getInstance().lovemusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());
        holder.tv_netmusic_album.setText(mnetmusic.getAlbum());

        //下载
        holder.down.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "已下载", Toast.LENGTH_SHORT).show();
            }
        });
        //列表点击
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "收藏列表仅供显示，不支持播放", Toast.LENGTH_SHORT).show();
            }
        });

        //长按
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(mContext, "歌曲已收藏", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return NetmusicManager.getInstance().lovemusicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_netmusic_name;
        private TextView tv_netmusic_singer;
        private TextView tv_netmusic_album;
        private Button down;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_netmusic_name = itemView.findViewById(R.id.tv_netmusic_name);
            tv_netmusic_singer = itemView.findViewById(R.id.tv_netmusic_singer);
            down=itemView.findViewById(R.id.downmusic);
            tv_netmusic_album=itemView.findViewById(R.id.tv_netmusic_album);
        }
    }
}
