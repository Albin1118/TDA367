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

public class ExerciseListAdapter extends ArrayAdapter<IExercise> {

    private Context mContext;
    private List<IExercise>exercises = new ArrayList<>();

    public ExerciseListAdapter(@NonNull Context context, List<IExercise>list){
        super(context, 0, list);
        mContext = context;
        exercises = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.exercise_list_item, parent,false);

        IExercise currentExercise = exercises.get(position);

        TextView exerciseNameTextView = (TextView) listItem.findViewById(R.id.exerciseNameTextView);
        TextView exerciseDescriptionTextView = (TextView) listItem.findViewById(R.id.exerciseDescriptionTextView);

        exerciseNameTextView.setText(currentExercise.getName());
        exerciseDescriptionTextView.setText(currentExercise.getDescription());

        return listItem;
    }
}
