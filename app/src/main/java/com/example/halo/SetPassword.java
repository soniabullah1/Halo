package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        //Go back to the login screen
        Switch login = (Switch) findViewById(R.id.switcher);
        login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    Intent loginActivity = new Intent(SetPassword.this, MainActivity.class);
                    startActivity(loginActivity);
                }
            }
        });

        //Creating a Username and Password
        Button confirm = (Button) findViewById(R.id.checker);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TextView result = (TextView) findViewById(R.id.display);
                EditText uName = (EditText) findViewById(R.id.username);
                EditText password = (EditText) findViewById(R.id.password);
                EditText confirmP = (EditText) findViewById(R.id.confirm);

                final String username = uName.getText().toString();
                final String pass1 = password.getText().toString();
                final String pass2 = confirmP.getText().toString();

                if(pass1.isEmpty() || pass2.isEmpty() || username.isEmpty()){
                    result.setText("Please create a Username and Password.");
                }

                else if(pass1.equals(pass2)){
                    result.setText("Username and Password was Successfully Created!");

                    OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

                    HttpUrl.Builder urlBuilder = HttpUrl
                            .parse("https://lamp.ms.wits.ac.za/home/s1853416/user.php")
                            .newBuilder();

                    String topSecret = pass1 + "H?0e3#";

                    urlBuilder.addQueryParameter("Username", username);
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

                                SetPassword.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        TextView result = (TextView) findViewById(R.id.display);
                                        result.setText(theResponse);
                                    }
                                });
                            }
                        }
                    });
                }

                else{
                    result.setText("Password and Username Creation Unsuccessful! Please make sure you have typed in your password correctly.");
                }
            }
        });


        //Get associated User_Id for the next part of the registration process.
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

                HttpUrl.Builder urlBuilder = HttpUrl
                        .parse("https://lamp.ms.wits.ac.za/home/s1853416/getUserId.php")
                        .newBuilder();

                EditText username = (EditText) findViewById(R.id.username);
                final String user = username.getText().toString();

                urlBuilder.addQueryParameter("Username", user);
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

                            SetPassword.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Intent register = new Intent(SetPassword.this, RegistrationProcess.class);
                                    register.putExtra("UserId",theResponse);
                                    startActivity(register);
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}