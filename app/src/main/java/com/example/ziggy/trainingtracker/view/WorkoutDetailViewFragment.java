package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ziggy.trainingtracker.R;

public class WorkoutDetailViewFragment extends Fragment {

    private TextView workoutNameTextView;
    private TextView workoutDescriptionTextView;
    private Button startWorkoutButton;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_detail_view, container, false);

        workoutNameTextView = (TextView)view.findViewById(R.id.workoutNameTextView);
        workoutDescriptionTextView = (TextView)view.findViewById(R.id.workoutDescriptionTextView);
        startWorkoutButton = (Button)view.findViewById(R.id.startWorkoutButton);

        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Workout started!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    public void setWorkoutNameTextView(String workoutName) {
        this.workoutNameTextView.setText(workoutName);
    }

    public void setWorkoutDescriptionTextView(String workoutDescription) {
        this.workoutDescriptionTextView.setText(workoutDescription);
    }
}
