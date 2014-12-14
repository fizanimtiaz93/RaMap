package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import flickr.FlickrActivity;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class OptionsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        Bundle bundle = getIntent().getExtras(); // Creates a Bundle from intent and gets extras
        String message = bundle.getString("TO_OPTIONS"); //Looks for

        TextView txtView = (TextView) findViewById(R.id.textView);
        txtView.setText(message);

        // Info Button
        Button infoButton = (Button)findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch new activity for info
                Intent info = new Intent(OptionsActivity.this, InfoActivity.class);
                info.putExtra("TO_OPTIONS", getTitle());
                startActivity(info);

            }
        });

        // Pics Button
        Button picsButton = (Button)findViewById(R.id.pics_button);
        picsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch new activity for info
                Intent intent = new Intent(getApplicationContext(), FlickrActivity.class);
                startActivity(intent); // launches InfoActivity

            }
        });


    }
}
