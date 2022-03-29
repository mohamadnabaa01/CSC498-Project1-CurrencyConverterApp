package com.example.currencyconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String amount="";
        String url="http://localhost:8080/CurrencyConverter/scrape.php";

        DownloadTask task=new DownloadTask();
        task.execute(url);
    }

}