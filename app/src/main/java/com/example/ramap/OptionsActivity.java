package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import flickr.FlickrActivity;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class OptionsActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        // Info Button
        Button infoButton = (Button)findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch new activity for info
                Bundle extras = getIntent().getExtras();
                if(extras != null) {
                    String value = extras.getString("name");
                }
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                intent.putExtra("name", getTitle());
                startActivity(intent); // launches InfoActivity

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

        // Twitter Button
        /*Button twitterButton = (Button)findViewById(R.id.twitter_button);
        picsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch new activity for info
                Intent intent = new Intent(getApplicationContext(), TwitterActivity.class);
                startActivity(intent); // launches InfoActivity

            }
        });*/

    }
}
