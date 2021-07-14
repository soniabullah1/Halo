package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfulReceipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_receipt);

        Button repeat = (Button) findViewById(R.id.anotherRequest);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receipt = getIntent();
                String id = receipt.getStringExtra("UserId");

                Intent again = new Intent(SuccessfulReceipt.this, RecipientProcess.class);
                again.putExtra("UserId", id);
                startActivity(again);
            }
        });

        Button home = (Button) findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(SuccessfulReceipt.this, HomePage.class);
                startActivity(backHome);
            }
        });
    }
}
