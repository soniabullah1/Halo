package com.example.halo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomePage extends AppCompatActivity {

    //this is to check if they're a donor or not
    private boolean blnCheckDonor=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent homePage = getIntent();
        final String id = homePage.getStringExtra("UserId");

        final Button donate = (Button) findViewById(R.id.donate);
        final Button receive = (Button) findViewById(R.id.receive);

        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl
                .parse("https://lamp.ms.wits.ac.za/home/s1853416/checkUser2.php")
                .newBuilder();

        urlBuilder.addQueryParameter("UserId", id);
        String newUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(newUrl)
                .build();

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) { e.printStackTrace();}
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                //checking for failure using isSuccessful before proceeding
                if (response.isSuccessful()) {
                    final String theResponse = response.body().string().trim();

                    if(theResponse.equals("True")){
                        setblnCheckDonor(true);
                    }
                    else if(theResponse.equals("False")){
                        setblnCheckDonor(false);
                    }
                }
                countDownLatch.countDown();
            }
        });

        try{
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if (blnCheckDonor){
            receive.setEnabled(false);
        }
        else{
            donate.setEnabled(false);
        }


        //Code for Each Category
        final TextView food = findViewById(R.id.textView3);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Food";

                Intent food = new Intent(HomePage.this, DonorCategories.class);
                food.putExtra("UserId", id);
                food.putExtra("Category", category);
                startActivity(food);
            }
        });

        final TextView clothing = findViewById(R.id.textView4);
        clothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Clothing";

                Intent clothing = new Intent(HomePage.this, DonorCategories.class);
                clothing.putExtra("UserId", id);
                clothing.putExtra("Category", category);
                startActivity(clothing);
            }
        });

        final TextView sanitary = findViewById(R.id.textView6);
        sanitary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Sanitary";

                Intent sanitary = new Intent(HomePage.this, DonorCategories.class);
                sanitary.putExtra("UserId", id);
                sanitary.putExtra("Category", category);
                startActivity(sanitary);
            }
        });

        final TextView stationery = findViewById(R.id.textView7);
        stationery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Stationery";

                Intent stationery = new Intent(HomePage.this, DonorCategories.class);
                stationery.putExtra("UserId", id);
                stationery.putExtra("Category", category);
                startActivity(stationery);
            }
        });

        final TextView animal = findViewById(R.id.textView12);
        animal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Animals";

                Intent animal = new Intent(HomePage.this, DonorCategories.class);
                animal.putExtra("UserId", id);
                animal.putExtra("Category", category);
                startActivity(animal);
            }
        });

        final TextView other = findViewById(R.id.textView11);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Other";

                Intent other = new Intent(HomePage.this, DonorCategories.class);
                other.putExtra("UserId", id);
                other.putExtra("Category", category);
                startActivity(other);
            }
        });

        //Make a Donation
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donate = new Intent(HomePage.this, DonationProcess.class);
                donate.putExtra("UserId", id);
                startActivity(donate);
            }
        });

        //Need a Donation?
        receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receive = new Intent(HomePage.this, RecipientProcess.class);
                receive.putExtra("UserId", id);
                startActivity(receive);
            }
        });
    }


    private void setblnCheckDonor(boolean b) {
        blnCheckDonor=b;
    }


    //Code for Drop Down Menu and Settings Page.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.halo_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent homePage = getIntent();
        final String id = homePage.getStringExtra("UserId");

        switch(item.getItemId()){
            case R.id.settings:
                Intent settings = new Intent(HomePage.this, Settings.class);
                settings.putExtra("UserId", id);
                startActivity(settings);
                return true;

            case R.id.drop1:
                Intent update1 = new Intent(HomePage.this, UpdateUsernameAndPassword.class);
                update1.putExtra("UserId", id);
                startActivity(update1);
                return true;

            case R.id.drop2:
                Intent delete = new Intent(HomePage.this, DeleteAccount.class);
                delete.putExtra("UserId", id);
                startActivity(delete);
                return true;

            case R.id.drop3:
                Intent logout = new Intent(HomePage.this, MainActivity.class);
                startActivity(logout);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}