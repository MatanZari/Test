package com.zari.matan.testapk;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapters.GifArrayAdapter;
import adapters.MyArrayAdapter;
import adapters.MyPagerAdapter;
import fragments.HomeFragment;
import fragments.NotificationsFragment;
import tabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity implements Task.DoIt, HomeFragment.CallActivity {
    Toolbar toolbar;
    View v;
    ViewPager pager;
    SlidingTabLayout tabs;
    MyPagerAdapter adapter;
    ListView listView;
    private int[] imageResId = {
            R.drawable.home_tab_icon,
            R.drawable.notification_tab_icon,
            R.drawable.search_tab_icon,
            R.drawable.favorite_tab_icon
    };
    int numbOfTabs = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new MyPagerAdapter(this, getSupportFragmentManager(), imageResId, numbOfTabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        tabs.setCustomTabView(R.layout.custom_tab, R.id.textPageTitle);

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);


    }


    @Override
    public void send(String result) {
        ArrayList<Item> strings;
        try {
            strings = new ArrayList<>();
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject object = (JSONObject) jsonArray.get(i);
                if (object.getString("type").equals("video")){
                    strings.add(new Item(object.getString("thumbnail"),object.getString("videoUrl")));

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            strings = null;
        }
        if (strings != null){
            MyArrayAdapter adapter1 = new MyArrayAdapter(this,R.layout.web_video_layout,strings);
            listView.setAdapter(adapter1);
        }
    }


    @Override
    public void getLayout(View v) {
        listView = (ListView) v.findViewById(R.id.feed_list);
        Task task = new Task(this);
        task.execute("http://wolflo.com/walls/system/vine");
    }
}
