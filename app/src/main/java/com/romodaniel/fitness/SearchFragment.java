package com.romodaniel.fitness;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.romodaniel.fitness.Utilities.FoodItemAdapter;
import com.romodaniel.fitness.Utilities.FoodItems;
import com.romodaniel.fitness.Utilities.NetworkUtils;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Daniel on 7/29/2017.
 */

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button search, done;
    EditText q;
    String query;
    private RecyclerView fooditemsrv;

    private final String TAG = "searchfragment";

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.cal_search, container, false);

        search = (Button) view.findViewById(R.id.searchButton);
        done = (Button) view.findViewById(R.id.doneButton);
        q = (EditText) view.findViewById(R.id.search);
        fooditemsrv = (RecyclerView) view.findViewById(R.id.fooditemrv);

        fooditemsrv.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        search.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // get edit text and use it to search
                query= q.getText().toString();
                Log.d(TAG, "q : " + query);
                FetchSearchedItem fetch = new FetchSearchedItem();
                fetch.execute();
            }
        });

        // set button done to go back to tracker fragment.
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // Go back to TrackerFragment
                Fragment fragment = new TrackerFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.contentMain, fragment);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
        Log.d(TAG,"item #" + position + " clicked");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private class FetchSearchedItem extends AsyncTask<URL, Void, ArrayList<FoodItems>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<FoodItems> doInBackground(URL... strings) {

            ArrayList<FoodItems> result = null;

            URL url = NetworkUtils.buildUrl(query);
            Log.d(TAG, "url: " + url.toString());

            try {
                String json =  NetworkUtils.getResponseFromHttpUrl(url);
                result = NetworkUtils.parseJSON(json);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

            return result;

        }

        @Override
        protected void onPostExecute(final ArrayList<FoodItems> newsData) {
            super.onPostExecute(newsData);
            if(newsData!=null){
                FoodItemAdapter adapter = new FoodItemAdapter(newsData, new FoodItemAdapter.ItemClickListener() {
                    @Override
                    public void onItemClick(int clickedItemIndex) {
                        Log.d(TAG,"item clicked #" + clickedItemIndex);
                        Toast.makeText(getContext(), "Added into today's calories", Toast.LENGTH_SHORT).show();
                        // ADD INTO DB THE CALORIE OF THE ITEM, THEN TOAST
                    }
                });
                fooditemsrv.setAdapter(adapter);
            }
        }
    }
}
