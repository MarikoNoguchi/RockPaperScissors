package edu.mnstate.cw3967me.rockpaperscissors;

/*
show the activity_main layout which has a fragment element defined by GameFragment class
 Mariko Noguchi
 11/28/2016
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.List;

import edu.mnstate.cw3967me.rockpaperscissors.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
