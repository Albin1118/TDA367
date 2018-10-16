package com.example.ziggy.trainingtracker.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;

import java.util.ArrayList;
import com.example.ziggy.trainingtracker.model.Exercise;

/**
 * Fragment representing a view where the user can create custom exercises
 */
public class ExerciseCreatorFragment extends Fragment {


    private Button createExerciseButton;
    private EditText exerciseNameEditText;
    private EditText exerciseUnitEditText;
    private EditText exerciseDescriptionEditText;
    private EditText exerciseInstructionsEditText;
    private Spinner exerciseCategorySpinner;
    private Button saveExerciseButton;
    private Button cancelEditExerciseButton;

    private MainActivity parentActivity;
    private View view;

    private Exercise editableExercise = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity =  ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_exercise_creator, container, false);
        initViews();
        initListeners();


        return view;
    }

    private void initViews() {
        exerciseNameEditText = view.findViewById(R.id.exerciseNameEditText);
        exerciseUnitEditText = view.findViewById(R.id.exerciseUnitEditText);
        exerciseDescriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
        exerciseInstructionsEditText = view.findViewById(R.id.exerciseInstructionsEditText);
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        saveExerciseButton = view.findViewById(R.id.saveExerciseButton);
        cancelEditExerciseButton = view.findViewById(R.id.cancelEditExerciseButton);

        if (editableExercise != null) {
            editMode();
        }
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);

        ExerciseCategory e = new ExerciseCategory();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.parentActivity, android.R.layout.simple_spinner_item, e.getAllCategoriesToString());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseCategorySpinner.setAdapter(adapter);
    }

    private void initListeners() {
        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExercise();
                parentActivity.popBackStack();
                Toast.makeText(getContext(), "New exercise created!", Toast.LENGTH_SHORT).show();
            }
        });

        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExercise();
                parentActivity.popBackStack();
            }
        });

        cancelEditExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.popBackStack();
            }
        });
    }

    private void createExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        String category = exerciseCategorySpinner.getSelectedItem().toString();
        parentActivity.viewModel.addCustomExercise(name, unit, description, instructions, category);
    }

    private void saveExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        parentActivity.viewModel.editCustomExercise(editableExercise, name, description, instructions, unit);
    }

    private void editMode() {
        saveExerciseButton.setVisibility(View.VISIBLE);
        cancelEditExerciseButton.setVisibility(View.VISIBLE);
        createExerciseButton.setVisibility(View.GONE);

        exerciseNameEditText.setText(editableExercise.getName());
        exerciseDescriptionEditText.setText(editableExercise.getDescription());
        exerciseInstructionsEditText.setText(editableExercise.getInstructions());
        exerciseUnitEditText.setText(editableExercise.getUnit());
    }

    public void setEditableExercise(Exercise editableExercise) {
        this.editableExercise = editableExercise;
    }
}
