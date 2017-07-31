package com.romodaniel.fitness;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TrackerFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private TextView date;
    public  TextView totalcal;
    private Button addButton;
    private Button manualButton;
    private static SQLiteDatabase db;

    private final String TAG = "addcalorie";

    public TrackerFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracker,container,false);

        date = (TextView) view.findViewById(R.id.date);
        totalcal = (TextView) view.findViewById(R.id.totalCalories);
        addButton = (Button) view.findViewById(R.id.addButton);
        manualButton = (Button) view.findViewById(R.id.manualButton);

        // Set date tv
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String formattedDate = df.format(c.getTime());
        date.setText(formattedDate);

        // TODO direct 'totalcal' to db data and grab total calories column

        // If add button clicked, then redirect to SearchFragment
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment fragment = new SearchFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contentMain, fragment);
                ft.commit();
            }
        });
        // If manual button clicked, then redirect to InputFragment
        manualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputFragment fragment = new InputFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contentMain, fragment);
                ft.commit();
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

    public static void addFood(String name, String cal, Context context) {
        Toast.makeText(context, "Added " + cal  + " calories from " + name, Toast.LENGTH_SHORT).show();

        // TODO add argument's into calories db by getting reference to db and setting calories

    }

    public static SQLiteDatabase getDb() {
        return db;
    }
}
