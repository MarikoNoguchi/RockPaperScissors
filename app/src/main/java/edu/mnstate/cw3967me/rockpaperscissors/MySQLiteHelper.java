package edu.mnstate.cw3967me.rockpaperscissors;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 Manage database
 Mariko Noguchi
 11/28/2016
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ranking.db";
    private static final int DATABASE_VERSION = 1;
    public static final String  TABLE_RANKING = "ranking";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";

    //PRIVATE String Constant -- used to create the database
    private static final String DATABASE_CREATE = "create table " + TABLE_RANKING + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME + " text not null, " +COLUMN_SCORE +" text not null);";

    public MySQLiteHelper(Context context){super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase myDatabase){myDatabase.execSQL(DATABASE_CREATE);}

    @Override
    public void onUpgrade(SQLiteDatabase myDatabase, int oldVersion, int newVersion){
        myDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_RANKING);
        onCreate(myDatabase);
    }
}
