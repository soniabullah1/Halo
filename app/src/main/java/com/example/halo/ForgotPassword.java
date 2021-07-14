package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(login);
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(login);
            }
        });

        Button confirm = (Button) findViewById(R.id.checker);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView result = (TextView) findViewById(R.id.display);
                EditText password = (EditText) findViewById(R.id.password);
                EditText confirmP = (EditText) findViewById(R.id.confirm);

                String pass1 = password.getText().toString();
                String pass2 = confirmP.getText().toString();

                if(pass1.equals(pass2)){
                    OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

                    HttpUrl.Builder urlBuilder = HttpUrl
                            .parse("https://lamp.ms.wits.ac.za/home/s1853416/updatePassword.php")
                            .newBuilder();

                    EditText username = (EditText) findViewById(R.id.username);
                    String user = username.getText().toString();

                    String topSecret = pass1 + "H?0e3#";

                    urlBuilder.addQueryParameter("Username", user);
                    urlBuilder.addQueryParameter("Password", topSecret);

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

                                ForgotPassword.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(theResponse);
                                    }
                                });
                            }
                        }
                    });
                }

                else if(pass1.isEmpty() || pass2.isEmpty()){
                    result.setText("Please Type in your Username and New Password.");
                }

                else{
                    result.setText("Your request was Unsuccessful! Please make sure you have typed in the correct Username and Password.");
                }
            }
        });
    }
}
