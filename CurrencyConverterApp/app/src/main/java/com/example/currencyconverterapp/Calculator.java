package com.example.currencyconverterapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Calculator extends AppCompatActivity {

    ListView currency_list;
    ArrayList<String> the_currency_list;
    ArrayAdapter<String> adapter;
    String currency_convert;
    String dollar_current_rate;
    String amount_to_convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        Intent y = getIntent();
        dollar_current_rate = y.getStringExtra("dollar_rate");
        TextView dollar_text = (TextView) findViewById(R.id.current_rate_text);
        dollar_text.setText(dollar_current_rate + " L.L.");

        currency_list = (ListView) findViewById(R.id.currency_list);
        the_currency_list = new ArrayList<String>(Arrays.asList("الليرة اللبنانية", "دولار امريكي"));
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, the_currency_list);
        currency_list.setAdapter(adapter);

        currency_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        currency_convert = "L.L.";
                        break;
                    case 1:
                        currency_convert = "USD";
                        break;
                }
            }
        });
    }

    public void convert_amount(View view) {
        EditText amount_convert = (EditText) findViewById(R.id.amount_to_convert);
        amount_to_convert = amount_convert.getText().toString();
        TextView other_currency_statement = (TextView) findViewById(R.id.other_currency_stmt);
        if (currency_convert.equalsIgnoreCase("USD"))
            other_currency_statement.setText("المبلغ بالليرة اللبنانية هو:");
        if (currency_convert.equalsIgnoreCase("L.L."))
            other_currency_statement.setText("المبلغ بالدولار هو:");

        //intent sending the amount to the main activity
        Intent intent_amount = new Intent(getApplicationContext(), MainActivity.class);
        intent_amount.putExtra("amount_to_convert",amount_to_convert);
        intent_amount.putExtra("currency_type",currency_convert);//sending whether it is usd or lbp
    }

    public void Reset(View view) {
        EditText amount_convert = (EditText) findViewById(R.id.amount_to_convert);
        amount_convert.setText("اي مبلغ متاح فقط");
        TextView other_currency_statement = (TextView) findViewById(R.id.other_currency_stmt);
        other_currency_statement.setText("");
        TextView converted_amount = (TextView) findViewById(R.id.converted_amount);
        converted_amount.setText("");
        Toast.makeText(getApplicationContext(), "لقد تم تكرار الصفحة كاملة", Toast.LENGTH_LONG).show();
    }

    public void put_amount(View v) {
        EditText amount = (EditText) v;
        amount.setText("");
    }
}