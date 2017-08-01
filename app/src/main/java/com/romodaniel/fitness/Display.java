package com.romodaniel.fitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class Display extends AppCompatActivity
{
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    }
    public void onButtonClick(View v)
    {
        //now we will take edit text of name gender and so on and convert it to string values

        EditText name=(EditText)findViewById(R.id.TFname);
        EditText gender=(EditText)findViewById(R.id.TFgender);
        EditText weight=(EditText)findViewById(R.id.TFweight);
        EditText age=(EditText)findViewById(R.id.TFage);
        EditText height=(EditText)findViewById(R.id.TFheight);

        String namestr=name.getText().toString();
        String genderstr=gender.getText().toString();
        String weightstr=weight.getText().toString();
        String agestr=age.getText().toString();
        String heightstr=height.getText().toString();

        //now insert the details into database

        Contact  c=new Contact();
        c.setName(namestr);
        c.setGender(genderstr);
        c.setWeight(Float.valueOf(weightstr));
        c.setAge(String.valueOf(agestr));
        c.setHeight(Float.valueOf(heightstr));

        helper.insertContact(c);


    }
}
