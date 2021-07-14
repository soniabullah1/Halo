package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RecipientProcess extends AppCompatActivity {

    private ArrayList<DonateItem> DList;
    private DonateAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipient_process);

        Intent need = getIntent();
        final String id = need.getStringExtra("UserId");

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(RecipientProcess.this, HomePage.class);
                home.putExtra("UserId", id);
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
                DonateItem clickedItem = (DonateItem) parent.getItemAtPosition(position);
                String clickedCat = clickedItem.getCatName();
                Toast.makeText(RecipientProcess.this, clickedCat + " selected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        Button confirm = findViewById(R.id.confirmRequest);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient client = new OkHttpClient();

                Spinner spinner = findViewById(R.id.spinner);
                DonateItem clickedItem = (DonateItem) spinner.getSelectedItem();
                final String category = clickedItem.getCatName();

                RecipientURL u = new RecipientURL();
                String url = u.getUrl(category);

                HttpUrl.Builder urlBuilder = HttpUrl
                        .parse(url)
                        .newBuilder();

                Intent receive = getIntent();
                final String idNum = receive.getStringExtra("UserId");

                EditText item = findViewById(R.id.ItemName);
                String itemName = item.getText().toString();

                EditText itemNum = findViewById(R.id.NumItems);
                String num = itemNum.getText().toString();

                EditText desc = findViewById(R.id.ItemDes);
                String description = desc.getText().toString();

                EditText phoneNo = findViewById(R.id.PhoneNum);
                String phone = phoneNo.getText().toString();

                EditText motive = findViewById(R.id.motivation);
                String motivation = motive.getText().toString();

                urlBuilder.addQueryParameter("id", idNum);
                urlBuilder.addQueryParameter("phone", phone);
                urlBuilder.addQueryParameter("name", itemName);
                urlBuilder.addQueryParameter("quantity", num);
                urlBuilder.addQueryParameter("description", description);
                urlBuilder.addQueryParameter("category", category);
                urlBuilder.addQueryParameter("motivation", motivation);

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

                            RecipientProcess.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final TextView myResult = findViewById(R.id.display);
                                    myResult.setText(theResponse);

                                    Intent receipt = new Intent(RecipientProcess.this, SuccessfulReceipt.class);
                                    receipt.putExtra("UserId", idNum);
                                    startActivity(receipt);
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