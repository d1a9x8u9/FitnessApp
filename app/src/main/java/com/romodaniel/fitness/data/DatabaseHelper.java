package com.romodaniel.fitness;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Welcome To Future on 7/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_PASS="password";
    private static final int COLUMN_PN = Integer.parseInt("pn");
    private static final float COLUMN_CURRENTWEIGHT=Integer.parseInt("CW");
    private static final float COLUMN_GOALWEIGHT=Integer.parseInt("GW");
    private static final int COLUMN_AGE=Integer.parseInt("age");
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null auto_increment , name text not null , uname text not null , pass text not null ,pn int not null, cw float not null,gw float not null , age int not null);" ;

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }
    //created a method for inserting a Contact
    public void insertContact(com.romodaniel.fitness.Contact c)
    {
        // making this writable for inserting into database
        db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_UNAME,c.getUname());
        values.put(COLUMN_PASS,c.getPassword());

        values.put(String.valueOf(COLUMN_PN),c.getPn());
        values.put(String.valueOf(COLUMN_CURRENTWEIGHT),c.getCw());
        values.put(String.valueOf(COLUMN_GOALWEIGHT),c.getGw());
        values.put(String.valueOf(COLUMN_AGE),c.getAge());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public String searchPass(String uname)
    {
        db=this.getReadableDatabase();
        String query="select uname ,pass from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        String a;
        String b="not found";

        if(cursor.moveToFirst())
        {
            do{
                //fetch the password and username .0 for the username
                a=cursor.getString(0);
                //if username that we have in our database that matches with the user entered one for that
                if(a.equals(uname))
                {
                    //1 for the password
                    b=cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        String query ="DROP TABLE IF EXISTS " +TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
