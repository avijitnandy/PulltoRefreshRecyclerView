package com.w3xplorers.pulltorefreshrecyclerview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ArrayList android_version_names = new ArrayList<>(Arrays.asList("Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow"
    ));

    //Image links
    ArrayList<String> android_image_urls = new ArrayList<>(Arrays.asList(
            "https://vignette2.wikia.nocookie.net/peridot0/images/2/28/Pink_frosted_sprinkled_donut.png",
            "http://vignette4.wikia.nocookie.net/logopedia/images/d/d8/Andorid-2.3-Gingerbread-logo.png",
            "http://www.pngmart.com/files/4/Android-PNG-Photos.png",
            "http://goodereader.com/blog/uploads/images/android-froyo.png",
            "https://vignette3.wikia.nocookie.net/logopedia/images/2/24/Android_honeycomb.png",
            "http://wpmedia.windsorstar.com/2011/12/android_ice_cream_sandwich.png",
            "http://www.gamefromscratch.com/image.axd?picture=jellybeen.png",
            "http://www.jivi.in/jsp29/Kitkat.png",
            "https://www.xda-developers.com/wp-content/uploads/2015/09/NBAYK8V.png",
            "http://iskin.tooliphone.net/themes/3010/2213/preview-256.png"
    ));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

    }

    private void initViews(){
        // init SwipeRefreshLayout and ListView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),android_version_names,android_image_urls);
        recyclerView.setAdapter(adapter);

        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                shuffleItems();
            }
        });

    }

//    private ArrayList prepareData(){
//        ArrayList android_version = new ArrayList<>();
//        for(int i=0;i<android_version_names.length;i++){
//            AndroidVersion androidVersion = new AndroidVersion();
//            androidVersion.setAndroid_version_name(android_version_names[i]);
//            androidVersion.setAndroid_image_url(android_image_urls[i]);
//            android_version.add(androidVersion);
//        }
//        return android_version;
//    }

    public void shuffleItems() {
        // shuffle the ArrayList's items and set the adapter
        Collections.shuffle(android_version_names, new Random(System.currentTimeMillis()));
        Collections.shuffle(android_image_urls, new Random(System.currentTimeMillis()));
        // call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this, android_version_names, android_image_urls);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }


}
