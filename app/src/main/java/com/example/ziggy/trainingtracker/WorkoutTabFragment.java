package com.example.ziggy.trainingtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutTabFragment extends Fragment {

    private FloatingActionButton addWorkoutButton;
    private ListView workoutList;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_tab, container, false);

        addWorkoutButton = (FloatingActionButton) view.findViewById(R.id.addWorkoutButton);
        workoutList = (ListView) view.findViewById(R.id.workoutList);

        //Used for testing
        List <Exercise> exercises = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            exercises.add(new Exercise("Pre-made exercise", "This is a pre-made exercise"));
        }

        List <Workout> workouts = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            workouts.add(new Workout("Pre-made workout", "This is a pre-made workout", exercises));
        }

        ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(getContext(), android.R.layout.simple_list_item_1, workouts);

        workoutList.setAdapter(adapter);


        /*
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });*/


        return view;
    }

}
