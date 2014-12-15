/** The AsyncTask is responsible for authenticating the application
 *  As a registered developer of Twitter, I was able to receive a secret key and public key in order to access twitter's services
 *  This class makes it so we have a combined key in order to pass to twitter, then attempt to get tweets
 */
/*
package com.example.newtwitter;

import android.app.ListActivity;
import android.widget.ArrayAdapter;
import android.os.AsyncTask;
import android.widget.ListView;
import com.example.newtwitter.TwitterAPI;
import com.example.newtwitter.TwitterTweet;

import java.util.ArrayList;

public class TwitterAsyncTask extends AsyncTask<Object, Void, ArrayList<TwitterTweet>> {
    ListActivity callerActivity;

    final static String TWITTER_API_KEY = "p5vt7nNDukiCW6td807l81V9c";
    final static String TWITTER_API_SECRET = "ioYcVmgaBcjn88TsmzV6Aj3AVvddFP5ITSF5BgbgFFW2b01gSj";

    @Override
    protected ArrayList<TwitterTweet> doInBackground(Object... params) {
        ArrayList<TwitterTweet> twitterTweets = null;
        callerActivity = (ListActivity) params[1];
        if (params.length > 0) {
            TwitterAPI twitterAPI = new TwitterAPI(TWITTER_API_KEY,TWITTER_API_SECRET);
            twitterTweets = twitterAPI.getTwitterTweets(params[0].toString());
        }
        return twitterTweets;
    }

    @Override
    protected void onPostExecute(ArrayList<TwitterTweet> twitterTweets) {
        ArrayAdapter<TwitterTweet> adapter =
                new ArrayAdapter<TwitterTweet>(callerActivity, R.layout.activity_main,
                        R.id.listTextView, twitterTweets);
        callerActivity.setListAdapter(adapter);
        ListView lv = callerActivity.getListView();
        lv.setDividerHeight(0);
        //lv.setDivider(this.getResources().getDrawable(android.R.color.transparent));
        lv.setBackgroundColor(callerActivity.getResources().getColor(R.color.Twitter_blue));
    }
}*/