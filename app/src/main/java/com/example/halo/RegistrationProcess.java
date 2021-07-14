package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

public class RegistrationProcess extends AppCompatActivity {

    public RadioGroup radioGroup;
    TextView choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_process);

        //Radio button
        choice = (TextView) findViewById(R.id.selected);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView choice = (TextView) findViewById(R.id.selected);
                switch(checkedId){
                    case R.id.donator:
                        Toast.makeText(getApplicationContext(), "You Selected Donator!", Toast.LENGTH_SHORT).show();
                        choice.setText("You have selected Donator.");
                        break;

                    case R.id.recipient:
                        Toast.makeText(getApplicationContext(), "You Selected Recipient!", Toast.LENGTH_SHORT).show();
                        choice.setText("You have selected Recipient.");
                        break;

                    default:
                        choice.setText("Please make a selection.");
                }
            }
        });

        //Reset Selection (Donator/ Recipient cleared)
        Button resetBtn = (Button) findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
            }
        });


        //Register and create an account
        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

                HttpUrl.Builder urlBuilder = HttpUrl
                        .parse("https://lamp.ms.wits.ac.za/home/s1853416/register.php")
                        .newBuilder();

                Intent register = getIntent();
                String id = register.getStringExtra("UserId");

                EditText firstName = (EditText) findViewById(R.id.fname);
                String first = firstName.getText().toString();

                EditText lName = (EditText) findViewById(R.id.lname);
                String last = lName.getText().toString();                  //gets last name from user input

                EditText dob = (EditText) findViewById(R.id.dob);
                String age = dob.getText().toString();                    //gets age from user input

                EditText resAddress = (EditText) findViewById(R.id.address);
                String address = resAddress.getText().toString();

                EditText suburb = (EditText) findViewById(R.id.suburb);
                String area = suburb.getText().toString();

                EditText contactNum1 = (EditText) findViewById(R.id.contactNum);
                String num1 = contactNum1.getText().toString();

                EditText contactNum2 = (EditText) findViewById(R.id.secondContactNum);
                String num2 = contactNum2.getText().toString();

                EditText email = (EditText) findViewById(R.id.email);
                String emailAd = email.getText().toString();

                int radioId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(radioId);
                String selected = radioButton.getText().toString();

                urlBuilder.addQueryParameter("Id", id);
                urlBuilder.addQueryParameter("firstName", first);
                urlBuilder.addQueryParameter("lastName", last);
                urlBuilder.addQueryParameter("DOB", age);
                urlBuilder.addQueryParameter("Address", address);
                urlBuilder.addQueryParameter("Suburb", area);
                urlBuilder.addQueryParameter("Contact1", num1);
                urlBuilder.addQueryParameter("Contact2", num2);
                urlBuilder.addQueryParameter("Email", emailAd);
                urlBuilder.addQueryParameter("Group", selected);

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
                            final String theResponse = response.body().string();  //what you get back

                            RegistrationProcess.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final TextView myResult = (TextView) findViewById(R.id.display);
                                    myResult.setText(theResponse);

                                    if(theResponse.equals("Successful Registration!")){
                                        TextView success  = (TextView) findViewById(R.id.success);
                                        success.setText("Your User Profile is now Complete! \n Thank you for choosing Halo!");
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

       //Takes you to the Home Page
        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = getIntent();
                String userId = register.getStringExtra("UserId");

                Intent mainScreen = new Intent(RegistrationProcess.this, HomePage.class);
                mainScreen.putExtra("UserId", userId);
                startActivity(mainScreen);
            }
        });
    }
}