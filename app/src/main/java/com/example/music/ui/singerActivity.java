package com.example.music.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.R;
import com.example.music.adapter.singerAdapter;
import com.example.music.manager.NetmusicManager;
import com.example.music.utils.DBUtil;

public class singerActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView stroesingmusic;
    private TextView singername;
    private TextView singerproduce;
    RecyclerView mRecyclerView;
    private singerAdapter msingMusicAdapter;
    private Handler handler;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singer);

        singername=findViewById(R.id.Sing_name);
        singerproduce=findViewById(R.id.singerproduce);
        stroesingmusic=findViewById(R.id.stroesingmusic);

        singername.setText(NetmusicManager.getInstance().getSingername());
        singerproduce.setText(NetmusicManager.getInstance().getSingermore());
        stroesingmusic.setOnClickListener(this);


        //列表初始化显示
        mRecyclerView=(RecyclerView) findViewById(R.id.netmusiclist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        msingMusicAdapter = new singerAdapter(this, NetmusicManager.getInstance().singermusicList);
        mRecyclerView.setAdapter(msingMusicAdapter);

        WorkThread wt=new WorkThread();
        wt.start();

    }

    //实现播放界面全屏
    private void initSystemUI() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stroesingmusic:
                for(int i = 0; i< NetmusicManager.getInstance().singermusicList.size(); i++){
                    String mname = NetmusicManager.getInstance().singermusicList.get(i).getSongname();//歌名
                    String msingername = NetmusicManager.getInstance().singermusicList.get(i).getSingername();
                    Message m=handler.obtainMessage();//获取事件
                    Bundle b=new Bundle();
                    b.putString("mname",mname);
                    b.putString("msingername",msingername);
                    m.setData(b);
                    handler.sendMessage(m);//把信息放到通道中
                }
                Toast.makeText(singerActivity.this, "存放完成，请勿重复存放", Toast.LENGTH_SHORT).show();
              break;
        }
    }

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
                    String mname = b.getString("mname");
                    String msingername = b.getString("msingername");
                    DBUtil db= new DBUtil();
                    String ret = db.storesingermusic(mname,msingername);//得到返回值
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

}
