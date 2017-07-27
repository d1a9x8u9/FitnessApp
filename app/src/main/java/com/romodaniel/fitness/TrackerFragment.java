package com.romodaniel.fitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class TrackerFragment extends Fragment {

    Button addButton;
    public TrackerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tracker, container, false);

        addButton =(Button) view.findViewById(R.id.addButton);

        addButton.setOnClickListener( new View.OnClickListener(){
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
