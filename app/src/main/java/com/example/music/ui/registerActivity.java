package com.example.music.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;

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
                }else
                    Toast.makeText(registerActivity.this, "成功", Toast.LENGTH_SHORT).show();
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
}
