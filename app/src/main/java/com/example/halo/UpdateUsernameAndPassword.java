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

public class UpdateUsernameAndPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_username_and_password);

        Intent dropDown = getIntent();
        final String id = dropDown.getStringExtra("UserId");

        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(UpdateUsernameAndPassword.this, HomePage.class);
                home.putExtra("UserId", id);
                startActivity(home);
            }
        });

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(UpdateUsernameAndPassword.this, HomePage.class);
                back.putExtra("UserId", id);
                startActivity(back);
            }
        });

        Button confirm = (Button) findViewById(R.id.checker);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView result = (TextView) findViewById(R.id.display);
                EditText password = (EditText) findViewById(R.id.password);
                EditText confirmP = (EditText) findViewById(R.id.confirm);
                EditText username = (EditText) findViewById(R.id.username);

                final String pass1 = password.getText().toString();
                final String pass2 = confirmP.getText().toString();
                final String user = username.getText().toString();

                if(pass1.isEmpty() || pass2.isEmpty() || user.isEmpty()){
                    result.setText("Please Type in your New Username and Password.");
                }

                if(pass1.equals(pass2) && !pass1.isEmpty() && !pass2.isEmpty()){
                    OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

                    HttpUrl.Builder urlBuilder = HttpUrl
                            .parse("https://lamp.ms.wits.ac.za/home/s1853416/updateUaP.php")
                            .newBuilder();

                    Intent update = getIntent();
                    String id = update.getStringExtra("UserId");

                    String topSecret = pass1 + "H?0e3#";

                    urlBuilder.addQueryParameter("id", id);
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

                                UpdateUsernameAndPassword.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        result.setText(theResponse);
                                    }
                                });
                            }
                        }
                    });
                }

                else{
                    result.setText("Your request was Unsuccessful! Please make sure you have correctly typed in your New Username and Password.");
                }
            }
        });
    }
}