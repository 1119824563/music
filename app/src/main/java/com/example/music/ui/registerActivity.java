package com.example.music.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.music.R;

public class registerActivity extends AppCompatActivity {

    private Button mbtn_register;
    private Button mbtn_back;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mbtn_register=findViewById(R.id.btn_register);
        mbtn_back=findViewById(R.id.btn_back);

        mbtn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, loginActivity.class));
                finish();
            }
        });
        mbtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerActivity.this, loginActivity.class));
                finish();
            }
        });
    }
}
