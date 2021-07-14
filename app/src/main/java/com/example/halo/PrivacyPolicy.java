package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrivacyPolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        Button back = findViewById(R.id.button3);
        Intent privacy = getIntent();
        final String id = privacy.getStringExtra("UserId");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receive = new Intent(PrivacyPolicy.this, Settings.class);
                receive.putExtra("UserId", id);
                startActivity(receive);
            }
        });
    }
}
