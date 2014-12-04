package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Joe's Desktop on 12/8/2014.
 */
public class OptionsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_activity);

        Button infoButton = (Button)findViewById(R.id.info_button);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO launch new activity for info
                Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(intent); // launches InfoActivity

            }
        });
    }
}
