package com.romodaniel.fitness.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

/**
 * Created by drdan on 7/26/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "fitness.db";
    private static final String TAG = "dbhelper";

    public DBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " + Contract.TABLE_RUNS.TABLE_NAME + " ("+
                Contract.TABLE_RUNS._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.TABLE_RUNS.COLUMN_NAME_MINUTES + " int, " +
                Contract.TABLE_RUNS.COLUMN_NAME_SECONDS + " int, " +
                Contract.TABLE_RUNS.COLUMN_NAME_MILES + " double, " +
                Contract.TABLE_RUNS.COLUMN_NAME_CAL + " int, " +
                Contract.TABLE_RUNS.COLUMN_NAME_STEPS + " int" +
                "); ";

        Log.d(TAG, "create Table sql: " +queryString);
        db.execSQL(queryString);

        String queryString2 = "CREATE TABLE " + Contract.TABLE_USER.TABLE_NAME + " (" +
                Contract.TABLE_USER.COLUMN_NAME_GENDER + " TEXT," +
                Contract.TABLE_USER.COLUMN_NAME_AGE + " INTEGER," +
                Contract.TABLE_USER.COLUMN_NAME_WEIGHT + " INTEGER);";


        Log.d(TAG, "create Table sql: " +queryString2);
        db.execSQL(queryString2);


    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + Contract.TABLE_INFO.TABLE_NAME + " if exists;");

    }

}
