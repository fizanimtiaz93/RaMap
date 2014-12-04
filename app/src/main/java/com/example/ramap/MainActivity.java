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
        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;
        import com.google.android.gms.maps.model.Polygon;
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

        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class MainActivity extends FragmentActivity {
    private static final String LOG_TAG = "RaMap";

    private static final String SERVICE_URL = "http://nrdyninja.com/android/ramap/locations.json";

    //private static final String POLYGON_URL = "http://nrdyninja.com/android/ramap/locationsPoly.json";
    private static final LatLng FORDHAM = new LatLng(40.8607081, -73.8854141);

    private static final int WIDTH_MAX = 50;
    private static final int HUE_MAX = 360;
    private static final int ALPHA_MAX = 255;

    private Polygon mMutablePolygon;


    protected GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);  // references layout/main.xml to the initial view
        setUpMapIfNeeded();             // sets up the MapView

        // Used for finding current location with button
        // Will eventually pass current location into a value so that markers
        // are populated when they're 5m from current location.
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);

        final TextView locationText = (TextView) findViewById(R.id.checkInLocation);
        Button getAnswerButton = (Button) findViewById(R.id.checkInButton);

        getAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String answer = "Checked Into <location name here>";
                locationText.setText(answer);
            }
        });

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker){
                if(marker.getTitle().equals("name")){
                    Intent info = new Intent(getApplicationContext(), CheckIns.class);
                    startActivity(info);
                }
            }
        });




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
        switch (item.getItemId()) {
            case R.id.action_normal:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;

            case R.id.action_hybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;

            case R.id.action_satellite:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
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
            }
        }
    }

    private void setUpMap() {
        // Retrieve the city data from the web service
        // In a worker thread since it's a network operation.
        new Thread(new Runnable() {
            public void run() {
                try {
                    retrieveAndAddMarkers();
                    retrieveAndAddPolygons();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Cannot retrieve markers", e);
                    return;
                }
            }
        }).start();


    }

    protected void retrieveAndAddMarkers() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL); // reads the service url into memory for the connection library
            conn = (HttpURLConnection) url.openConnection(); // creates the connection between thread and http
            InputStreamReader in = new InputStreamReader(conn.getInputStream()); // starts looking through latlng marker points in JSON file

            // reads the json information into the string builder
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
        // Create markers for the city data.
        // Must run this on the UI thread since it's a UI operation.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    createPolygonFromJson(json.toString()); // creates polygons from json file
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error processing JSON", e);
                }
            }
        });
    }


    protected void retrieveAndAddPolygons() throws IOException {
        HttpURLConnection conn = null;
        final StringBuilder json = new StringBuilder();
        try {
            // Connect to the web service
            URL url = new URL(SERVICE_URL); // reads the service url into memory for the connection library
            conn = (HttpURLConnection) url.openConnection(); // creates the connection between thread and http
            InputStreamReader in = new InputStreamReader(conn.getInputStream()); // starts looking through latlng marker points in JSON file

            // reads the json information into the string builder
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


        // Create markers for the city data.
        // Must run this on the UI thread since it's a UI operation.
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    createMarkersFromJson(json.toString()); // creates markers from json file
                    createPolygonFromJson(json.toString()); // creates polygons from json file
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Error processing JSON", e);
                }
            }
        });
    }

    void createPolygonFromJson(String json) throws JSONException {
    // De-serialize the JSON string into an array of city objects
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.strokeColor(Color.RED);
        polygonOptions.fillColor(Color.BLUE);
        JSONArray jsonArrayPoly = new JSONArray(json);
        for (int i = 0; i < jsonArrayPoly.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArrayPoly.getJSONObject(i);
            polygonOptions.add(new LatLng(jsonObj.getJSONArray("latlngPoly").getDouble(0),jsonObj.getJSONArray("latlngPoly").getDouble(1)));
        }
        Polygon polygon = map.addPolygon(polygonOptions);
    }

    void createMarkersFromJson(String json) throws JSONException {
        // De-serialize the JSON string into an array of city objects
        JSONArray jsonArray = new JSONArray(json);

        for (int i = 0; i < jsonArray.length(); i++) {
            // Create a marker for each city in the JSON data.
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            map.addMarker(new MarkerOptions()
                            .title(jsonObj.getString("name"))
                            .snippet(Integer.toString(jsonObj.getInt("check ins")))
                            .position(new LatLng(
                                    jsonObj.getJSONArray("latlng").getDouble(0),
                                    jsonObj.getJSONArray("latlng").getDouble(1)
                            ))
            );
        }
    }
}
