package com.romodaniel.fitness;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Register extends AppCompatActivity {

    //created an object of the class database helper
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText etAge;
        EditText etName;
        EditText etUsername;
        EditText etPassword;
        EditText etPN;
        EditText etCW;
        EditText etGW;
        EditText etPassword1;

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPN = (EditText) findViewById(R.id.etPN);
        etCW = (EditText) findViewById(R.id.etCW);
        etGW=(EditText) findViewById(R.id.etGW);
        etPassword1 = (EditText) findViewById(R.id.etPassword1);

        Button bRegister=(Button) findViewById(R.id.bRegister);

        //if password's dont match then toast that please type password again

        /*if(!etPassword.equals(etPassword1))
        {
            Toast pass=Toast.makeText(Register.this,"Password's dont match type again",Toast.LENGTH_LONG);
            pass.show();
        }*/
    }

    public void onButtonClick(View v)
    {
        EditText etAge;
        EditText etName;
        EditText etUsername;
        EditText etPassword;
        EditText etPN;
        EditText etCW;
        EditText etGW;
        EditText etPassword1;

        etAge = (EditText) findViewById(R.id.etAge);
        etName = (EditText) findViewById(R.id.etName);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPN = (EditText) findViewById(R.id.etPN);
        etCW = (EditText) findViewById(R.id.etCW);
        etGW=(EditText) findViewById(R.id.etGW);
        etPassword1 = (EditText) findViewById(R.id.etPassword1);


        if(!etPassword.equals(etPassword1))
        {
            Toast pass=Toast.makeText(Register.this,"Password's dont match type again",Toast.LENGTH_LONG);
            pass.show();
        }
        else
        {
            //Insert the details in the database
            Contact c =new Contact();
            c.setName(String.valueOf(etName));
            c.setPassword(String.valueOf(etPassword));
            c.setUname(String.valueOf(etUsername));
            c.setAge(Integer.valueOf(String.valueOf(etAge)));
            c.setPn(Integer.valueOf(String.valueOf(etPN)));
            c.setCw(Integer.valueOf(String.valueOf(etCW)));
            c.setGw(Integer.valueOf(String.valueOf(etGW)));

            helper.insertContact(c);

        }
    }



}
