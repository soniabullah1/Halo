package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DeleteAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        Intent deleteAccount = getIntent();
        final String id = deleteAccount.getStringExtra("UserId");

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.yes:
                        Toast.makeText(getApplicationContext(), "Please Proceed with the Deletion Process.", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.no:
                        Intent no = new Intent(DeleteAccount.this, HomePage.class);
                        no.putExtra("UserId", id);
                        startActivity(no);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Please Make a Selection before Proceeding.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(DeleteAccount.this, HomePage.class);
                back.putExtra("UserId", id);
                startActivity(back);
            }
        });


        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goodbye = new Intent(DeleteAccount.this, SplashScreen.class);
                startActivity(goodbye);
            }
        });


        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();

                HttpUrl.Builder urlBuilder = HttpUrl
                        .parse("https://lamp.ms.wits.ac.za/home/s1853416/delete1.php")
                        .newBuilder();

                Intent delete = getIntent();
                String id = delete.getStringExtra("UserId");

                urlBuilder.addQueryParameter("UserId", id);
                String url = urlBuilder.build().toString();

                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String theResponse = response.body().string();

                            DeleteAccount.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView myResult = (TextView) findViewById(R.id.display);
                                    myResult.setText(theResponse);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}