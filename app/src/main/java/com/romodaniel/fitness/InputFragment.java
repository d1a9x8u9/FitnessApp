package com.romodaniel.fitness;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by fy on 7/26/17.
 */

public class InputFragment extends Fragment {

    ImageButton searchButton, backSpace;
    TextView numScreen, backButton;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, addButton;

    public InputFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cal_add, container, false);

        backButton = (TextView) view.findViewById(R.id.backButton);

        searchButton = (ImageButton) view.findViewById(R.id.searchButton);

        numScreen =(TextView) view.findViewById(R.id.numScreen);

        button0 = (Button) view.findViewById(R.id.Button0);
        button1 = (Button) view.findViewById(R.id.Button1);
        button2 = (Button) view.findViewById(R.id.Button2);
        button3 = (Button) view.findViewById(R.id.Button3);
        button4 = (Button) view.findViewById(R.id.Button4);
        button5 = (Button) view.findViewById(R.id.Button5);
        button6 = (Button) view.findViewById(R.id.Button6);
        button7 = (Button) view.findViewById(R.id.Button7);
        button8 = (Button) view.findViewById(R.id.Button8);
        button9 = (Button) view.findViewById(R.id.Button9);

        backSpace = (ImageButton) view.findViewById(R.id.ButtonBS);

        addButton =(Button) view.findViewById(R.id.addButton);

        backButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.contentMain, new TrackerFragment())
                        .commit();
            }
        });

        searchButton.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction()
                        .replace(R.id.contentMain, new SearchFragment())
                        .commit();
            }
        });

        button0.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("0");
                else
                    numScreen.append("0");
            }
        });

        button1.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("1");
                else
                    numScreen.append("1");
            }
        });

        button2.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("2");
                else
                    numScreen.append("2");
            }
        });

        button3.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("3");
                else
                    numScreen.append("3");
            }
        });

        button4.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("4");
                else
                    numScreen.append("4");
            }
        });

        button5.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("5");
                else
                    numScreen.append("5");
            }
        });

        button6.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("6");
                else
                    numScreen.append("6");
            }
        });

        button7.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("7");
                else
                    numScreen.append("7");
            }
        });

        button8.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("8");
                else
                    numScreen.append("8");
            }
        });

        button9.setOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(checkZero(numScreen))
                    numScreen.setText("9");
                else
                    numScreen.append("9");
            }
        });

        backSpace.setOnClickListener( new View.OnClickListener(){
            String num;
            @Override
            public void onClick(View v) {

                if(!checkZero(numScreen)) {
                    num = numScreen.getText().toString();
                    num = num.substring(0, num.length()-1);
                    numScreen.setText(num);
                    if(num.isEmpty())
                        numScreen.setText("0");
                }

            }
        });

        addButton.setOnClickListener( new View.OnClickListener(){
            String num;
            @Override
            public void onClick(View v) {

            }
        });

        return view;

    }

    public boolean checkZero(TextView numScreen)
    {
        if(numScreen.getText().toString().equals("0"))
        {
            return true;
        }
        else
            return false;
    }

}