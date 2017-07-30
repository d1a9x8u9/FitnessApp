package com.romodaniel.fitness.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.romodaniel.fitness.R;
import com.romodaniel.fitness.TrackerFragment;

import java.util.ArrayList;

/**
 * Created by Daniel on 7/27/2017.
 */

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.ItemHolder>{

    private ArrayList<FoodItems> data;
    ItemClickListener listener;

    private final String TAG = "fooditemadapter";


    public FoodItemAdapter(ArrayList<FoodItems> data, ItemClickListener listener){
        this.data = data;
        this.listener = listener;
    }

    public interface ItemClickListener {
        void onItemClick(int clickedItemIndex);
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.calorieitem, parent, shouldAttachToParentImmediately);
        ItemHolder holder = new ItemHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView foodname;
        TextView foodcalorie;

        ItemHolder(View view){
            super(view);

            foodname = (TextView)view.findViewById(R.id.foodName);
            foodcalorie = (TextView)view.findViewById(R.id.foodCalorie);

            view.setOnClickListener(this);
        }

        public void bind(int pos){
            // Get data to display into layout 'calorieitem'.
            FoodItems repo = data.get(pos);
            foodname.setText(repo.getItem_name());
            foodcalorie.setText(repo.getCalories().concat(" cal"));
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            listener.onItemClick(pos);

            // Return selected item's calorie to TrackerFragment
            TrackerFragment.addFood(foodname.getText().toString(),foodcalorie.getText().toString(),v.getContext());
        }
    }

}