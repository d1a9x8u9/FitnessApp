package com.romodaniel.fitness;

import android.content.Context;
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
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;

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

    private String TAG= "userTest";

    private Cursor cursor;


//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        db = helper.getWritableDatabase();
//        cursor = getAllItems(db);
//        Log.d("mainactivity","db: " + db);
//
//        // TODO - CREATE AN ADAPTER
//    }

//    private Cursor getAllItems(SQLiteDatabase db) {
//
//        return db.query(
//                Contract.TABLE_FITNESS.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                Contract.TABLE_FITNESS.COLUMN_NAME_DATE
//        );
//    }

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

        helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        View hView = navigationView.getHeaderView(0);
        userName = (TextView) hView.findViewById(R.id.user_name);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirst = prefs.getBoolean("isfirst", true);


        Log.d(TAG, "is first start up:"+ isFirst);


        if(isFirst){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contentMain, new UserInputFragment())
                    .commit();

        }

        Cursor userCursor = UserDbUtils.getAll(db);
        Log.d("userCursor", userCursor.moveToFirst() +"");
        if(userCursor.moveToFirst()){
            String firstName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_FIRST_NAME));
            String lastName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_LAST_NAME));
            userName.setText(firstName + " "+ lastName);

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

}
