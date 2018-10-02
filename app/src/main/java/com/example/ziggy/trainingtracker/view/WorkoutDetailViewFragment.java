package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDetailViewFragment extends Fragment {

    private String workoutName = "Workout name1";
    private String workoutDescription = "Workout description";
    private List<WorkoutBlock>workoutBlocks = new ArrayList<>();

    private TextView workoutNameTextView;
    private TextView workoutDescriptionTextView;
    private Button startWorkoutButton;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_detail_view, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        workoutNameTextView = view.findViewById(R.id.workoutNameTextView);
        workoutNameTextView.setText(workoutName);

        workoutDescriptionTextView = view.findViewById(R.id.workoutDescriptionTextView);
        workoutDescriptionTextView.setText(workoutDescription);

        startWorkoutButton = view.findViewById(R.id.startWorkoutButton);
    }

    private void initListeners() {
        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Workout started!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh fragment
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setReorderingAllowed(false);
            ft.detach(this).attach(this).commit();
            /*FragmentTransaction transaction = ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction();
            transaction.setReorderingAllowed(false);
            transaction.detach(this).attach(this).commit();*/
        }
    }

    public void setWorkoutDetailViewComponents(String workoutName, String workoutDescription, List<WorkoutBlock>workoutBlocks){
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.workoutBlocks = workoutBlocks;
    }

    public void setWorkoutNameTextView(String workoutName) {
        this.workoutName = workoutName;
    }

    public void setWorkoutDescriptionTextView(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }
}
