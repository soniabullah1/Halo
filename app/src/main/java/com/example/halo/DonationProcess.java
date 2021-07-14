package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;

import java.io.IOException;
import java.util.ArrayList;

import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DonationProcess extends AppCompatActivity {

    private ArrayList<DonateItem> DList;
    private DonateAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_process);

        Intent donate = getIntent();
        final String userId = donate.getStringExtra("UserId");

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DonationProcess.this, HomePage.class);
                home.putExtra("UserId", userId);
                startActivity(home);
            }
        });

        myList();
        Spinner spinnerCategories = findViewById(R.id.spinner);
        adapter1 = new DonateAdapter(this, DList);
        spinnerCategories.setAdapter(adapter1);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position >0) {
                    DonateItem clickedItem = (DonateItem) parent.getItemAtPosition(position);
                    String clickedCat = clickedItem.getCatName();
                    Toast.makeText(DonationProcess.this, clickedCat + " selected", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button confirm = findViewById(R.id.confrimBtn);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();

                Spinner spinner = findViewById(R.id.spinner);
                DonateItem clickedItem = (DonateItem) spinner.getSelectedItem();
                final String category = clickedItem.getCatName();

                DonorURL u = new DonorURL();
                String url = u.getUrl(category);

                HttpUrl.Builder urlBuilder = HttpUrl
                        .parse(url)
                        .newBuilder();


                EditText item = findViewById(R.id.ItemName);
                final String name = item.getText().toString();

                EditText desc = findViewById(R.id.itemDescrip);
                String description = desc.getText().toString();

                EditText num = findViewById(R.id.NumItems);
                final String quantity = num.getText().toString();

                urlBuilder.addQueryParameter("id", userId);
                urlBuilder.addQueryParameter("name", name);
                urlBuilder.addQueryParameter("quantity", quantity);
                urlBuilder.addQueryParameter("description", description);

                String newUrl = urlBuilder.build().toString();

                Request request = new Request.Builder()
                        .url(newUrl)
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

                            DonationProcess.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final TextView myResult = findViewById(R.id.display);
                                    myResult.setText(theResponse);

                                    Intent donorsChoice = new Intent(DonationProcess.this, DonorsChoice.class);
                                    donorsChoice.putExtra("itemName",name);
                                    donorsChoice.putExtra("UserId", userId);
                                    donorsChoice.putExtra("numItems", quantity);
                                    donorsChoice.putExtra("category", category);
                                    startActivity(donorsChoice);
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void myList() {
        DList = new ArrayList<>();
        DList.add(new DonateItem("SELECT THE CATEGORY HERE:"));
        DList.add(new DonateItem("FOOD"));
        DList.add(new DonateItem("CLOTHING"));
        DList.add(new DonateItem("SANITARY & HYGIENE"));
        DList.add(new DonateItem("STATIONERY"));
        DList.add(new DonateItem("ANIMAL CARE"));
        DList.add(new DonateItem("OTHER"));
    }
}