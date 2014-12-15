/** Programmed by Alex Despotakis
 * Final Project for Mobile Programming
 * Objective is to get Fordham's tweets from Twitter and display them on the screen
 */

/*
package com.example.newtwitter;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.newtwitter.AndroidNetworkUtility;

public class MainActivity extends ListActivity {

    final static String twitterScreenName = "fordhamnotes";
    final static String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidNetworkUtility androidNetworkUtility = new AndroidNetworkUtility();
        if (androidNetworkUtility.isConnected(this)) {
            new TwitterAsyncTask().execute(twitterScreenName,this);
        } else {
            Log.v(TAG, "Network not Available!");
        }
    }
}*/