package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IWorkout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListAdapter extends ArrayAdapter<IWorkout> {

    private Context mContext;
    private List<IWorkout>workouts = new ArrayList<>();

    WorkoutListAdapter(@NonNull Context context, List<IWorkout>list){
        super(context, 0, list);
        mContext = context;
        workouts = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_workout, parent,false);

        IWorkout currentWorkout = workouts.get(position);

        TextView workoutNameTextView = (TextView) listItem.findViewById(R.id.workoutNameTextView);
        TextView workoutBlocksTextView = (TextView) listItem.findViewById(R.id.workoutBlocksTextView);

        workoutNameTextView.setText(currentWorkout.getName());
        workoutBlocksTextView.setText(currentWorkout.getNumberOfBlocks() + " blocks");

        return listItem;
    }
}
