package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;

/**
 * Fragment representing a view displaying contents of a selected exercise
 */
public class ExerciseDetailViewFragment extends Fragment {

    private MainActivity parentActivity;

    private TextView exerciseNameTextView;
    private TextView exerciseUnitTextView;
    private TextView exerciseDescriptionTextView;
    private TextView exerciseInstructionsTextView;

    private EditText exerciseNameEditText;
    private EditText exerciseUnitEditText;
    private EditText exerciseDescriptionEditText;
    private EditText exerciseInstructionsEditText;

    private Button removeExerciseButton;
    private Button editExerciseButton;
    private Button cancelEditExerciseButton;
    private Button saveExerciseButton;

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
        exerciseUnitTextView = view.findViewById(R.id.exerciseUnitTextView);
        exerciseDescriptionTextView = view.findViewById(R.id.exerciseDescriptionTextView);
        exerciseDescriptionTextView.setMovementMethod(new ScrollingMovementMethod());
        exerciseInstructionsTextView = view.findViewById(R.id.exerciseInstructionsTextView);
        exerciseInstructionsTextView.setMovementMethod(new ScrollingMovementMethod());
        exerciseNameEditText = view.findViewById(R.id.exerciseNameEditText);
        exerciseUnitEditText = view.findViewById(R.id.exerciseUnitEditText);
        exerciseDescriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
        exerciseInstructionsEditText = view.findViewById(R.id.exerciseInstructionsEditText);
        removeExerciseButton = view.findViewById(R.id.removeExerciseButton);
        editExerciseButton = view.findViewById(R.id.editExerciseButton);
        cancelEditExerciseButton = view.findViewById(R.id.cancelEditExerciseButton);
        saveExerciseButton = view.findViewById(R.id.saveExerciseButton);

        showExerciseInfo();
    }

    private void initListeners() {
        removeExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.viewModel.removeCustomExercise(exercise);
                parentActivity.setFragmentContainerContent(new ExerciseTabFragment());
            }
        });

        editExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditableExerciseInfo();
            }
        });

        cancelEditExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showExerciseInfo();
            }
        });

        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
                showExerciseInfo();
            }
        });
    }

    private void showExerciseInfo() {
        exerciseNameTextView.setText(exercise.getName());
        exerciseUnitTextView.setText(exercise.getUnit());
        exerciseDescriptionTextView.setText(exercise.getDescription());
        exerciseInstructionsTextView.setText(exercise.getInstructions());

        exerciseNameEditText.setVisibility(View.GONE);
        exerciseUnitEditText.setVisibility(View.GONE);
        exerciseDescriptionEditText.setVisibility(View.GONE);
        exerciseInstructionsEditText.setVisibility(View.GONE);
        cancelEditExerciseButton.setVisibility(View.GONE);
        saveExerciseButton.setVisibility(View.GONE);
        exerciseNameTextView.setVisibility(View.VISIBLE);
        exerciseUnitTextView.setVisibility(View.VISIBLE);
        exerciseDescriptionTextView.setVisibility(View.VISIBLE);
        exerciseInstructionsTextView.setVisibility(View.VISIBLE);
        if (parentActivity.viewModel.getCustomExercises().contains(exercise)) {
            removeExerciseButton.setVisibility(View.VISIBLE);
            editExerciseButton.setVisibility(View.VISIBLE);
        }
    }

    private void showEditableExerciseInfo() {
        exerciseNameEditText.setText(exercise.getName());
        exerciseUnitEditText.setText(exercise.getUnit());
        exerciseDescriptionEditText.setText(exercise.getDescription());
        exerciseInstructionsEditText.setText(exercise.getInstructions());

        exerciseNameTextView.setVisibility(View.GONE);
        exerciseUnitTextView.setVisibility(View.GONE);
        exerciseDescriptionTextView.setVisibility(View.GONE);
        exerciseInstructionsTextView.setVisibility(View.GONE);
        editExerciseButton.setVisibility(View.GONE);
        exerciseNameEditText.setVisibility(View.VISIBLE);
        exerciseUnitEditText.setVisibility(View.VISIBLE);
        exerciseDescriptionEditText.setVisibility(View.VISIBLE);
        exerciseInstructionsEditText.setVisibility(View.VISIBLE);
        cancelEditExerciseButton.setVisibility(View.VISIBLE);
        saveExerciseButton.setVisibility(View.VISIBLE);
    }

    private void saveExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        parentActivity.viewModel.editCustomExercise(exercise, name, description, instructions, unit);
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
