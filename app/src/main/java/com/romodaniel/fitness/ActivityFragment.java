package com.romodaniel.fitness;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ActivityFragment extends Fragment {
    private ImageView startRun;
    private View view;



    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity, container, false);

        startRun = (ImageView) view.findViewById(R.id.startRun);

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
