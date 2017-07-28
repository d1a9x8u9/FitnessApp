package com.romodaniel.fitness;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        // fetch and display the name
        String name=getIntent().getStringExtra("Name");
        String gender=getIntent().getStringExtra("Gender");
        String weight=getIntent().getStringExtra("Weight");
        String age=getIntent().getStringExtra("Age");
        String height=getIntent().getStringExtra("Height");

        //Now set the text view to perticular value .
        TextView tv=(TextView)findViewById(R.id.TFname);
        TextView tv1=(TextView)findViewById(R.id.TFgender);
        TextView tv2=(TextView)findViewById(R.id.TFweight);
        TextView tv3=(TextView)findViewById(R.id.TFheight);
        TextView tv4=(TextView)findViewById(R.id.TFage);

        tv.setText(name);
        tv1.setText(gender);
        tv2.setText(weight);
        tv3.setText(age);
        tv4.setText(height);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        return view;
    }



}
