package com.romodaniel.fitness;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.romodaniel.fitness.data.DBHelper;
import com.romodaniel.fitness.data.User;
import com.romodaniel.fitness.data.UserDbUtils;

import static com.romodaniel.fitness.data.Contract.TABLE_USER.*;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ImageView iv;
    private SQLiteDatabase db;
    private DBHelper helper;
    private Button submit;
    private EditText fName;
    private EditText lName;
    private EditText gender;
    private EditText foot;
    private EditText inch;
    private EditText weight;
    private TextView userName;
    private boolean isFirst;

    private String TAG= "userTest";

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

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        View hView = navigationView.getHeaderView(0);
        userName = (TextView) hView.findViewById(R.id.user_name);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        isFirst = prefs.getBoolean("isfirst", true);

        Log.d(TAG, "is first start up:"+ isFirst);


        if(isFirst){
            findViewById(R.id.user_first).setVisibility(View.VISIBLE);

            submit = (Button)  findViewById(R.id.submit);
            fName = (EditText) findViewById(R.id.f_name);
            lName = (EditText) findViewById(R.id.l_name);
            gender= (EditText) findViewById(R.id.gender);
            weight = (EditText) findViewById(R.id.weight);
            foot= (EditText) findViewById(R.id.foot);
            inch= (EditText) findViewById(R.id.inches);

            submit.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    Log.d("buttonPressed","pressed");
                    Log.d("buttonPressed", fName.getText().toString());

                    if(fName.getText().toString().trim().isEmpty()
                            ||lName.getText().toString().trim().isEmpty()
                            ||gender.getText().toString().trim().isEmpty()
                            ||weight.getText().toString().trim().isEmpty()
                            ||foot.getText().toString().trim().isEmpty()
                            ||inch.getText().toString().trim().isEmpty()){

                        Context context = getApplicationContext();
                        String warning = "please fill out EVERYTHING";
                        Toast toast = Toast.makeText(context, warning, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 100);
                        toast.show();

                    }
                    else{
                        int inches = Integer.parseInt(foot.getText().toString().trim()) * 12 + Integer.parseInt(inch.getText().toString().trim());
                        int lbs = Integer.parseInt(weight.getText().toString().trim());

                        User user = new User(fName.getText().toString(),lName.getText().toString(),gender.getText().toString().trim(), inches, lbs);

                        Log.d(TAG, "userInfo: "+ user.toString());
                        UserDbUtils.InsertToDb(db,user);

                        setUserName();
                        findViewById(R.id.user_first).setVisibility(View.GONE);
                        isFirst = false;
                    }

                }
            });


            isFirst = false;

        }else{
            findViewById(R.id.user_first).setVisibility(View.GONE);
            setUserName();

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


//   settings not yet on the list to do
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
                    .replace(R.id.contentMain, new ActivityFragment())
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


    public void setUserName(){
        Cursor userCursor = UserDbUtils.getAll(db);
        Log.d("userCursor", userCursor.moveToFirst() +"");
        if(userCursor.moveToNext()){
            String firstName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_FIRST_NAME));
            String lastName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_LAST_NAME));
            userName.setText(firstName + " "+ lastName);

        }
    }





}
