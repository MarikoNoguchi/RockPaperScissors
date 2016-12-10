package edu.mnstate.cw3967me.rockpaperscissors;

import android.app.Application;

import java.util.ArrayList;

/**
    store global values which every classes can use
 Mariko Noguchi
 11/28/2016
 */

public class Globals extends Application {
    //global values
    int rounds = 0;
    String name = "YOU";
    ArrayList<Result> results = new ArrayList<Result>(); //an array of result objects
    int score = 0;//win・・・2, draw・・・1, lose・・・0

}

