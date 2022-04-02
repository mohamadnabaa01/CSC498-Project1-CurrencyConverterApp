package com.example.currencyconverterapp;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    String amount_from_calc;
    String currency_type;
    String amount_result;
    TextView converted_amount;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... urls){
            String result = "";
            URL url;
            HttpURLConnection http;

            try{
                url = new URL(urls[0]);
                http = (HttpURLConnection) url.openConnection();

                InputStream in = http.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while(data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
            return result;
        }

        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try{
                JSONObject json = new JSONObject(s);
                amount_result = json.getString("result");
                Log.i("Result",amount_result);
                converted_amount.setText(amount_result);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);
        Intent y = getIntent();
        dollar_current_rate = y.getStringExtra("dollar_rate");
        TextView dollar_text = (TextView) findViewById(R.id.current_rate_text);
        dollar_text.setText(dollar_current_rate + " L.L.");
        converted_amount = (TextView) findViewById(R.id.converted_amount);

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
        String url2="http://172.20.10.7:8080/CurrencyConverter/calculator.php?amount="+amount_to_convert+"&currency_type="+currency_convert+"&current_rate="+dollar_current_rate;
        DownloadTask task = new DownloadTask();
        task.execute(url2);

    }

    public void Reset(View view) {
        EditText amount_convert = (EditText) findViewById(R.id.amount_to_convert);
        amount_convert.setText("اي مبلغ متاح فقط");
        TextView other_currency_statement = (TextView) findViewById(R.id.other_currency_stmt);
        other_currency_statement.setText("");
        converted_amount.setText("");
        Toast.makeText(getApplicationContext(), "لقد تم تكرار الصفحة كاملة", Toast.LENGTH_LONG).show();
    }

    public void put_amount(View v) {
        EditText amount = (EditText) v;
        amount.setText("");
    }
}