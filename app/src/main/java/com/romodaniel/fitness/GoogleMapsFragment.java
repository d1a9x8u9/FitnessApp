package com.romodaniel.fitness;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import android.support.v4.app.Fragment;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;


import java.util.ArrayList;
import java.util.Locale;

public class GoogleMapsFragment extends Fragment implements LocationListener, OnMapReadyCallback {

    private static final long REFRESH_RATE = 100L;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "GoogleMapsFragment";

    private boolean onStart;
    private double totalDistance;
    private long elapsedTime;
    private long startTime;
    private long timeOnPause;
    private long timeOnStart;
//    private ArrayList<LatitudeLongitude> coordinates;
    private GoogleMap mGoogleMap;
    private Handler mHandler;
    private LocationManager mLocationManager;
    private TextView mActiveTimeView;
    private TextView mTotalDistanceView;
    private TextView mAveragePaceView;

    private Runnable updateTimeThread = new Runnable() {

        // calculate time user begins, pauses, finished such as a stopwatch
        @Override
        public void run() {
            elapsedTime = SystemClock.uptimeMillis() - timeOnStart;
            int elapse = (int) (timeOnPause + elapsedTime) / 1000;
            int minutes = elapse / 60;
            int secs = elapse % 60;

            mActiveTimeView.setText(String.format(Locale.US, "02d:%02d", minutes, secs));
            mHandler.postDelayed(this, REFRESH_RATE);
        }

    };

    public GoogleMapsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

}
