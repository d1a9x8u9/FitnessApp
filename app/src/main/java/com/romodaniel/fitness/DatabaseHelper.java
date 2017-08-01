package com.romodaniel.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Welcome To Future on 7/27/2017.
 */
//SQLiteOpenHelper takes care of opening the database if it exists creating it upgrading it.
public class DatabaseHelper extends SQLiteOpenHelper
{
    //
    public DatabaseHelper(Context context){
        //thirs argument is custom cursor object and i am not creating that so its null
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="contacts.db";
    private static final String TABLE_NAME="contacts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_GENDER="gender";
    private static final String COLUMN_WEIGHT="weight";
    private static final String COLUMN_AGE="age";
    private static final String COLUMN_HEIGHT="height";

    SQLiteDatabase db;
    private static final String TABLE_CREATE="create table contacts (id integer primary key not null , name text not null, gender text not null , weight float not null , age int not null , height float not null )";

//called when database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        /*try {*/
        Log.d("THis is sql",TABLE_CREATE);
            db.execSQL(TABLE_CREATE);
            this.db = db;
        }/**/
      /*  catch(SQLException e)
        {
            e.printStackTrace();
        }
    }*/


    public void insertContact(Contact c){
        //to insert into database i am making this writable
        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        String query="select * from contacts";

        Cursor cursor=db.rawQuery(query,null);
        //we are passing the present contacts to the id
        int count=cursor.getCount();
       //here the count will start from zero
        values.put(COLUMN_ID,count);

        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_GENDER,c.getGender());
        values.put(COLUMN_WEIGHT,c.getWeight());
        values.put(COLUMN_AGE,c.getAge());
        values.put(COLUMN_HEIGHT,c.getHeight());
        //so now to insert contact object into the database
        db.insert(TABLE_NAME,null,values);
        db.close();




    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ="DROP TABLE IF EXISTS" +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
