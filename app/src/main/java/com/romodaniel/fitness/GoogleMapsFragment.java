package com.romodaniel.fitness;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

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
    private ArrayList<LatLng> coordinates;
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
            int seconds = elapse % 60;

            mActiveTimeView.setText(String.format(Locale.US, "%02d:%02d", minutes, seconds));
            mHandler.postDelayed(this, REFRESH_RATE);
        }

    };

    // prompt the user to enable their location if its not already enabled
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 1);
        } else if (mGoogleMap != null) {
            mGoogleMap.setMyLocationEnabled(true);

            // location needs to be updated every 5 seconds
            if (mLocationManager != null) {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 0.0f, this);

                Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if (location != null) {
                    LatLng lastKnownLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastKnownLatLng, 15.0f));
                }
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_maps, container, false);
    }

    // trace the user's run
    @Override
    public void onLocationChanged(Location location) {
        if (onStart) {
            LatLng coordinate = new LatLng(location.getLatitude(), location.getLongitude());
            coordinates.add(coordinate);

            // to avoid redrawing the same coordinates
            mGoogleMap.clear();

            // trace the map using the coordinates collected as the user ran
            mGoogleMap.addPolyline(new PolylineOptions().addAll(coordinates).color(Color.BLUE).width(5.0f));

            // update the map to the new position
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));

            // compute the distance ran
            int size = coordinates.size();
            if (size > 1) {
                LatLng previous = coordinates.get(size - 2);
                totalDistance += SphericalUtil.computeDistanceBetween(previous, coordinate);

                // convert the distance from meters to miles
                double displayTotalDistance = totalDistance * 0.000621371;
                mTotalDistanceView.setText(String.format(Locale.US, "%.2f mi", displayTotalDistance));

                int activeTime = (int) (timeOnPause + elapsedTime) / 1000 / 60;
                double averagePace = activeTime / displayTotalDistance;
                int minutes = (int) averagePace;
                int seconds = (int) (averagePace % 1 * 60);

                mAveragePaceView.setText(String.format(Locale.US, "%d:%02d/mi", minutes, seconds));

            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        mGoogleMap = googleMap;
        enableMyLocation();
    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    // check if the permission was granted
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE)
            return;
        if(permissionWasGranted(permissions, grantResults, Manifest.permission.ACCESS_FINE_LOCATION)) {
            enableMyLocation();
        } else {
            Log.e(TAG, "Permission wasn't granted");
        }
    }
    // verify that the permission granted is the location permission
    public static boolean permissionWasGranted(String[] permissions, int[] results, String permission) {
        for (int i = 0; i < permissions.length; i++) {
            if (permission.equals(permissions[i]))
                return results[i] == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }
    // layout
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        coordinates = new ArrayList<>();
        mHandler = new Handler();

        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_view)).getMapAsync(this);

        mActiveTimeView = (TextView) getActivity().findViewById(R.id.active_time);
        mAveragePaceView = (TextView) getActivity().findViewById(R.id.average_pace);
        mTotalDistanceView = (TextView) getActivity().findViewById(R.id.total_miles);

        final Button startPauseButton = (Button) getActivity().findViewById(R.id.start_pause_button);
        final Button stopButton = (Button) getActivity().findViewById(R.id.stop_button);

        // start button and the pause button
        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // time taken to run
                if (startTime == 0L)
                    startTime = SystemClock.uptimeMillis();
                onStart =! onStart;

                startPauseButton.setText(onStart ?  R.string.action_pause : R.string.action_start);
                stopButton.setEnabled(onStart);

                if (onStart) {
                    // active time
                    timeOnStart = SystemClock.uptimeMillis();
                    mHandler.postDelayed(updateTimeThread, REFRESH_RATE);
                    mActiveTimeView.setAnimation(null);
                } else {
                    // time paused
                    timeOnPause += elapsedTime;
                    mHandler.removeCallbacks(updateTimeThread);

                    Animation animation = new AlphaAnimation(0.0f, 1.0f);
                    animation.setDuration(500L);
                    animation.setRepeatCount(Animation.INFINITE);
                    animation.setRepeatMode(Animation.REVERSE);
                    animation.setStartOffset(20L);

                    mActiveTimeView.setAnimation(animation);
                }
            }
        });
        // once stop button is pressed, display the summary of the run
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart = false;

                mHandler.removeCallbacks(updateTimeThread);

                startPauseButton.setEnabled(false);

                int activeTime = (int) (timeOnPause + elapsedTime) / 1000;
                int totalTime = (int) (SystemClock.uptimeMillis() - startTime) / 1000;

                int minutes = activeTime / 60;
                int seconds = totalTime % 60;

                Log.d(TAG, String.format(Locale.US, "Active time: %02d:%02d", minutes, seconds));

                minutes = totalTime / 60;
                seconds = totalTime % 60;

                Log.d(TAG, String.format(Locale.US, "Total time: %02d:%02d", minutes, seconds));
            }
        });

    }
}