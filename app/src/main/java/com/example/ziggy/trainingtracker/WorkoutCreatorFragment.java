package com.example.ziggy.trainingtracker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class WorkoutCreatorFragment extends Fragment {

    private EditText workoutNameEditText;
    private EditText workoutDescriptionEditText;
    private Button createWorkoutButton;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_creator, container, false);

        workoutNameEditText = (EditText) view.findViewById(R.id.workoutNameEditText);
        workoutDescriptionEditText = (EditText) view.findViewById(R.id.workoutDescriptionEditText);
        createWorkoutButton = (Button) view.findViewById(R.id.createWorkoutButton);

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });


        return view;
    }
}
