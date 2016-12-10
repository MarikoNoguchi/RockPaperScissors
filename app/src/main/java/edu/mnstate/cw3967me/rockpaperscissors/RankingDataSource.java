package edu.mnstate.cw3967me.rockpaperscissors;

/*
retrieve data from a database and delete data in the database
 Mariko Noguchi
 11/28/2016
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RankingDataSource {
    private SQLiteDatabase myDB;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_SCORE};

    //constructor
    public RankingDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        myDB = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void createRanking(String name, String score) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_NAME, name);
        values.put(MySQLiteHelper.COLUMN_SCORE, score);
        myDB.insert(MySQLiteHelper.TABLE_RANKING, null, values);
        myDB.close();
    }

    public void deleteFirstRanking() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = myDB.query(MySQLiteHelper.TABLE_RANKING, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_SCORE + " DESC", "1");
                cursor.moveToFirst();
                Ranking ranking = cursorToRanking(cursor);
                final long id = ranking.getId();
                myDB.delete(MySQLiteHelper.TABLE_RANKING, MySQLiteHelper.COLUMN_ID + "=" + id, null);
            }
        }).start();
    }

    public List<Ranking> getAllRankings() {
        final List<Ranking> rankings = new ArrayList<Ranking>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor cursor = myDB.query(MySQLiteHelper.TABLE_RANKING, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_SCORE + " DESC");
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    Ranking myRanking = cursorToRanking(cursor);
                    rankings.add(myRanking);
                    cursor.moveToNext();
                }
                cursor.close();
            }

        }).start();
        return rankings;
    }

    public List<Ranking> getYourRankings(final String name) {
        final List<Ranking> rankings = new ArrayList<Ranking>();

        new Thread(new Runnable() {
            @Override
            public void run() {
        Cursor cursor = myDB.query(MySQLiteHelper.TABLE_RANKING, allColumns, MySQLiteHelper.COLUMN_NAME + " = '" + name + "'", null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ranking myRanking = cursorToRanking(cursor);
            rankings.add(myRanking);
            cursor.moveToNext();
        }
        cursor.close();
            }

        }).start();
        return rankings;
    }

    public List<Ranking> getTopThreeRankings() {
        final List<Ranking> rankings = new ArrayList<Ranking>();
        new Thread(new Runnable() {
            @Override
            public void run() {
        Cursor cursor = myDB.query(MySQLiteHelper.TABLE_RANKING, allColumns, null, null, null, null, MySQLiteHelper.COLUMN_SCORE + " DESC", "3");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ranking myRanking = cursorToRanking(cursor);
            rankings.add(myRanking);
            cursor.moveToNext();
        }
        cursor.close();
    }

}).start();
        return rankings;
    }

    private Ranking cursorToRanking(Cursor cursor) {
        Ranking myRanking = new Ranking();
        myRanking.setId(cursor.getLong(0));
        myRanking.setName(cursor.getString(1));
        myRanking.setScore(cursor.getString(2));
        return myRanking;
    }
}
