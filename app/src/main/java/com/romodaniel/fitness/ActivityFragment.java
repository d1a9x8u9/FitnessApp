package com.romodaniel.fitness;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.romodaniel.fitness.data.Contract;
import com.romodaniel.fitness.data.DBHelper;
import com.romodaniel.fitness.data.DatabaseUtils;

import java.util.Locale;


public class ActivityFragment extends Fragment {
    private ImageView startRun;
    private TextView totalRuns;
    private TextView totalMiles;
    private TextView totalTime;
    private TextView totalCal;
    private View view;
    private SQLiteDatabase db;



    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity, container, false);

        startRun = (ImageView) view.findViewById(R.id.startRun);
        totalRuns =(TextView) view.findViewById(R.id.total_runs);
        totalCal = (TextView) view.findViewById(R.id.total_cal);
        totalMiles =(TextView) view.findViewById(R.id.total_miles);
        totalTime = (TextView) view.findViewById(R.id.total_time);


        DBHelper dbhelper = new DBHelper(this.getActivity());
        db = dbhelper.getWritableDatabase();
        Cursor cursor = DatabaseUtils.getAll(db);

        double miles=0;
        double calories =0;
        int sec =0;

        //go in every entry add it to the last
        while(cursor.moveToNext()){
            miles += cursor.getDouble(cursor.getColumnIndex(Contract.TABLE_RUNS.COLUMN_NAME_MILES));
            calories += cursor.getDouble(cursor.getColumnIndex(Contract.TABLE_RUNS.COLUMN_NAME_CAL));
            sec += cursor.getDouble(cursor.getColumnIndex(Contract.TABLE_RUNS.COLUMN_NAME_TIME));
        }


        totalMiles.setText("" + miles);
        totalCal.setText(String.format(Locale.US, "%.2f",calories));
        totalTime.setText(String.format(Locale.US, "%02d:%02d:%02d", sec/36000, (sec/60)%60, sec %60));


        //total run is the id of the last run entered since user cant delete runs
        if(cursor.moveToLast()){
            totalRuns.setText(""+cursor.getInt(0));
        }else {
            totalRuns.setText("0");
        }


        startRun.setOnClickListener(new View.OnClickListener() {
            // once the image is clicked get the google maps fragment where the user can start run
            @Override
            public void onClick(View v) {
                //transition to map tracer
                view.findViewById(R.id.start).setVisibility(View.GONE);


                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.fragment_activity, new GoogleMapsFragment())
                        .commit();

            }
        });

        return view;
    }


}
