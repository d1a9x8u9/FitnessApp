package com.romodaniel.fitness;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

            mActiveTimeView.setText(String.format(Locale.US, "02d:%02d", minutes, seconds));
            mHandler.postDelayed(this, REFRESH_RATE);
        }

    };

    // actions to take when location is enabled
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

}
