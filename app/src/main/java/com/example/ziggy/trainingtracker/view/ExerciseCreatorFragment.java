package com.example.ziggy.trainingtracker.view;

import android.arch.lifecycle.ViewModelProviders;
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
import com.example.ziggy.trainingtracker.viewmodel.ExerciseCreatorViewModel;

/**
 * Fragment representing a view where the user can create custom exercises
 */
public class ExerciseCreatorFragment extends Fragment {


    private TextView addedCategoriesTextView;
    private EditText exerciseNameEditText;
    private EditText exerciseUnitEditText;
    private EditText exerciseDescriptionEditText;
    private EditText exerciseInstructionsEditText;
    private Spinner exerciseCategorySpinner;
    private Button createExerciseButton;
    private Button saveExerciseButton;
    private Button cancelEditExerciseButton;
    private Button addCategoryButton;

    private MainActivity parentActivity;
    private View view;
    private ExerciseCreatorViewModel viewModel;

    private ArrayList<ExerciseCategory> categories = new ArrayList<>();
    private Exercise editableExercise = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = ((MainActivity)getActivity());
        viewModel = ViewModelProviders.of(this).get(ExerciseCreatorViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_creator, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        addedCategoriesTextView = view.findViewById(R.id.addedCategoriesTextView);
        exerciseNameEditText = view.findViewById(R.id.exerciseNameEditText);
        exerciseUnitEditText = view.findViewById(R.id.exerciseUnitEditText);
        exerciseDescriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
        exerciseInstructionsEditText = view.findViewById(R.id.exerciseInstructionsEditText);
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        saveExerciseButton = view.findViewById(R.id.saveExerciseButton);
        cancelEditExerciseButton = view.findViewById(R.id.cancelEditExerciseButton);
        addCategoryButton = view.findViewById(R.id.addCategoryButton);

        if (editableExercise != null) {
            editMode();
        }
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);

        ArrayList<String> categories = parentActivity.viewModel.getCategoriesToString();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.parentActivity, android.R.layout.simple_spinner_item, categories);
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
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = exerciseCategorySpinner.getSelectedItem().toString();
                addedCategoriesTextView.setText(addedCategoriesTextView.getText() + "  " + category);
                categories.add(ExerciseCategory.valueOf(category));
            }
        });

    }

    private void createExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        parentActivity.viewModel.addCustomExercise(name, description, instructions, unit, categories);
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
