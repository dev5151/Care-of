package com.dev5151.careof.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.dev5151.careof.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //ImageView img = findViewById(R.id.imageView);

        int SPLASH_TIME_OUT = 700;
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, OnboardingActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}

