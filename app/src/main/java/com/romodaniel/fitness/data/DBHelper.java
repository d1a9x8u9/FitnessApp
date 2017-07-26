package com.romodaniel.fitness.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "items.db";
    private static final String TAG = "dbhelper";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryString = "CREATE TABLE " + Contract.TABLE_INFO.TABLE_NAME + " (" +
                Contract.TABLE_INFO.COLUMN_NAME_GENDER + " TEXT," +
                Contract.TABLE_INFO.COLUMN_NAME_AGE + " INTEGER," +
                Contract.TABLE_INFO.COLUMN_NAME_WEIGHT + " INTEGER);";
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("drop table " + Contract.TABLE_INFO.TABLE_NAME + " if exists;");

    }

}
