package com.example.music.ui;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.music.manager.MusicManager;
import com.example.music.model.Music;
import com.example.music.utils.TimeUtils;
import com.example.music.utils.UtilBitmap;
import com.hw.lrcviewlib.LrcDataBuilder;
import com.hw.lrcviewlib.LrcRow;
import com.hw.lrcviewlib.LrcView;
import com.pili.pldroid.player.PLOnCompletionListener;

import java.io.File;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

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

    private  int position;

    private ObjectAnimator circleRotateAnim;

    private LrcView mLrcView;

    private Random random;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        initSystemUI();

        random = new Random();

        mLrcView=findViewById(R.id.mLrcView);
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
        mLrcView.setOnClickListener(this);
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

        MusicManager.getInstance().setOnMusicProgress(new MusicManager.OnMusicProgressListener() {
            @Override
            public void onProgress(long progress,long all) {
                mSProgress.setProgress((int) progress);
                mSProgress.setMax((int) all);
                //每秒刷新显示
                mTvCurrentTime.setText(TimeUtils.formatDuring(progress));
                //进度条总时间
                mTvAllTime.setText(TimeUtils.formatDuring(all));
                //使拖动时间后歌词对应
                mLrcView.smoothScrollToTime(progress);
            }
        });
        MusicManager.getInstance().setOnCompletionListener(new PLOnCompletionListener() {
            @Override
            public void onCompletion() {
                musicPlay(false);
            }
        });

        //实现拖到哪就播放到哪
        mSProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                MusicManager.getInstance().setCurrentPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        position=getIntent().getIntExtra("position",-1);
        if (position!=-1){
            //获取相关信息
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
        Music music = MusicManager.getInstance().getClientImpl().getMusicList().get(position);

        //播放
        MusicManager.getInstance().startPlay(music.getPath());

        //进度条
        mSProgress.setProgress(0);

        //中间的播放按钮状态为暂停
        mIvControl.setBackgroundResource(R.drawable.ic_pause);

        mTvMusicName.setText(music.getMusicName());
        mTvMusicSinger.setText(music.getMusicSinger());

        circleRotateAnim.start();

        if (music.getMusicCover() != null) {
            setMusicCover(music.getMusicCover());
        }else{
            setMusicCover(BitmapFactory.decodeResource(getResources(),R.drawable.iv_music_test_cover));
        }

        loadLrc(music);
    }

    //歌词
    private void loadLrc(Music music) {
        if(!TextUtils.isEmpty(music.getLrcPath())){
            List<LrcRow> lrcRows = new LrcDataBuilder().Build(new File(music.getLrcPath()));
            mLrcView.getLrcSetting()
                    .setTimeTextSize(40)//时间字体大小
                    .setSelectLineColor(Color.parseColor("#ffffff"))//选中线颜色
                    .setSelectLineTextSize(25)//选中线大小
                    .setHeightRowColor(Color.parseColor("#aaffffff"))//高亮字体颜色
                    .setNormalRowTextSize(45)//正常行字体大小
                    .setHeightLightRowTextSize(45)//高亮行字体大小
                    .setTrySelectRowTextSize(45)//尝试选中行字体大小
                    .setTimeTextColor(Color.parseColor("#ffffff"))//时间字体颜色
                    .setTrySelectRowColor(Color.parseColor("#55ffffff"));//尝试选中字体颜色

            mLrcView.commitLrcSettings();
            mLrcView.setLrcData(lrcRows);
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
                if(MusicManager.getInstance().MEDIA_STATUS == MusicManager.getInstance().MEDIA_STATUS_PLAY){
                    MusicManager.getInstance().pausePlay();
                    mIvControl.setBackgroundResource(R.drawable.ic_play);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        circleRotateAnim.pause();
                    }
                } else if(MusicManager.getInstance().MEDIA_STATUS == MusicManager.getInstance().MEDIA_STATUS_PAUSE){
                    MusicManager.getInstance().continuePlay();
                    mIvControl.setBackgroundResource(R.drawable.ic_pause);
                    circleRotateAnim.start();
                }
                break;
            case R.id.iv_next:
                musicPlay(false);
                break;
            case R.id.profile_image:
                mLrcView.setVisibility(View.VISIBLE);
                mProfileImage.setVisibility(View.GONE);
                break;
            case R.id.mLrcView:
                mProfileImage.setVisibility(View.VISIBLE);
                mLrcView.setVisibility(View.GONE);
                break;
            case R.id.iv_play_model:
                //顺序→随机→单曲
                if( MusicManager.MEDIA_PLAY_MODE == MusicManager.MEDIA_PLAY_MODE_ORDER){
                    MusicManager.MEDIA_PLAY_MODE = MusicManager.MEDIA_PLAY_MODE_RANDOM;
                    Toast.makeText(this, "随机播放", Toast.LENGTH_SHORT).show();
                    mivplaymodel.setImageResource(R.drawable.random);
                }else if( MusicManager.MEDIA_PLAY_MODE == MusicManager.MEDIA_PLAY_MODE_RANDOM){
                    MusicManager.MEDIA_PLAY_MODE = MusicManager.MEDIA_PLAY_MODE_SINGLE;
                    Toast.makeText(this, "单曲循环", Toast.LENGTH_SHORT).show();
                    mivplaymodel.setImageResource(R.drawable.single);
                }else if( MusicManager.MEDIA_PLAY_MODE == MusicManager.MEDIA_PLAY_MODE_SINGLE){
                    MusicManager.MEDIA_PLAY_MODE = MusicManager.MEDIA_PLAY_MODE_ORDER;
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

        if(MusicManager.MEDIA_PLAY_MODE==MusicManager.MEDIA_PLAY_MODE_ORDER){
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
                if (position <= (MusicManager.getInstance().getClientImpl().getMusicList().size() - 1)) {
                    startMusic(position);
                }else{
                    //已经没有下一曲
                    Toast.makeText(this, "已经没有下一曲", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(MusicManager.MEDIA_PLAY_MODE==MusicManager.MEDIA_PLAY_MODE_RANDOM){
            if(random!=null){
                int x=random.nextInt(MusicManager.getInstance().getClientImpl().getMusicList().size());
                startMusic(x);
            }
        }else if(MusicManager.MEDIA_PLAY_MODE==MusicManager.MEDIA_PLAY_MODE_SINGLE){
            startMusic(position);
        }
    }
}

