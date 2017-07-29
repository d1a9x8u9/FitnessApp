package com.romodaniel.fitness.Utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Daniel on 7/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "fitness.db";
    private static final String TAG = "dbhelper";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create our table with Contract data
    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " + Contract.TABLE_FITNESS.TABLE_NAME + " ("+
                Contract.TABLE_FITNESS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.TABLE_FITNESS.COLUMN_NAME_DATE + " VARCHAR(10) NO NULL," +
                Contract.TABLE_FITNESS.COLUMN_NAME_BREAKFAST + " TEXT NOT NULL, " +
                Contract.TABLE_FITNESS.COLUMN_NAME_LUNCH + " TEXT NO NULL," +
                Contract.TABLE_FITNESS.COLUMN_NAME_DINNER + " TEXT NOT NULL); ";

        Log.d(TAG, "Create table SQL: " + queryString);
        db.execSQL(queryString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + Contract.TABLE_FITNESS.TABLE_NAME + " if exists;");
    }
}

