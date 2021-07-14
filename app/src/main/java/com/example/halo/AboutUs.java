package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Button back = findViewById(R.id.button4);
        Intent about = getIntent();
        final String id = about.getStringExtra("UserId");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receive = new Intent(AboutUs.this, Settings.class);
                receive.putExtra("UserId", id);
                startActivity(receive);
            }
        });
    }
}
