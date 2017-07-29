package com.romodaniel.fitness;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
<<<<<<< Updated upstream
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
=======
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
>>>>>>> Stashed changes
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
<<<<<<< Updated upstream
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
=======
import android.widget.Button;
import android.widget.TextView;

import com.romodaniel.fitness.Utilities.MyAdapter;
>>>>>>> Stashed changes

import java.text.SimpleDateFormat;
import java.util.Calendar;

<<<<<<< Updated upstream
public class TrackerFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    TextView breakfast;
    TextView lunch;
    TextView dinner;
    TextView date;
    Button breakfastButton;
    Button lunchButton;
    Button dinnerButton;
    private final String TAG = "addcalorie";

    public TrackerFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracker,container,false);
        breakfast = (TextView) view.findViewById(R.id.breakfast);
        lunch = (TextView) view.findViewById(R.id.lunch);
        dinner = (TextView) view.findViewById(R.id.dinner);
        date = (TextView) view.findViewById(R.id.date);
        breakfastButton = (Button) view.findViewById(R.id.breakfastButton);
        lunchButton = (Button) view.findViewById(R.id.lunchButton);
        dinnerButton = (Button) view.findViewById(R.id.dinnerButton);

        // Setup buttons
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Breakfast button clicked");
                // Direct to SearchAPIFragment. Input the result into appropriate db section
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Lunch button clicked");
                // Direct to SearchAPIFragment. Input the result into appropriate db section
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"Dinner button clicked");
                // Direct to SearchAPIFragment. Input the result into appropriate db section
            }
        });
=======
public class TrackerFragment extends Fragment implements AdapterView.OnItemSelectedListener, MyAdapter.ItemClickListener {

    private TextView date;
    private Button addButton;
    private SQLiteDatabase db;
>>>>>>> Stashed changes

    private final String TAG = "addcalorie";

<<<<<<< Updated upstream

        // TODO add fragment into db
=======
    public TrackerFragment(){
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracker,container,false);

        date = (TextView) view.findViewById(R.id.date);
        addButton = (Button) view.findViewById(R.id.addButton);
>>>>>>> Stashed changes

        // Set date tv
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
<<<<<<< Updated upstream
        String formattedDate = df.format(c.getTime());

        date.setText(formattedDate);

        // TODO - a way to get existing data (Eg. lunch data) that has already been written earlier that day
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
=======
        final String formattedDate = df.format(c.getTime());
        date.setText(formattedDate);

        // Setup buttons
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment fragment = new SearchFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contentMain, fragment);
                ft.commit();

//                Log.d(TAG,"Add food button clicked");
//                FetchSearchedItem fetch = new FetchSearchedItem();
//                fetch.execute();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(Cursor cursor, int clickedItemIndex) {
        cursor.moveToPosition(clickedItemIndex);
        Log.d(TAG, String.format("Item clicked # ", clickedItemIndex));
>>>>>>> Stashed changes
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
