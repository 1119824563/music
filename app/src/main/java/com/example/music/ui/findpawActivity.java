package com.example.music.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;

public class findpawActivity extends AppCompatActivity {

    private Button mfind_paw;
    private Button mfind_back;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findpaw);

        mfind_paw=findViewById(R.id.find_paw);
        mfind_back=findViewById(R.id.find_back);

        mfind_paw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(findpawActivity.this, loginActivity.class));
                finish();
            }
        });
        mfind_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(findpawActivity.this, loginActivity.class));
                finish();
            }
        });
    }
}
