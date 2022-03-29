package com.example.currencyconverterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
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
                while( data != -1){
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
            return result;
        }
        protected void onPostExecute(String s){
//            Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
            try{
                JSONObject json = new JSONObject(s);
                String rate = json.getString("rate");
                Log.i("Rate", rate);


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
        String url = "http://localhost:8080/CurrencyConverter/scrape.php";
        DownloadTask task = new DownloadTask();
        task.execute(url);
    }
}