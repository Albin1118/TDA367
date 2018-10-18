package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockListAdapter extends ArrayAdapter<WorkoutBlock> {

    private Context mContext;
    private List<WorkoutBlock>workoutBlocks = new ArrayList<>();

    public WorkoutBlockListAdapter(@NonNull Context context, List<WorkoutBlock>list){
        super(context, 0, list);
        mContext = context;
        workoutBlocks = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.workout_block_list_item,parent,false);

        WorkoutBlock currentBlock = workoutBlocks.get(position);
        List<Exercise>exercises = currentBlock.getExercises();
        List<Integer>amounts = currentBlock.getAmounts();

        TextView multiplierTextView = (TextView)listItem.findViewById(R.id.multiplierTextView);
        multiplierTextView.setText(currentBlock.getMultiplierString());

        LinearLayout exercisesInBlockLinearLayout = (LinearLayout)listItem.findViewById(R.id.exercisesInBlockLinearLayout);
        exercisesInBlockLinearLayout.removeAllViews();

        for (int i = 0; i < exercises.size(); i++){
            View child = LayoutInflater.from(mContext).inflate(R.layout.exercise_list_item_unit, null);

            Exercise e = exercises.get(i);
            int amount = amounts.get(i);

            TextView name = (TextView)child.findViewById(R.id.exerciseNameTextView);
            name.setText(e.getName());

            TextView unit = (TextView)child.findViewById(R.id.exerciseUnitTextView);
           //e.setNumberofUnit(50);
            unit.setText(String.valueOf(amount) + " " + e.getUnit());

            exercisesInBlockLinearLayout.addView(child);
        }


        return listItem;
    }
}
