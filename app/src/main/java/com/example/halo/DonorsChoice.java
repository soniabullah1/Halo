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

public class DonorsChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donors_choice);

        //DISPLAY LIST
        OkHttpClient client = new OkHttpClient();

        final Intent donorsChoice = getIntent();
        final String category = donorsChoice.getStringExtra("category");

        ListURL listUrl = new ListURL();
        String url = listUrl.getListUrl(category);

        HttpUrl.Builder urlBuilder = HttpUrl
                .parse(url)
                .newBuilder();


        final String itemName =  donorsChoice.getStringExtra("itemName");

        urlBuilder.addQueryParameter("Item", itemName);
        String newUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(newUrl)
                .build();

        // Get a handler that can be used to post to the main thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //checking for failure using isSuccessful before proceeding
                if (response.isSuccessful()) {
                    final String theResponse = response.body().string();

                    DonorsChoice.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            processJSON(theResponse);
                        }
                    });
                }
            }
        });


        Intent getNum = getIntent();
        final String num =  getNum.getStringExtra("numItems");
        final TextView quant = findViewById(R.id.quantity);
        quant.setText("You have " + num + " item/s that you can donate.");

        //Sending the recipient information the donation will go to so that the database can be updated
        Button submitDonation = (Button) findViewById(R.id.submitDonation);
        submitDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText quantity = (EditText) findViewById(R.id.numItems);
                final String numItems = quantity.getText().toString();
                final TextView display = (TextView) findViewById(R.id.display);

                int has = Integer.parseInt(num);
                int donate = Integer.parseInt(numItems);


                if(has < donate){
                    display.setText("Your donation request exceeds the number of items you have to donate. Please try again");
                }

                else{
                    has = has - donate;
                    final int newNum = has;

                    OkHttpClient client = new OkHttpClient();

                    UpdateRecipURL updateRecipURL = new UpdateRecipURL();
                    String url = updateRecipURL.updateUrl(category);

                    HttpUrl.Builder urlBuilder = HttpUrl
                            .parse(url)
                            .newBuilder();

                    EditText idNum = (EditText) findViewById(R.id.recipId);
                    String idR = idNum.getText().toString();

                    urlBuilder.addQueryParameter("ID", idR);
                    urlBuilder.addQueryParameter("Num", numItems);
                    String newUrl = urlBuilder.build().toString();

                    Request request = new Request.Builder()
                            .url(newUrl)
                            .build();

                    client.newCall(request).enqueue(new okhttp3.Callback() {
                        @Override
                        public void onFailure(okhttp3.Call call, IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(okhttp3.Call call, final Response response) throws IOException {
                            if(response.isSuccessful()){
                                final String theResponse = response.body().string();

                                DonorsChoice.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        display.setText(theResponse);
                                        quant.setText("You have " + newNum + " item/s that you can donate.");
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });


        Button next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String donorId = donorsChoice.getStringExtra("UserId");

                Intent success = new Intent(DonorsChoice.this, SuccessfulDonation.class);
                success.putExtra("UserId", donorId);
                startActivity(success);
            }
        });
    }

    public void processJSON (String json){
        try {
            JSONArray jArr = new JSONArray(json);

            for (int i = 0; i < jArr.length(); i++){
                JSONObject item = jArr.getJSONObject(i);
                String id = item.getString("USER_ID");
                String name = item.getString("ITEM_NAME");
                String quantity = item.getString("QUANTITY");
                String describe = item.getString("DESCRIPTION");
                String num = item.getString("CONTACT_NUM");
                String motivation = item.getString("MOTIVATION");

                //dynamically add TextView
                LinearLayout container = (LinearLayout) findViewById(R.id.layout);
                TextView tv = new TextView(this.getApplicationContext());
                tv.setText("User ID = " +id + ":\nx" + quantity + " " + name + " : " + describe +" \n" + "Motivation: " + motivation + "\n");
                container.addView(tv);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}