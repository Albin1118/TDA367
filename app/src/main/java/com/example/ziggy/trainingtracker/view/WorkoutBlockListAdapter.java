package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockListAdapter extends ArrayAdapter<IWorkoutBlock> {

    private Context mContext;
    private EditText weight;
    private Boolean hideWeightField;
    private List<IWorkoutBlock>workoutBlocks = new ArrayList<>();


    public WorkoutBlockListAdapter(@NonNull Context context, List<IWorkoutBlock>list, Boolean hideWeightField){
        super(context, 0, list);
        mContext = context;
        workoutBlocks = list;
        this.hideWeightField = hideWeightField;
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
        List<Double>weights = currentBlock.getWeights();

        TextView multiplierTextView = (TextView)listItem.findViewById(R.id.multiplierTextView);
        multiplierTextView.setText(currentBlock.getMultiplierString());

        LinearLayout exercisesInBlockLinearLayout = (LinearLayout)listItem.findViewById(R.id.exercisesInBlockLinearLayout);
        exercisesInBlockLinearLayout.removeAllViews();

        int i;
        for (i=0; i < exercises.size(); i++){
            View child = LayoutInflater.from(mContext).inflate(R.layout.item_blockexercise, parent, false);

            IExercise exercise = exercises.get(i);
            int amount = amounts.get(i);

            TextView name = (TextView)child.findViewById(R.id.exerciseNameTextView);
            name.setText(exercise.getName());

            TextView unit = (TextView)child.findViewById(R.id.exerciseUnitTextView);
            unit.setText(String.valueOf(amount) + " " + exercise.getUnit());

            weight = (EditText) child.findViewById(R.id.weightEditText);
            weight.setVisibility(View.INVISIBLE);

            if(exercise.isWeightBased() && !hideWeightField) {
                weight.setText(String.valueOf(weights.get(i)));
                weight.setVisibility(View.VISIBLE);
            }

            int indexOfCurrentExercise = i;

            weight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable no) {
                    try {
                        try {
                            if (no.length() > 0)
                                weights.set(indexOfCurrentExercise, Double.parseDouble(no.toString()));
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong input");
                        }
                    } catch (IndexOutOfBoundsException i) {
                        System.out.println("Out of bounds");
                    }
                }
            });
            exercisesInBlockLinearLayout.addView(child);
        }


        return listItem;
    }

}
