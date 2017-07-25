package com.romodaniel.fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


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



        // TODO add fragment into db

        // Set date tv
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        date.setText(formattedDate);

        // TODO - a way to get existing data (Eg. lunch data) that has already been written earlier that day
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
