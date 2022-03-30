package com.example.currencyconverterapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Calculator extends AppCompatActivity{
    Intent x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        Intent y = getIntent();
        String dollar_current_rate = y.getStringExtra("dollar_rate");
    }
}