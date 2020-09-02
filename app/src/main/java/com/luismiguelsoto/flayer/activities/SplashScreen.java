package com.luismiguelsoto.flayer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.luismiguelsoto.flayer.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            startActivity( new Intent(SplashScreen.this, MainScreen.class).addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) );
            finish();
        }, 600);
    }
}