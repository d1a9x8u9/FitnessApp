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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // fetch and display the name
        String name=getActivity().getIntent().getStringExtra("Name");
        String gender=getActivity().getIntent().getStringExtra("Gender");
        String weight=getActivity().getIntent().getStringExtra("Weight");
        String age=getActivity().getIntent().getStringExtra("Age");
        String height=getActivity().getIntent().getStringExtra("Height");

        //Now set the text view to perticular value .
        TextView tv=(TextView)view.findViewById(R.id.TFname);
        TextView tv1=(TextView)view.findViewById(R.id.TFgender);
        TextView tv2=(TextView)view.findViewById(R.id.TFweight);
        TextView tv3=(TextView)view.findViewById(R.id.TFheight);
        TextView tv4=(TextView)view.findViewById(R.id.TFage);

        tv.setText(name);
        tv1.setText(gender);
        tv2.setText(weight);
        tv3.setText(age);
        tv4.setText(height);


        // Inflate the layout for this fragment
        return view;
    }



}
