package com.romodaniel.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText  etPassword = (EditText) findViewById(R.id.etPassword);
        Button bLogin = (Button) findViewById(R.id.bLogin);
        TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        //creating a listener . when a user will click the link it will redirect to register page

        registerLink.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                //here we are saying that from this page navigate user to Register class so
                                                //for that i have created an intent

                                                Intent registerIntent=new Intent(Loginpage.this,Register.class);
                                                //make  this intent actually happen

                                                Loginpage.this.startActivity(registerIntent);
                                            }
                                        }

        );
    }
}
