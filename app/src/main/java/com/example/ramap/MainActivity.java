package com.example.ramap;
/*
 * Copyright (c) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.

 */

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.model.PolygonOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends FragmentActivity {
    private static final String LOG_TAG = "RaMap";

    private static final String SERVICE_URL = "http://nrdyninja.com/android/ramap/locations.json";

    protected GoogleMap map;

    //private static PrefsActivity _appPrefs;

    //public static SharedPreferences sharedPreferences;
    //public static String preName = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  // references layout/main.xml to the initial view
        setUpMapIfNeeded();             // sets up the MapView

        //_appPrefs = new PrefsActivity(getApplicationContext());
        // Shared Prefs
        // sharedPreferences = getSharedPreferences(preName, MODE_PRIVATE);

        // Used for finding current location with button
        // Will eventually pass current location into a value so that markers
        // are populated when they're 5m from current location.
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        /*final TextView answerLabel = (TextView) findViewById(R.id.checkInLocation);
        Button getCheckInButton = (Button) findViewById(R.id.checkInButton);

        getCheckInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                System.out.println("Output from onclick: "+ arg0); // Debug console output.
                String answer = "You're checked into Keating Hall."; //set InfoWindowClickListener name
                answerLabel.setText(answer);

                //String buildingName = "Keating Hall";

                //_appPrefs.save(buildingName);

                // Open shared preferences for storage
                //SharedPreferences.Editor editor = sharedPreferences.edit();

                // Save the data
                //editor.putString("Name", "Keating Hall");

                // Commit or Saves the value
               // editor.commit();

            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.main, menu); //uses menu/main.xml <item> to populate
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId())
        {
            //TODO this should open HistoryActivity.java
            case R.id.check_in_history_menu:
                Intent intent = new Intent (getApplicationContext(), HistoryActivity.class);
                startActivity(intent); // starts
                break;

            //TODO this should open settings activity
            case R.id.settings_menu:
                Toast.makeText(getBaseContext(), "Settings option doesn't work yet.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about_menu:
                Toast.makeText(getBaseContext(), "Created by: Joseph LeRoy, Kevin McCarthy, Alexander Despotakis, Fizan Imtiaz", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            if (map != null) {
                setUpMap();

                //TODO parse from JSON
                //Keating Hall
                map.addPolygon(new PolygonOptions()
                .add(new LatLng(40.86087017518951, -73.88396859169006),
                        new LatLng(40.86056994824036, -73.88344019651413),
                        new LatLng(40.86017843402579, -73.88381570577621),
                        new LatLng(40.86049286272303, -73.88437360525131))
                        .strokeColor(Color.BLACK)
                        .strokeWidth(4)
                        .fillColor(Color.GREEN));

                //Physics
                map.addPolygon(new PolygonOptions()
                        .add(new LatLng(40.86065109079324, -73.8857951760292),
                                new LatLng(40.86057197680538, -73.88562351465225),
                                new LatLng(40.86062877660109, -73.88557523488998),
                                new LatLng(40.860541548323354, -73.88539552688599),
                                new LatLng(40.86047866274954, -73.88544380664825),
                                new LatLng(40.8603934628446, -73.88526141643524),
                                new LatLng(40.86024740560974, -73.88539552688599),
                                new LatLng(40.86052329122742, -73.88590782880783)
                                )
                        .strokeColor(Color.BLACK)
                        .strokeWidth(4)
                        .fillColor(Color.GREEN));




                // allows info windows to be clicked on and open OptionsActivity
                map.setOnInfoWindowClickListener(new OnInfoWindowClickListener(){
                    @Override
                    public void onInfoWindowClick(final Marker marker){
                        if(marker.isVisible()
                            //|| marker.getTitle().equals("John Mulcahy Hall")
                            //|| marker.getTitle().equals("Dealy Hall")
                            //|| marker.getTitle().equals("Duane Library")
                                ){ // TODO replace Keating Hall with last marker clicked title

                            // TODO pass marker.getTitle() to --> OptionsActivity --> Info Button, etc.
                            Toast.makeText(MainActivity.this, "Location: " + marker.getTitle(), Toast.LENGTH_SHORT).show();
                            Intent info = new Intent(MainActivity.this, OptionsActivity.class);
                            startActivity(info);

                            Button getCheckInButton = (Button) findViewById(R.id.checkInButton);
                            getCheckInButton.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View arg0) {
                                    Toast.makeText(MainActivity.this, "You checked into:" + marker.getTitle(), Toast.LENGTH_SHORT).show();
                                    //System.out.println("Output from onclick: "+ marker.getTitle()); // Debug console output.

                                }
                            });
                        }
                    }
                });
            }
        }
    }

    private void setUpMap() {
        // Retrieve the city data from the web service
        // In a worker thread since it's a network operation.
        new Thread(new Runnable() {
            public void run() {
                try {
                    retrieveAndAddBuildings();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Cannot retrieve buildings", e);
                    return;
                }
            }
        }).start();
    }

    // The following reads the JSON file from the SERVICE_URL
    // Basically, we need to store the data from locations.json to a string
    protected void retrieveAndAddBuildings() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(conn.getInputStream());

            // Read the JSON data into the StringBuilder
            int read;
            char[] buff = new char[1024];
            while ((read = in.read(buff)) != -1) {
                json.append(buff, 0, read);
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error connecting to service", e);
            throw new IOException("Error connecting to service", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }

        // Create markers for the building data.
        // Must run this on the UI thread since it's a UI operation.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    createMarkersFromJson(json.toString());
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error processing JSON", e);
                }
            }
        });
    }

    // The following code parses JSON data from string.
    void createMarkersFromJson(String json) throws JSONException {
        // De-serialize the JSON string into an array of building objects
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each building in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            map.addMarker(new MarkerOptions()
                            .title(jsonObj.getString("name"))
                            .snippet(Integer.toString(jsonObj.getInt("idForInfoWindow")))
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                            .position(new LatLng(
                                    jsonObj.getJSONArray("latlng").getDouble(0),
                                    jsonObj.getJSONArray("latlng").getDouble(1)))
            );
        }
    }
}