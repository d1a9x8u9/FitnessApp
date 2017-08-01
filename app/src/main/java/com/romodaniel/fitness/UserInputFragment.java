package com.romodaniel.fitness;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.romodaniel.fitness.data.DBHelper;
import com.romodaniel.fitness.data.User;
import com.romodaniel.fitness.data.UserDbUtils;

import static com.google.android.gms.wearable.DataMap.TAG;
import static com.romodaniel.fitness.data.Contract.TABLE_USER.COLUMN_NAME_FIRST_NAME;
import static com.romodaniel.fitness.data.Contract.TABLE_USER.COLUMN_NAME_LAST_NAME;


public class UserInputFragment extends Fragment {

    private SQLiteDatabase db;
    private DBHelper helper;
    private Button submit;
    private EditText fName;
    private EditText lName;
    private EditText gender;
    private EditText foot;
    private EditText inch;
    private EditText weight;
    private TextView userName;

    public UserInputFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_user_input, container, false);

        submit = (Button)  view.findViewById(R.id.submit);
        fName = (EditText) view.findViewById(R.id.f_name);
        lName = (EditText) view.findViewById(R.id.l_name);
        gender= (EditText) view.findViewById(R.id.gender);
        weight= (EditText) view.findViewById(R.id.weight);
        foot=   (EditText) view.findViewById(R.id.foot);
        inch=   (EditText) view.findViewById(R.id.inches);

        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Log.d("buttonPressed","pressed");
                Log.d("buttonPressed", fName.getText().toString());

                if(fName.getText().toString().trim().isEmpty()
                        ||lName.getText().toString().trim().isEmpty()
                        ||gender.getText().toString().trim().isEmpty()
                        ||weight.getText().toString().trim().isEmpty()
                        ||foot.getText().toString().trim().isEmpty()
                        ||inch.getText().toString().trim().isEmpty()){


                    String warning = "please fill out EVERYTHING";
                    Toast toast = Toast.makeText(getActivity(), warning, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 100);
                    toast.show();

                }
                else{
                    int inches = Integer.parseInt(foot.getText().toString().trim()) * 12 + Integer.parseInt(inch.getText().toString().trim());
                    int lbs = Integer.parseInt(weight.getText().toString().trim());

                    User user = new User(fName.getText().toString(),lName.getText().toString(),gender.getText().toString().trim(), inches, lbs);

                    Log.d(TAG, "userInfo: "+ user.toString());
                    UserDbUtils.InsertToDb(db,user);

                    Cursor userCursor = UserDbUtils.getAll(db);
                    Log.d("userCursor", userCursor.moveToFirst() +"");
                    if(userCursor.moveToFirst()){
                        String firstName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_FIRST_NAME));
                        String lastName = userCursor.getString(userCursor.getColumnIndex(COLUMN_NAME_LAST_NAME));
                        userName.setText(firstName + " "+ lastName);

                    }
                    //findViewById(R.id.user_first).setVisibility(View.GONE);
                }

            }
        });
        return view;


    }


}
