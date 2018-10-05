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

public class CustomExerciseDetailViewFragment extends Fragment{
    private int exerciseShownIndex;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_custom_exercise_detail_view, container, false);
        parentActivity = ((MainActivity)getActivity());
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        exerciseNameTextView = view.findViewById(R.id.exerciseNameTextView);
        exerciseNameTextView.setText(exerciseName);

        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
        exerciseDescriptionTextView.setText(exerciseDescription);

        exerciseInstructionsTextView = view.findViewById(R.id.exerciseInstructionsTextView);
        exerciseInstructionsTextView.setText(exerciseInstructions);

        removeExerciseButton = view.findViewById(R.id.removeExerciseButton);
        editExerciseButton = view.findViewById(R.id.editExerciseButton);
    }

    private void initListeners() {
        removeExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.viewModel.removeCustomExercise(exerciseShownIndex);
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
     * The method is called from the mainActivity and sets the instance variables of the detailView
     * so that the fragment's contents will be updated next time onCreate() is called.
     * @param  exerciseName of the selected exercise
     * @param exerciseDescription description of the selected exercise
     * @param exerciseInstructions instructions for the selected exercise
     */



    public void setExerciseDetailViewComponents(String exerciseName, String exerciseDescription, String exerciseInstructions){
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseInstructions = exerciseInstructions;
    }

    public void getCurrentExerciseIndex(int index) {
        exerciseShownIndex = index;
    }

    public void setExerciseNameTextView(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseDescriptionTextView(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }
}
