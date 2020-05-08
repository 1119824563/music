package com.example.music.ui;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
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
import com.example.music.utils.DBUtil;
import com.example.music.utils.TimeUtils;
import com.example.music.utils.UtilBitmap;
import com.pili.pldroid.player.PLOnCompletionListener;

import java.net.URL;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.os.SystemClock.sleep;

public class RecMusicPlayActivity extends AppCompatActivity implements View.OnClickListener{
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
    private Handler handler;
    private Button mshare;

    private  int position;
    private Random random;
    private netmusic mic;
    private boolean repeat=false;

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
        mshare=findViewById(R.id.share);

        mIvPrev.setOnClickListener(this);
        mIvControl.setOnClickListener(this);
        mIvNext.setOnClickListener(this);
        mProfileImage.setOnClickListener(this);
        mivplaymodel.setOnClickListener(this);
        mTvMusicSinger.setOnClickListener(this);
        mshare.setOnClickListener(this);

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

    //播放音乐
    public void startMusic(int position){

        if(!NetmusicManager.getInstance().iftrue){
            Toast.makeText(this, "暂无版权，无法播放", Toast.LENGTH_SHORT).show();
        }

        mic= NetmusicManager.getInstance().recmusicList.get(position);
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
        NetmusicManager.getInstance().setOnMusicProgress(new NetmusicManager.OnMusicProgressListener() {
            @Override
            public void onProgress(long progress,long all) {
                mSProgress.setProgress((int) progress);
                mSProgress.setMax((int) all);
                //每秒刷新显示
                mTvCurrentTime.setText(TimeUtils.formatDuring(progress));
                //进度条总时间
                mTvAllTime.setText(TimeUtils.formatDuring(all));
            }
        });
        NetmusicManager.getInstance().setOnCompletionListener(new PLOnCompletionListener() {
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
                    NetmusicManager.getInstance().setCurrentPosition(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                //要分享的文本内容
                shareIntent.putExtra(Intent.EXTRA_TEXT, "一起听歌吧,推荐你听："+ NetmusicManager.getInstance().recmusicList.get(position).getSongname());
                //需要使用Intent.createChooser
                shareIntent = Intent.createChooser(shareIntent, "选择要分享的位置");
                startActivity(shareIntent);
                break;
            case R.id.tv_music_singer:
                NetmusicManager.getInstance().singermusicList.clear();
                NetmusicManager.getInstance().setSingername(NetmusicManager.getInstance().recmusicList.get(position).getSingername());
                NetmusicManager.getInstance().getsingerrec(position);
                sleep(2000);
                startActivity(new Intent(RecMusicPlayActivity.this,singerActivity.class));
                break;
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
                NetmusicManager.getInstance().getList(position);
                musicPlay(false);
                break;
            case R.id.profile_image:
                int mid=NetmusicManager.getInstance().recmusicList.get(position).getMusicid();//id
                String mname = NetmusicManager.getInstance().recmusicList.get(position).getSongname();//歌名
                String msingername = NetmusicManager.getInstance().recmusicList.get(position).getSingername();//歌手
                String malbum=NetmusicManager.getInstance().recmusicList.get(position).getAlbum();//专辑
                String murl= NetmusicManager.getInstance().recmusicList.get(position).getSongurl();//歌曲url
                String mlrc=NetmusicManager.getInstance().recmusicList.get(position).getMusiclrc();//歌词
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putInt("mid",mid);
                b.putString("mname",mname);
                b.putString("msingername",msingername);
                b.putString("malbum",malbum);
                b.putString("mlrc",mlrc);
                b.putString("murl",murl);
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中
                if(repeat){
                    Toast.makeText(this, "存放成功", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "数据已存在", Toast.LENGTH_SHORT).show();
                }
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

    //存放专辑，歌词，歌曲url
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
                    int mid = b.getInt("mid");//根据键取值
                    String mname = b.getString("mname");
                    String murl=b.getString("murl");
                    String msingername = b.getString("msingername");
                    String malbum = b.getString("malbum");
                    String mlrc=b.getString("mlrc");
                    DBUtil db= new DBUtil();
                    String ret = db.storeurl(mid,mname,murl);//得到返回值
                    String ret2 = db.storelrc(mid,mname,mlrc);
                    String ret3 = db.storeablum(mid,mname,msingername,malbum);
                    repeat=ret.equals("1");
                    repeat=ret2.equals("1");
                    repeat=ret3.equals("1");
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
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
                    NetmusicManager.getInstance().getrecList(position);
                    startMusic(position);
                }else{
                    //已经没有上一曲
                    Toast.makeText(this, "已经没有上一曲", Toast.LENGTH_SHORT).show();
                }
            } else {
                position = position + 1;
                if (position <= (NetmusicManager.getInstance().recmusicList.size() - 1)) {
                    NetmusicManager.getInstance().getrecList(position);
                    startMusic(position);
                }else{
                    //已经没有下一曲
                    Toast.makeText(this, "已经没有下一曲", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_RANDOM){
            if(random!=null){
                int x=random.nextInt(NetmusicManager.getInstance().recmusicList.size());
                NetmusicManager.getInstance().getrecList(x);
                startMusic(x);
            }
        }else if(NetmusicManager.MEDIA_PLAY_MODE==NetmusicManager.MEDIA_PLAY_MODE_SINGLE){
            NetmusicManager.getInstance().getrecList(position);
            sleep(1000);
            startMusic(position);
        }
    }
}
