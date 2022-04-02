package com.example.currencyconverterapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView current_rate;
    String rate_dollar;

    public class DownloadTask extends AsyncTask<String, Void, String>{

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
                rate_dollar = json.getString("rate");
                Log.i("Rate of Dollar", rate_dollar);
                current_rate.setText(rate_dollar+" L.L. / 1$");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String amount =  ""; //get the amount from the view
        String url = "http://192.168.138.1/CurrencyConverter/scrape.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);
        current_rate=(TextView) findViewById(R.id.current_rate);
    }
    public void start_exchange(View view){
        Intent intent = new Intent(getApplicationContext(), Calculator.class);
        intent.putExtra("dollar_rate", rate_dollar);
        startActivity(intent);


    }
}