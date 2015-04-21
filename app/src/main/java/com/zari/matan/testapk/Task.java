package com.zari.matan.testapk;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import adapters.MyArrayAdapter;
import tabs.SlidingTabLayout;

import static com.zari.matan.testapk.R.layout.activity_main;

/**
 * Created by Matan on 4/20/2015.
 */
public class Task extends AsyncTask<String,Void,String> {
    private Activity activity;
    public Task(Activity activity) {
        this.activity = activity;
    }
    private DoIt listener;

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection connection;
        StringBuilder builder;
        BufferedReader reader;
        String result;


        try {
            url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            builder = new StringBuilder();
            String line;
            while ((line = reader.readLine())!= null){
                builder.append(line+"\n");
            }

            result = builder.toString();
        } catch (Exception e) {
            //e.printStackTrace();
            Toast.makeText(activity, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            result = null;
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result) {
        Log.e("postexecute", result + "");
        if (result != null){
                if (activity instanceof  DoIt){
                    listener = (DoIt) activity;
                    listener.send(result);
                }
        }
    }

    public interface DoIt{
        public void send(String result);
    }
}
