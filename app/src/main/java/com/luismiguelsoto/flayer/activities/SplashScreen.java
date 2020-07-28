package com.luismiguelsoto.flayer.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.luismiguelsoto.flayer.R;
import com.luismiguelsoto.flayer.activities.main.SongsListScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity( new Intent(SplashScreen.this, SongsListScreen.class) );
            finish();
        }, 600);
    }
}