package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;

public class WorkoutCreatorFragment extends Fragment {

    private EditText workoutNameEditText;
    private EditText workoutDescriptionEditText;
    private Button createWorkoutButton;

    private MainActivity parentActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = (MainActivity)getActivity();
        view  = inflater.inflate(R.layout.fragment_workout_creator, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        workoutNameEditText = view.findViewById(R.id.workoutNameEditText);
        workoutDescriptionEditText = view.findViewById(R.id.workoutDescriptionEditText);
        createWorkoutButton = view.findViewById(R.id.createWorkoutButton);
    }

    private void initListeners() {
        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setViewPager(1);
                Toast.makeText(getContext(), "New workout created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
