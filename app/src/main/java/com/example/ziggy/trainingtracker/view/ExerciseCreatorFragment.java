package com.example.ziggy.trainingtracker.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;

import java.util.ArrayList;
import java.util.List;

import com.example.ziggy.trainingtracker.viewmodel.ExerciseCreatorViewModel;

/**
 * Fragment representing a view where the user can create custom exercises
 */
public class ExerciseCreatorFragment extends Fragment {

    private EditText exerciseNameEditText;
    private EditText exerciseUnitEditText;
    private EditText exerciseDescriptionEditText;
    private EditText exerciseInstructionsEditText;
    private Spinner exerciseCategorySpinner;
    private Button createExerciseButton;
    private Button saveExerciseButton;
    private Button cancelEditExerciseButton;

    private View view;
    private ExerciseCreatorViewModel viewModel;
    private NavigationManager navigator;

    List<CategorySpinnerObject> categories = new ArrayList<>();

    public static ExerciseCreatorFragment newInstance(ExerciseCreatorViewModel viewModel, NavigationManager navigator) {
        ExerciseCreatorFragment fragment = new ExerciseCreatorFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ExerciseCreatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
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
        exerciseNameEditText = view.findViewById(R.id.exerciseNameEditText);
        exerciseUnitEditText = view.findViewById(R.id.exerciseUnitEditText);
        exerciseDescriptionEditText = view.findViewById(R.id.exerciseDescriptionEditText);
        exerciseInstructionsEditText = view.findViewById(R.id.exerciseInstructionsEditText);
        createExerciseButton = view.findViewById(R.id.createExerciseButton);
        saveExerciseButton = view.findViewById(R.id.saveExerciseButton);
        cancelEditExerciseButton = view.findViewById(R.id.cancelEditExerciseButton);
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);

        categories.add(new CategorySpinnerObject("Select category"));
        for (String category : viewModel.getCategories()) {
            categories.add(new CategorySpinnerObject(category));
        }
        CategorySpinnerAdapter adapter = new CategorySpinnerAdapter(getContext(), 0, categories);
        exerciseCategorySpinner.setAdapter(adapter);

        if (viewModel.isEditMode()) {
            editMode();
        }
    }

    private void initListeners() {
        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NecessaryFieldsFilled()) {
                    createExercise();
                    navigator.goBack();
                    Toast.makeText(getContext(), "New exercise created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "You need to choose name, unit and category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NecessaryFieldsFilled()) {
                    saveExercise();
                    navigator.goBack();
                } else {
                    Toast.makeText(getContext(), "Add both name and category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelEditExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.goBack();
            }
        });
    }

    private void editMode() {
        saveExerciseButton.setVisibility(View.VISIBLE);
        cancelEditExerciseButton.setVisibility(View.VISIBLE);
        createExerciseButton.setVisibility(View.GONE);

        exerciseNameEditText.setText(viewModel.getEditableExercise().getName());
        exerciseDescriptionEditText.setText(viewModel.getEditableExercise().getDescription());
        exerciseInstructionsEditText.setText(viewModel.getEditableExercise().getInstructions());
        exerciseUnitEditText.setText(viewModel.getEditableExercise().getUnit());
    }

    private void createExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        List<String> categories = categoriesSelected();

        viewModel.createExercise(name, unit, description, instructions, categories);
    }

    private void saveExercise() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        List<String> categories = categoriesSelected();

        viewModel.saveExercise(name, unit, description, instructions, categories);
    }

    /**
     * Returns a list of the categories that are checked in the category spinner
     * @return A list of Strings representing the selected categories
     */
    private List<String> categoriesSelected() {
        List<String> categoriesSelected = new ArrayList<>();
        for (CategorySpinnerObject categorySpinnerObject : categories) {
            if (categorySpinnerObject.isCategorySelected()) {
                categoriesSelected.add(categorySpinnerObject.getCategoryName());
            }
        }
        return categoriesSelected;
    }

    /**
     * Checks if a name, a unit and a category has been selected
     * @return True if all these have been filled in, false if not
     */
    private boolean NecessaryFieldsFilled() {
        return !exerciseNameEditText.getText().toString().isEmpty() && !exerciseUnitEditText.getText().toString().isEmpty() && !categoriesSelected().isEmpty();
    }
}
