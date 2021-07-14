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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registration = new Intent(MainActivity.this,SetPassword.class);
                startActivity(registration);
            }
        });

        TextView forgot = findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forgotPassword = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(forgotPassword);
            }
        });


        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final TextView myResult = (TextView) findViewById(R.id.display);

        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isEmpty(username) || isEmpty(password)){
                    myResult.setText("Please provide your Username and Password.");
                }

                else{
                    OkHttpClient client = new OkHttpClient();

                    HttpUrl.Builder urlBuilder = HttpUrl
                            .parse("https://lamp.ms.wits.ac.za/home/s1853416/verify.php")
                            .newBuilder();

                    final String user = username.getText().toString();
                    String pass = password.getText().toString();

                    String topSecret = pass + "H?0e3#";

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

                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        UserID person = new UserID();
                                        String message = person.getTheString(theResponse);
                                        myResult.setText(message);

                                        if(message.equals("Password Valid!")){
                                            String id = person.getUserId(theResponse);

                                            Intent mainScreen = new Intent(MainActivity.this, HomePage.class);
                                            mainScreen.putExtra("UserId", id);
                                            startActivity(mainScreen);
                                        }

                                        if(message.equals("<br />")){
                                            TextView display = (TextView) findViewById(R.id.display);
                                            display.setText("Your Username or Password is Incorrect. Please Try Again.");
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}