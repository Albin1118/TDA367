package com.example.ziggy.trainingtracker.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCheckedListAdapter extends ArrayAdapter<IExercise> {

    private Context mContext;
    private List<IExercise>exercises = new ArrayList<>();

    ExerciseCheckedListAdapter(@NonNull Context context, List<IExercise>list){
        super(context, 0, list);
        mContext = context;
        exercises = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.item_exercisechecked, parent,false);

        IExercise currentExercise = exercises.get(position);

        CheckedTextView exerciseCheckedTextView = listItem.findViewById(R.id.exerciseCheckedTextView);

        exerciseCheckedTextView.setText(currentExercise.getName());

        /*exerciseCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(exerciseCheckedTextView.isChecked()){
                    exerciseCheckedTextView.setChecked(false);
                }else{
                    exerciseCheckedTextView.setChecked(true);
                }
            }
        });*/

        return listItem;
    }
}
