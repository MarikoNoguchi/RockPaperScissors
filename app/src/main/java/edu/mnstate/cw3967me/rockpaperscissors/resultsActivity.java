package edu.mnstate.cw3967me.rockpaperscissors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
show the activity_results layout which has a fragment element defined by ResultsFragment class
 Mariko Noguchi
 11/28/2016
 */
public class resultsActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the view for the activity using XML
        setContentView(R.layout.activity_results);
    }
}
