package com.example.music.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.MainActivity;
import com.example.music.R;

public class IndexActivity extends AppCompatActivity {

    private Handler mHander=new Handler();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //防止二次登陆
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        SharedPreferences pref = getSharedPreferences("data",MODE_PRIVATE);
        if(pref.getString("login",null)==null){
            editor.putString("login","1");
            editor.commit();
        }

        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                //防止二次登陆
                SharedPreferences.Editor editor2 = getSharedPreferences("data",MODE_PRIVATE).edit();
                SharedPreferences pref2 = getSharedPreferences("data",MODE_PRIVATE);
                if(pref2.getString("login","").equals("1")){
                    startActivity(new Intent(IndexActivity.this, loginActivity.class));
                    editor2.putString("login","2");
                    editor2.commit();
                    finish();

                }else{
                    startActivity(new Intent(IndexActivity.this, MainActivity.class));
                    finish();
                }
            }
        },2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
