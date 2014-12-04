package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class HistoryActivity extends Activity {
    // Array of strings...
    String[] checkInArray = {"JMH", "FMH", "KEATING", "MAIN GATE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.activity_listview, checkInArray);

        ListView listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);
    }
}
