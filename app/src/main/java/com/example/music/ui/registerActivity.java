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

public class registerActivity extends AppCompatActivity {

    private Button mbtn_register;
    private Button mbtn_back;
    private TextView met_username;
    private TextView metpsw;
    private TextView met_psw_again;
    private Handler handler;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //去除标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_register);

        mbtn_register=findViewById(R.id.btn_register);
        mbtn_back=findViewById(R.id.btn_back);
        met_username=(EditText) findViewById(R.id.et_username);
        metpsw=(EditText)findViewById(R.id.etpsw);
        met_psw_again=(EditText)findViewById(R.id.et_psw_again);

        WorkThread wt=new WorkThread();
        wt.start();

        //注册
        mbtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na = met_username.getText().toString();//获取页面账号
                String ps = metpsw.getText().toString();//获取页面密码
                String psa = met_psw_again.getText().toString();//获取页面密码
                if(na.isEmpty()){
                    displayerror3();
                }else if(ps.isEmpty()||psa.isEmpty()){
                    displayerror2();
                }else if(!ps.equals(psa)){
                    displayerror1();
                }else{
                    Message m=handler.obtainMessage();//获取事件
                    Bundle b=new Bundle();
                    b.putString("name",na);
                    b.putString("pass",ps);
                    m.setData(b);
                    handler.sendMessage(m);//把信息放到通道中
                    //Toast.makeText(registerActivity.this, "成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //返回
        mbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, loginActivity.class));
                finish();
            }
        });
    }

    public void displayerror1(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("密码错误")//标题
                .setMessage("两次密码不同")//内容
                .create();
        alertDialog1.show();
    }
    public void displayerror2(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("密码错误")//标题
                .setMessage("密码不可为空")//内容
                .create();
        alertDialog1.show();
    }
    public void displayerror3(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("账号错误")//标题
                .setMessage("账号不可为空")//内容
                .create();
        alertDialog1.show();
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
                    String pass = b.getString("pass");//根据键取值
                    DBUtil db= new DBUtil();
                    String ret = db.register(name,pass);//得到返回值
                    if(ret.equals("0")){
                        displayerror();
                    }
                    else
                        displaytrue();
                }
            };
            Looper.loop();//Looper循环，通道中有数据执行，无数据堵塞
        }
    }
    public void displayerror(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle("注册失败")//标题
                .setMessage("账号重复")//内容
                .create();
        alertDialog1.show();
    }
    public void displaytrue(){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setMessage("注册成功")//内容
                .create();
        alertDialog1.show();
    }
}
