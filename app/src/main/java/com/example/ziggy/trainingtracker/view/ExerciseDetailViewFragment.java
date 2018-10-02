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

public class ExerciseDetailViewFragment extends Fragment {

    private TextView exerciseNameTextView;
    private  TextView exerciseDescriptionTextView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_detail_view, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView);
        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
    }

    private void initListeners() {

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

    public void setExerciseNameTextView(String workoutName) {
        this.exerciseNameTextView.setText(workoutName);
    }

    public void setExerciseDescriptionTextView(String workoutDescription) {
        this.exerciseDescriptionTextView.setText(workoutDescription);
    }
}
