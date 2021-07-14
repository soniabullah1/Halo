package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button back = findViewById(R.id.back);
        TextView privacy = findViewById(R.id.textView68);
        TextView about = findViewById(R.id.textView69);
        TextView howToUse = findViewById(R.id.textView70);
        TextView help = findViewById(R.id.textView71);

        Intent settings = getIntent();
        final String id = settings.getStringExtra("UserId");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Settings.this, HomePage.class);
                back.putExtra("UserId", id);
                startActivity(back);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacy = new Intent(Settings.this, PrivacyPolicy.class);
                privacy.putExtra("UserId", id);
                startActivity(privacy);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(Settings.this, AboutUs.class);
                about.putExtra("UserId", id);
                startActivity(about);
            }
        });

        howToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent usage = new Intent(Settings.this, HowToUse.class);
                usage.putExtra("UserId", id);
                startActivity(usage);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(Settings.this, GetHelp.class);
                help.putExtra("UserId", id);
                startActivity(help);
            }
        });
    }
}
