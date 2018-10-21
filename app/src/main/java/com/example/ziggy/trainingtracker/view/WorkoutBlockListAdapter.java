package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockListAdapter extends ArrayAdapter<IWorkoutBlock> {

    private Context mContext;
    private List<IWorkoutBlock>workoutBlocks = new ArrayList<>();

    public WorkoutBlockListAdapter(@NonNull Context context, List<IWorkoutBlock>list){
        super(context, 0, list);
        mContext = context;
        workoutBlocks = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_workoutblock,parent,false);

        IWorkoutBlock currentBlock = workoutBlocks.get(position);
        List<IExercise>exercises = currentBlock.getExercises();
        List<Integer>amounts = currentBlock.getAmounts();

        TextView multiplierTextView = (TextView)listItem.findViewById(R.id.multiplierTextView);
        multiplierTextView.setText(currentBlock.getMultiplierString());

        LinearLayout exercisesInBlockLinearLayout = (LinearLayout)listItem.findViewById(R.id.exercisesInBlockLinearLayout);
        exercisesInBlockLinearLayout.removeAllViews();

        for (int i = 0; i < exercises.size(); i++){
            View child = LayoutInflater.from(mContext).inflate(R.layout.item_blockexercise, parent, false);

            IExercise e = exercises.get(i);
            int amount = amounts.get(i);

            TextView name = (TextView)child.findViewById(R.id.exerciseNameTextView);
            name.setText(e.getName());

            TextView unit = (TextView)child.findViewById(R.id.exerciseUnitTextView);
            unit.setText(String.valueOf(amount) + " " + e.getUnit());

            exercisesInBlockLinearLayout.addView(child);
        }


        return listItem;
    }
}
