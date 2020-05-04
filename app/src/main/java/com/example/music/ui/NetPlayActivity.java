package com.example.music.ui;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
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
import com.example.music.utils.UtilBitmap;

import java.net.URL;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.os.SystemClock.sleep;

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

    private  int position;
    private Random random;
    private netmusic mic;

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
            Toast.makeText(this, "暂无版权，无法播放", Toast.LENGTH_SHORT).show();
        }

        mic= NetmusicManager.getInstance().mnetmusicList.get(position);
        sleep(1000);
        //播放
        //Toast.makeText(this, mic.getSongurl(), Toast.LENGTH_SHORT).show();
        NetmusicManager.getInstance().startPlay(mic.getSongurl());

        //进度条
        mSProgress.setProgress(0);
        //中间的播放按钮状态为暂停
        mIvControl.setBackgroundResource(R.drawable.ic_pause);

        mTvMusicName.setText(mic.getSongname());
        mTvMusicSinger.setText(mic.getSingername());

        circleRotateAnim.start();
        getBitmap();
        sleep(1000);
        if(mic.getBitmap()!=null){
            setMusicCover(mic.getBitmap());
        }else{
            setMusicCover(BitmapFactory.decodeResource(getResources(),R.drawable.iv_music_test_cover));
        }

    }

    //设置中间的圈圈
    private void setMusicCover(Bitmap musicCover) {
        mProfileImage.setImageBitmap(musicCover);

        //高斯模糊
        Bitmap mBitmap = UtilBitmap.blurImageView(this, mProfileImage, 20);
        if (mBitmap != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                mLlContent.setBackground(new BitmapDrawable(getResources(), mBitmap));
            }
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
                if(NetmusicManager.getInstance().MEDIA_STATUS == NetmusicManager.getInstance().MEDIA_STATUS_PLAY){
                    NetmusicManager.getInstance().pausePlay();
                    mIvControl.setBackgroundResource(R.drawable.ic_play);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        circleRotateAnim.pause();
                    }
                } else if(NetmusicManager.getInstance().MEDIA_STATUS == NetmusicManager.getInstance().MEDIA_STATUS_PAUSE){
                    NetmusicManager.getInstance().continuePlay();
                    mIvControl.setBackgroundResource(R.drawable.ic_pause);
                    circleRotateAnim.start();
                }
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

    //从url获取图片
    public void getBitmap(){
        new Thread(){
            public void run() {
                try {
                    if(mic.getImg1v1Url()!= null)
                    {
                        URL url = new URL(mic.getImg1v1Url());
                        mic.setBitmap(BitmapFactory.decodeStream(url.openStream()));
                    }else{
                        mic.setBitmap(null);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };
        }.start();
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
                    NetmusicManager.getInstance().getList(position);
                    sleep(2000);
                    startMusic(position);
                }else{
                    //已经没有上一曲
                    Toast.makeText(this, "已经没有上一曲", Toast.LENGTH_SHORT).show();
                }
            } else {
                position = position + 1;
                if (position <= (NetmusicManager.getInstance().mnetmusicList.size() - 1)) {
                    NetmusicManager.getInstance().getList(position);
                    sleep(2000);
                    startMusic(position);
                }else{
                    //已经没有下一曲
                    Toast.makeText(this, "已经没有下一曲", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_RANDOM){
            if(random!=null){
                int x=random.nextInt(NetmusicManager.getInstance().mnetmusicList.size());
                NetmusicManager.getInstance().getList(x);
                sleep(2000);
                startMusic(x);
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_SINGLE){
            NetmusicManager.getInstance().getList(position);
            sleep(2000);
            startMusic(position);
        }
    }
}
