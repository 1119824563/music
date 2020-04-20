package com.example.music.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.MainActivity;
import com.example.music.R;

public class loginActivity extends AppCompatActivity{

    private Button mbtn_login;
    private TextView mtv_register;
    private TextView mtv_find_psw;
    private TextView met_user_name;
    private TextView met_psw;
    private Handler handler;
    private String user_tv;
    private String password_tv;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mbtn_login=findViewById(R.id.btn_login);
        mtv_register=findViewById(R.id.tv_register);
        mtv_find_psw=findViewById(R.id.tv_find_psw);

        met_user_name = findViewById(R.id.et_user_name);//用户名
        met_psw = (EditText) findViewById(R.id.et_psw);//密码

        mbtn_login.setOnClickListener(getLogin());
        mtv_register.setOnClickListener(getRegister());
        mtv_find_psw.setOnClickListener(getFind_psw());

       /* WorkThread wt=new WorkThread();
        wt.start();*/
    }

    //登录
    private View.OnClickListener getLogin(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                /*user_tv=met_user_name.getText().toString().trim();
                password_tv=met_psw.getText().toString().trim();
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putString("name",user_tv);
                b.putString("pass",password_tv);//以键值对形式放进 Bundle中
                m.setData(b);
                m.what=0;
                handler.sendMessage(m);//把信息放到通道中*/
                startActivity(new Intent(loginActivity.this, MainActivity.class));
                finish();

            }
        };
    }

    //注册
    private View.OnClickListener getRegister(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(loginActivity.this, registerActivity.class));
                finish();
            }
        };
    }

    //找回密码
    private View.OnClickListener getFind_psw(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(loginActivity.this, findpawActivity.class));
                finish();
            }
        };
    }

    /*class WorkThread extends  Thread{
        @Override
        public  void run(){
            Looper.prepare();
            handler=new Handler(){
                @Override
                public  void handleMessage(Message m){
                    switch (m.what) {
                        case 0:
                            Bundle b = m.getData();//得到与信息对用的Bundle
                            String name = b.getString("name");//根据键取值
                            String pass = b.getString("pass");
                            DBUtil db= new DBUtil(name,pass);
                            String ret = db.QuerySQL();//得到返回值
                            if (ret.equals("1"))//为1，页面跳转，登陆成功
                            {
                                Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(loginActivity.this, "错误", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }*/
}
