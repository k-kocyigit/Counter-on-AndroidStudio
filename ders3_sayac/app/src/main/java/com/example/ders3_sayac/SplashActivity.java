package com.example.ders3_sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler activityHandler =new Handler(Looper.getMainLooper());
        activityHandler.postDelayed(new Runnable() {
            @Override public void run() {
                finish();
            }
            }
            ,2000);
    }
}