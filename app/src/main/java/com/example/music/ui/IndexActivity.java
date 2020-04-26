package com.example.music.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;

public class IndexActivity extends AppCompatActivity {

    private Handler mHander=new Handler();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        mHander.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(IndexActivity.this, loginActivity.class));
                finish();
            }
        },2000);
    }

}
