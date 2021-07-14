package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SuccessfulDonation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_donation);

        Button donateAgain = (Button) findViewById(R.id.anotherDonation);
        donateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent success = getIntent();
                String donorId = success.getStringExtra("UserId");

                Intent repeat = new Intent(SuccessfulDonation.this, DonationProcess.class);
                repeat.putExtra("UserId", donorId);
                startActivity(repeat);
            }
        });

        Button home = (Button) findViewById(R.id.homePage);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backHome = new Intent(SuccessfulDonation.this, HomePage.class);
                startActivity(backHome);
            }
        });
    }
}
