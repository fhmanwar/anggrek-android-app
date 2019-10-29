package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Splash extends AppCompatActivity {
    private int time_loading = 1000; //1000 = 1 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // setelah loading menuju dashboard
                Intent Home = new Intent(Splash.this, Login.class);
                startActivity(Home);
                finish();
            }
        },time_loading);

    }
}
