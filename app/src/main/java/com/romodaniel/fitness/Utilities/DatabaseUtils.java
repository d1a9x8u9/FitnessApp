package com.romodaniel.fitness.Utilities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.romodaniel.fitness.Utilities.Contract.TABLE_FITNESS.COLUMN_NAME_DATE;
import static com.romodaniel.fitness.Utilities.Contract.TABLE_FITNESS.TABLE_NAME;

/**
 * Created by Daniel on 7/26/2017.
 */

public class DatabaseUtils {
    public static Cursor getAll(SQLiteDatabase db) {
        String[] columns = {"totalcalories"};
        Cursor cursor = db.query(
                TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }
    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }

}
