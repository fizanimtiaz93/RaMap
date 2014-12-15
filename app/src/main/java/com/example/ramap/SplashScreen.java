package com.example.ramap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Joe's Desktop on 12/14/2014.
 */
public class SplashScreen extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            //
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class); //setup intent to transition from SplashScreen to MainActivity
                startActivity(i); //start MainActivity

                finish(); //end current activity
            }
        }, SPLASH_TIME_OUT);
    }


}
