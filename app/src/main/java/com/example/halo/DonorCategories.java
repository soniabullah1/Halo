package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DonorCategories extends AppCompatActivity {

    LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_categories);

        container = findViewById(R.id.layout);

        OkHttpClient client = new OkHttpClient(); //OkHttpClient singleton

        Intent c = getIntent();
        final String category = c.getStringExtra("Category");
        final String id = c.getStringExtra("UserId");

        CategoryDURL u = new CategoryDURL();
        String url = u.getCatUrlD(category);

        Request request = new Request.Builder()
                .url(url)
                .build();

        // Get a handler that can be used to post to the main thread
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String theResponse = response.body().string(); // Read data on worker thread

                    DonorCategories.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            processJSON(theResponse);
                        }
                    });
                }
            }
        });


        Button recip = findViewById(R.id.recipList);
        recip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rList = new Intent(DonorCategories.this, RecipientCategories.class);
                rList.putExtra("UserId", id);
                rList.putExtra("Category", category);
                startActivity(rList);
            }
        });

        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(DonorCategories.this, HomePage.class);
                home.putExtra("UserId", id);
                startActivity(home);
            }
        });
    }


    public void processJSON (String json){
        try {
            JSONArray jArr = new JSONArray(json);

            for (int i = 0; i < jArr.length(); i++){
                JSONObject item = jArr.getJSONObject(i);

                DonorsListLayout list = new DonorsListLayout(this);
                list.populate(item);
                if(i % 2 == 0){
                    list.setBackgroundColor(Color.parseColor("#FCEDFF"));
                }

                container.addView(list);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
