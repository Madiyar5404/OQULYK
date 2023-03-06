package com.example.oqulyk.Authorization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.oqulyk.R;

public class MainActivity extends AppCompatActivity {
    Button loginBtn,skipBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn=findViewById(R.id.loginBtn);
        skipBtn=findViewById(R.id.skipBtn);

        loginBtn.setOnClickListener( v ->  {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
        skipBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        });
    }
}