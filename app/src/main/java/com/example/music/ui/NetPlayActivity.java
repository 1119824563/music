package com.example.music.ui;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;
import com.example.music.manager.NetmusicManager;
import com.example.music.model.netmusic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NetPlayActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTvMusicName;
    private TextView mTvMusicSinger;
    private CircleImageView mProfileImage;
    private TextView mTvCurrentTime;
    private SeekBar mSProgress;
    private TextView mTvAllTime;
    private ImageView mIvPrev;
    private ImageView mIvControl;
    private ImageView mIvNext;
    private LinearLayout mLlContent;
    private ImageView mivplaymodel;
    private ObjectAnimator circleRotateAnim;
    private List<netmusic> mnetmusicList = new ArrayList<>();;
    private Context mcontext;

    private  int position;
    private Random random;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initSystemUI();

        random = new Random();

        mTvMusicName = findViewById(R.id.tv_music_name);
        mTvMusicSinger = findViewById(R.id.tv_music_singer);
        mProfileImage = findViewById(R.id.profile_image);
        mTvCurrentTime = findViewById(R.id.tv_current_time);
        mSProgress = findViewById(R.id.s_progress);
        mTvAllTime = findViewById(R.id.tv_all_time);
        mIvPrev = findViewById(R.id.iv_prev);
        mIvControl = findViewById(R.id.iv_control);
        mIvNext = findViewById(R.id.iv_next);
        mLlContent = findViewById(R.id.ll_content);
        mivplaymodel=findViewById(R.id.iv_play_model);

        mIvPrev.setOnClickListener(this);
        mIvControl.setOnClickListener(this);
        mIvNext.setOnClickListener(this);
        mProfileImage.setOnClickListener(this);
        mivplaymodel.setOnClickListener(this);

        //6s 内 旋转一圈 0 - 360°
        circleRotateAnim = ObjectAnimator.ofFloat(mProfileImage, "rotation", 0.0f, 360.0f);
        circleRotateAnim.setDuration(6000);
        //循环的次数无限
        circleRotateAnim.setRepeatCount(Animation.INFINITE);
        //持续的
        circleRotateAnim.setRepeatMode(ObjectAnimator.RESTART);
        //差值器默认
        circleRotateAnim.setInterpolator(new LinearInterpolator());

        position=getIntent().getIntExtra("position",-1);
        if (position!=-1){
            //获取音乐相关信息
            startMusic(position);
        }
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


    //播放音乐
    public void startMusic(int position){

        if(!NetmusicManager.getInstance().iftrue){
            Toast.makeText(mcontext, "暂无版权，无法播放", Toast.LENGTH_SHORT).show();
        }else{
            netmusic mic= NetmusicManager.getInstance().mnetmusicList.get(position);
            //进度条
            mSProgress.setProgress(0);

            //中间的播放按钮状态为暂停
            mIvControl.setBackgroundResource(R.drawable.ic_pause);

            mTvMusicName.setText(mic.getSongname());
            mTvMusicSinger.setText(mic.getSingername());
        }
    }

    @Override
    //点击
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_prev:
                musicPlay(true);
                break;
            case R.id.iv_control:

                break;
            case R.id.iv_next:
                musicPlay(false);
                break;
            case R.id.profile_image:

                break;
            case R.id.iv_play_model:
                //顺序→随机→单曲
                if( NetmusicManager.MEDIA_PLAY_MODE == NetmusicManager.MEDIA_PLAY_MODE_ORDER){
                    NetmusicManager.MEDIA_PLAY_MODE = NetmusicManager.MEDIA_PLAY_MODE_RANDOM;
                    Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
                    mivplaymodel.setImageResource(R.drawable.random);
                }else if( NetmusicManager.MEDIA_PLAY_MODE == NetmusicManager.MEDIA_PLAY_MODE_RANDOM){
                    NetmusicManager.MEDIA_PLAY_MODE = NetmusicManager.MEDIA_PLAY_MODE_SINGLE;
                    Toast.makeText(this, "单曲循环", Toast.LENGTH_SHORT).show();
                    mivplaymodel.setImageResource(R.drawable.single);
                }else if( NetmusicManager.MEDIA_PLAY_MODE == NetmusicManager.MEDIA_PLAY_MODE_SINGLE){
                    NetmusicManager.MEDIA_PLAY_MODE = NetmusicManager.MEDIA_PLAY_MODE_ORDER;
                    Toast.makeText(this, "顺序播放", Toast.LENGTH_SHORT).show();
                    mivplaymodel.setImageResource(R.drawable.order);
                }
                break;
        }
    }

    /**
     * true：上一曲
     * false:下一曲
     */
    private void musicPlay(boolean isPrev) {

        if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_ORDER){
            if (isPrev) {
                position = position - 1;
                if (position >= 0) {
                    startMusic(position);
                }else{
                    //已经没有上一曲
                    Toast.makeText(this, "已经没有上一曲", Toast.LENGTH_SHORT).show();
                }
            } else {
                position = position + 1;
                if (position <= (NetmusicManager.getInstance().mnetmusicList.size() - 1)) {
                    startMusic(position);
                }else{
                    //已经没有下一曲
                    Toast.makeText(this, "已经没有下一曲", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_RANDOM){
            if(random!=null){
                int x=random.nextInt(NetmusicManager.getInstance().mnetmusicList.size());
                startMusic(x);
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_SINGLE){
            startMusic(position);
        }
    }
}
