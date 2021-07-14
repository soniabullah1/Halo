package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_help);

        Button back = findViewById(R.id.button7);
        Intent help = getIntent();
        final String id = help.getStringExtra("UserId");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(GetHelp.this, Settings.class);
                help.putExtra("UserId", id);
                startActivity(help);
            }
        });

    }
}
