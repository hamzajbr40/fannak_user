package com.hamzajbr.fannak_user.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.hamzajbr.fannak_user.R;
import com.hamzajbr.fannak_user.utilities.Utils;

public class SplashActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler  = new Handler();
        handler.postDelayed(() -> Utils.goToActivity(SplashActivity.this, LanguageActivity.class, true), 5000);
    }
}
