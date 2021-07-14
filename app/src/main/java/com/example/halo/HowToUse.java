package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HowToUse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_use);

        Intent howToUse = getIntent();
        final String id = howToUse.getStringExtra("UserId");

        Button back = findViewById(R.id.button6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(HowToUse.this, Settings.class);
                help.putExtra("UserId", id);
                startActivity(help);
            }
        });
    }
}
