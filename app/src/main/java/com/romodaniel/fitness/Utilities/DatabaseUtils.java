package com.romodaniel.fitness.Utilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.romodaniel.fitness.data.Contract.TABLE_FITNESS.*;

/**
 * Created by Daniel on 7/26/2017.
 */

public class DatabaseUtils {
    public static Cursor getAllBreakfast(SQLiteDatabase db) {
        String[] columns = {"_id","breakfast"};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                COLUMN_NAME_DATE + " DESC"
        );
        return cursor;
    }

    public static Cursor getAllLunch(SQLiteDatabase db) {
        String[] columns = {"_id","lunch"};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                COLUMN_NAME_DATE + " DESC"
        );
        return cursor;
    }

    public static Cursor getAllDinner(SQLiteDatabase db) {
        String[] columns = {"_id","dinner"};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                COLUMN_NAME_DATE + " DESC"
        );
        return cursor;
    }
    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

}
