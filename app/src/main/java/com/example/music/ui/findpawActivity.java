package com.example.music.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;
import com.example.music.utils.DBUtil;

public class findpawActivity extends AppCompatActivity {

    private Button mfind_paw;
    private Button mfind_back;
    private TextView met_find_pwd;
    private Handler handler;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_findpaw);

        mfind_paw=findViewById(R.id.find_paw);
        mfind_back=findViewById(R.id.find_back);
        met_find_pwd=(EditText) findViewById(R.id.et_find_pwd);

        WorkThread wt=new WorkThread();
        wt.start();

        //找回密码
        mfind_paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na=met_find_pwd.getText().toString();//获取页面账号
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putString("name",na);
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中
            }
        });

        //返回
        mfind_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(findpawActivity.this, loginActivity.class));
                finish();
            }
        });
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
                    String name = b.getString("name");//根据键取值
                    DBUtil db= new DBUtil();
                    String ret = db.FindSQL(name);//得到返回值
                    if(ret.equals("0")){
                        displayerror();
                    }
                    else
                        displaypass(ret);
                        //Toast.makeText(findpawActivity.this, "密码为"+ret, Toast.LENGTH_SHORT).show();
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    public void displayerror(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("账号错误")//标题
                .setMessage("没有此账号")//内容
                .create();
        alertDialog1.show();
    }

    public void displaypass(String ret){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("密码")//标题
                .setMessage("密码为"+ret)//内容
                .create();
        alertDialog1.show();
    }
}
