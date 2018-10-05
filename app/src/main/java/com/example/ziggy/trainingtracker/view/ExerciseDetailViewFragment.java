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

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;

/**
 * Fragment representing a view displaying contents of a selected exercise
 */
public class ExerciseDetailViewFragment extends Fragment {

    private MainActivity parentActivity;

    private String exerciseName = "Exercise name";
    private String exerciseDescription = "Exercise description";
    private String exerciseInstructions = "Exercise instructions";

    private TextView exerciseNameTextView;
    private TextView exerciseDescriptionTextView;
    private TextView exerciseInstructionsTextView;

    private Button removeExerciseButton;
    private Button editExerciseButton;

    private View view;
    private Exercise exercise = new Exercise("name", "description", "instructions", "unit");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_detail_view, container, false);
        parentActivity = ((MainActivity)getActivity());
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView);
        exerciseNameTextView.setText(exercise.getName());

        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
        exerciseDescriptionTextView.setText(exercise.getDescription());

        exerciseInstructionsTextView = view.findViewById(R.id.exerciseInstructionsTextView);
        exerciseInstructionsTextView.setText(exercise.getInstructions());

        //TODO: hide these if it is not a custom Exercise
        removeExerciseButton = view.findViewById(R.id.removeExerciseButton);
        editExerciseButton = view.findViewById(R.id.editExerciseButton);
    }

    private void initListeners() {
        removeExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.viewModel.removeCustomExercise(exercise);
                parentActivity.setFragmentContainerContent(new ExerciseTabFragment());
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refreshes the fragment if it's currently visible to the user
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setReorderingAllowed(false);
            ft.detach(this).attach(this).commit();
        }
    }

    /**
     * Set Exercise to be displayed.
     * This method needs to be called before creating the view or there will be a NullPointerException in initViews.
     * @param e The Exercise to be displayed
     */
    public void setExercise(Exercise e) {
        this.exercise = e;
    }
}
