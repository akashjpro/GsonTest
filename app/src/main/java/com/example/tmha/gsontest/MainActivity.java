package com.example.tmha.gsontest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new getData().execute("http://blog.teamtreehouse.com/api/get_recent_summary/");
            }
        });
    }

    public class getData extends AsyncTask<String, Void, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            List<Post> listPost = new ArrayList<>();

            try {
                URL url = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");


                if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                    InputStream input = connection.getInputStream();
                    if (input != null){
                        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                        String line = reader.readLine();
                        String json = "";
                        while (line != null){
                            json += line;
                            line = reader.readLine();
                        }
                        reader.close();

                        Gson gson = new Gson();
                        Blog blog = gson.fromJson(json, Blog.class);
                        Log.d("MainActivity", "signInWithCredential:onComplete:" + blog.status);
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
