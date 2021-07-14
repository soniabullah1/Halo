package com.example.halo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#FFFFFF"))
                .withHeaderText("Welcome To . . .")
                .withFooterText("Loading . . .")
                .withBeforeLogoText("HALO")
                .withAfterLogoText("Changing The World One Smile At a Time!")
                .withLogo(R.mipmap.logo); //all attributes for splash screen

        config.getHeaderTextView().setTextColor(Color.BLACK);//Change colours of Text, can do it for all text
        config.getHeaderTextView().setTextSize(25);
        config.getBeforeLogoTextView().setTextColor(Color.DKGRAY);
        config.getBeforeLogoTextView().setTextSize(35);
        config.getAfterLogoTextView().setTextColor(Color.DKGRAY);
        config.getAfterLogoTextView().setTextSize(18);
        config.getFooterTextView().setTextColor(Color.GRAY);

        View easySplash = config.create();
        setContentView(easySplash);
    }
}