package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class HistoryActivity extends Activity {
    // Array of strings...
    String[] checkInArray = {"JMH", "FMH", "KEATING", "MAIN GATE"};

    private static PrefsActivity _appPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        _appPrefs = new PrefsActivity(getApplicationContext());

        _appPrefs.get();

        // Shared Prefs
       // MainActivity.sharedPreferences = getSharedPreferences(MainActivity.preName, MODE_PRIVATE);

        // Store shared preferences
       // SharedPreferences.Editor editor = MainActivity.sharedPreferences.
       // var testName = editor  .  getString("Name");

       //System.out.print( testName );


        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, checkInArray);

        ListView listView = (ListView) findViewById(R.id.building_list);
        listView.setAdapter(adapter);
    }
}
