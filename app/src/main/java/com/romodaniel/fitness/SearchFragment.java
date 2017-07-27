package com.romodaniel.fitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by fy on 7/26/17.
 */

public class SearchFragment extends Fragment {

    TextView numScreen, backButton;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cal_search, container, false);

        backButton = (TextView) view.findViewById(R.id.backButton);

        backButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.contentMain, new InputFragment())
                        .commit();
            }
        });

        return view;
    }
}