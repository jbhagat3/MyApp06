package com.example.jaishri.newapps;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView=null;
    ProgressBar visibility = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.text);
        visibility = (ProgressBar) findViewById(R.id.ProgressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.search,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FetchTask ft1=new FetchTask();
        ft1.execute();
        return super.onOptionsItemSelected(item);
    }

    public class FetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            visibility.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            URL apiRequestUrl = NetworkUtils.buildUrl();
            Log.d("MainActivity", apiRequestUrl.toString());

            try {
               return NetworkUtils
                        .getResponseFromHttpUrl(apiRequestUrl);


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(String data) {
            visibility.setVisibility(View.GONE);
            textView.setText(data);
        }


    }


}

