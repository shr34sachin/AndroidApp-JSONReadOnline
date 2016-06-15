package com.sachin.sachinshrestha.jsonreadonline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button btnLoad;
    TextView tvDisplayContent;
    private String uri = "http://sachinshrestha84.netne.net/fooditems.json";

    List<FoodItems> foodItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplayContent = (TextView)findViewById(R.id.tvDisplayContent);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        tvDisplayContent.setMovementMethod(new ScrollingMovementMethod());

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (isOnline()){
                    requestData(uri);
                } else{
                    Toast.makeText(getApplicationContext(),"Network isn't available", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void displayInTextView(){
        if(foodItems !=null){
            for(FoodItems FoodItems:foodItems){
                tvDisplayContent.append(FoodItems.getProductId()+"\n");
                tvDisplayContent.append(FoodItems.getCategory()+"\n");
                tvDisplayContent.append(FoodItems.getName()+"\n");
                tvDisplayContent.append(FoodItems.getPrice()+"\n\n");
            }
            Toast.makeText(getApplicationContext(),"Data extracted from " + uri,Toast.LENGTH_LONG).show();
        }
    }

    // check whether the network is availed or not
    // Note that the permissions, ACCESS_NETWORK_STATE and INTERNET should be set first in manifest file
    private boolean isOnline(){
        ConnectivityManager cm= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        } else{
            return false;
        }
    }

    private void requestData(String uri) {
        asyncTasks tasks = new asyncTasks();
        tasks.execute(uri);
    }

    private class asyncTasks extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {
            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String stringFromDoInBckGnd) {

            foodItems = JSONParser.parseFeed(stringFromDoInBckGnd);
            displayInTextView();
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
