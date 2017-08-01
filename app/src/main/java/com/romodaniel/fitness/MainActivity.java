package com.romodaniel.fitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView iv;
    //i am passing the context object inside my database helper class
    DatabaseHelper helper=new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        iv = (ImageView) findViewById(R.id.imageView2);
    }

    public void onButtonClcik(View v)
    {
        if(v.getId() == R.id.Bdisplay)
        {
            //fetch user's name
            EditText a=(EditText)findViewById(R.id.TFname);
            EditText b=(EditText)findViewById(R.id.TFgender);
            EditText c=(EditText)findViewById(R.id.TFweight);
            EditText d=(EditText)findViewById(R.id.TFage);
            EditText e=(EditText)findViewById(R.id.TFheight);

            //get the user's name and convert it to string
            String str=a.getText().toString();
            String str1=b.getText().toString();
            String str2=c.getText().toString();
            String str3=d.getText().toString();
            String str4=e.getText().toString();
            Intent i=new Intent(MainActivity.this,Display.class);
            //transfer the name from mainactivity to Profile Fragment for that
            i.putExtra("Name",str);
            i.putExtra("Gender",str1);
            i.putExtra("Weight",str2);
            i.putExtra("Age",str3);
            i.putExtra("Height",str4);
            startActivity(i);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_profile) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMain, new ProfileFragment())
                    .commit();
       }
         else if (id == R.id.nav_tracker) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMain, new TrackerFragment())
                    .commit();

        } else if(id == R.id.nav_action){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMain, new MapFragment())
                    .commit();

        }

        //if we have time to do
//            if (id == R.id.nav_notes) {

//                iv.setImageResource(R.drawable.journal);
//            }
//        else } else if (id == R.id.nav_planner) {
//            iv.setImageResource(R.drawable.calendar);
//
//        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
