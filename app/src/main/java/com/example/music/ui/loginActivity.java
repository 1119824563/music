package com.example.music.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.MainActivity;
import com.example.music.R;
import com.example.music.utils.DBUtil;

public class loginActivity extends AppCompatActivity{

    private Button mbtn_login;
    private TextView mtv_register;
    private TextView mtv_find_psw;
    private TextView met_user_name;
    private TextView met_psw;
    private Handler handler;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        //去除手机状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        mbtn_login=findViewById(R.id.btn_login);
        mtv_register=findViewById(R.id.tv_register);
        mtv_find_psw=findViewById(R.id.tv_find_psw);

        met_user_name = findViewById(R.id.et_user_name);//用户名
        met_psw = (EditText) findViewById(R.id.et_psw);//密码

        mbtn_login.setOnClickListener(getLogin());
        mtv_register.setOnClickListener(getRegister());
        mtv_find_psw.setOnClickListener(getFind_psw());

        WorkThread wt=new WorkThread();
        wt.start();
    }

    //登录
    private View.OnClickListener getLogin(){
        return new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String s= met_psw.getText().toString();//获取页面密码
                String sy=met_user_name.getText().toString();//获取页面账号
                Message m=handler.obtainMessage();//获取事件
                Bundle b=new Bundle();
                b.putString("pass",s);//以键值对形式放进 Bundle中
                b.putString("name",sy);
                m.setData(b);
                handler.sendMessage(m);//把信息放到通道中
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
            }
        };
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
                            String pass = b.getString("pass");
                            DBUtil db= new DBUtil();
                            String ret = db.QuerySQL(name,pass);//得到返回值
                            if (ret.equals("1"))//为1，页面跳转，登陆成功
                            {
                                startActivity(new Intent(loginActivity.this, MainActivity.class));
                                Toast.makeText(loginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                finish();
                                return;
                            }
                            else if(ret.equals("0")){
                                Toast.makeText(loginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                                displayerror();
                            }
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }

    public void displayerror(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setMessage("账号或密码错误")
                .create();
        alertDialog1.show();
    }
}
