/*package unused;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramap.R;

import java.util.Scanner;


public class InfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);


        Bundle bundle = getIntent().getExtras(); // Creates a Bundle from intent and gets extras
        String message = bundle.getString("TO_INFO"); //Looks for

        //TextView txtView = (TextView) findViewById(R.id.textViewInfo);
        //txtView.setText(message);

        //--- text view---
        TextView txtView = (TextView) findViewById(R.id.McGinley_center);
        final String Label = txtView.getText().toString();

        txtView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "You have clicked the Label : " + Label,
                        Toast.LENGTH_LONG).show();
            }

        });


    }

}*/
