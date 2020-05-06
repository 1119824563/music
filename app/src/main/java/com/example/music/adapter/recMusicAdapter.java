package com.example.music.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.netmusic;
import com.example.music.ui.RecMusicPlayActivity;

import java.util.List;

import static android.os.SystemClock.sleep;

public class recMusicAdapter extends RecyclerView.Adapter<recMusicAdapter.ViewHolder>{
    private LayoutInflater inflater;
    public Context mContext;
    boolean love=false;

    public recMusicAdapter(Context mContext, List<netmusic> mnetmusicList){
        this.mContext=mContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public recMusicAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_netmusic,null);
        recMusicAdapter.ViewHolder viewHolder = new recMusicAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final recMusicAdapter.ViewHolder holder, final int position) {

        final netmusic mnetmusic =  NetmusicManager.getInstance().recmusicList.get(position);
        holder.tv_netmusic_name.setText(mnetmusic.getSongname());
        holder.tv_netmusic_singer.setText(mnetmusic.getSingername());

        //列表点击
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NetmusicManager.getInstance().getrecList(position);
                Toast.makeText(mContext, "接口数据获取较慢，请勿重复点击", Toast.LENGTH_SHORT).show();
                sleep(2000);
                if(NetmusicManager.getInstance().iftrue){
                    Intent intent = new Intent(mContext, RecMusicPlayActivity.class);
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
                if(love){
                    netmusic mlovemusic=new netmusic();
                    mlovemusic.setSongname(mnetmusic.getSongname());
                    mlovemusic.setSingername(mnetmusic.getSingername());
                    mlovemusic.setMusicid(mnetmusic.getMusicid());
                    NetmusicManager.getInstance().lovemusicList.add(mlovemusic);
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
                    love=true;
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
        return NetmusicManager.getInstance().recmusicList.size();
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
