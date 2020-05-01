package com.example.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.model.netmusic;

import java.util.ArrayList;
import java.util.List;

public class NetMusicAdapter extends RecyclerView.Adapter<NetMusicAdapter.ViewHolder>{
    private List<netmusic> mnetmusicList = new ArrayList<>();;
    private LayoutInflater inflater;
    public Context mContext;
    public netmusic mnetmusic;

    public NetMusicAdapter(Context mContext,List<netmusic>mnetmusicList){
        this.mContext=mContext;
        this.mnetmusicList=mnetmusicList;
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

        netmusic mnetmusic =  mnetmusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());

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

}
