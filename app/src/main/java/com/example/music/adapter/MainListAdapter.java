package com.example.music.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.model.Music;
import com.example.music.ui.PlayerActivity;

import java.util.List;

//本地音乐适配器
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.ViewHolder> {
    private Context mContext;
    private LayoutInflater inflater;
    private List<Music> mMusicList;

    public MainListAdapter(Context mContext, List<Music> mMusicList) {
        this.mContext = mContext;
        this.mMusicList = mMusicList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_music_list,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Music music =  mMusicList.get(position);

        holder.tv_music_name.setText(music.getMusicName());
        holder.tv_music_singer.setText(music.getMusicSinger());
        holder.tv_music_album.setText(music.getAlbum());
        holder.tv_music_time.setText(music.getDuration());

        //跳到播放界面
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position",position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_music_name;
        private TextView tv_music_singer;
        private TextView tv_music_album;
        private TextView tv_music_time;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_music_name = itemView.findViewById(R.id.tv_music_name);
            tv_music_singer = itemView.findViewById(R.id.tv_music_singer);
            tv_music_album = itemView.findViewById(R.id.tv_music_album);
            tv_music_time = itemView.findViewById(R.id.tv_music_time);
        }
    }
}
