package com.romodaniel.fitness.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.romodaniel.fitness.data.Contract.TABLE_RUNS.TABLE_NAME;

/**
 * Created by drdan on 7/26/2017.
 */

public class DatabaseUtils {

    public static Cursor getAll(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    public static long InsertToDb(SQLiteDatabase db, Runs r){
        ContentValues cv = new ContentValues();
        cv.put(Contract.TABLE_RUNS.COLUMN_NAME_SECONDS, r.getSeconds());
        cv.put(Contract.TABLE_RUNS.COLUMN_NAME_MINUTES, r.getMinutes());
        cv.put(Contract.TABLE_RUNS.COLUMN_NAME_STEPS, r.getSteps());
        cv.put(Contract.TABLE_RUNS.COLUMN_NAME_MILES, r.getMiles());
        cv.put(Contract.TABLE_RUNS.COLUMN_NAME_CAL, r.getCal());
        return db.insert(TABLE_NAME, null,cv);

    }


//    cursor = DatabaseUtils.getAll(db);
//                cursor.moveToFirst();
//
//                while(cursor.moveToNext()){
//        int id = cursor.getInt(0);
//        double miles = cursor.getDouble(cursor.getColumnIndex(Contract.TABLE_RUNS.COLUMN_NAME_MILES));
//
//    }




    public static void deleteAll(SQLiteDatabase db) {
        db.delete(TABLE_NAME, null, null);
    }
}
