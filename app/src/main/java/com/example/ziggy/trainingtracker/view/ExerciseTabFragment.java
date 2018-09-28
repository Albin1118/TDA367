package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTabFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseList;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);

        addExerciseButton = (FloatingActionButton) view.findViewById(R.id.addExerciseButton);
        exerciseList = (ListView) view.findViewById(R.id.exerciseList);

        //Used for testing
        List<Exercise> exercises = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            exercises.add(new Exercise("Pre-made exercise", "This is a pre-made exercise"));
        }

        ArrayAdapter<Exercise> adapter = new ArrayAdapter<Exercise>(getContext(), android.R.layout.simple_list_item_1, exercises);

        exerciseList.setAdapter(adapter);
       //

        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(4);
            }
        });


        return view;
    }
}
