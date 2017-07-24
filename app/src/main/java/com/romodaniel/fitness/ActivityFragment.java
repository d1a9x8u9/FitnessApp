package com.romodaniel.fitness;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;


public class ActivityFragment extends Fragment implements OnMapReadyCallback {
    private ImageView startRun;
    private MapView mapView;
    private GoogleMap map;
    private View view;

    public ActivityFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_activity, container, false);
        view.findViewById(R.id.map).setVisibility(View.INVISIBLE);
        startRun = (ImageView) view.findViewById(R.id.startRun);
        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);



        startRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //transition to map tracer
                view.findViewById(R.id.start).setVisibility(View.GONE);
                view.findViewById(R.id.map).setVisibility(View.VISIBLE);


            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        //map.addMarker(new MarkerOptions().position(l));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
