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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;

import java.util.ArrayList;
import java.util.List;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.IExercise;
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

    private MainActivity parentActivity;
    private NavigationManager navigationManager;
    private View view;
    private ExerciseCreatorViewModel viewModel;


    List<CategorySpinnerObject> categories = new ArrayList<>();
    private IExercise editableExercise = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = ((MainActivity)getActivity());
        navigationManager = (MainActivity)getActivity();
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

        if (editableExercise != null) {
            editMode();
        }
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);


        for(int i=0; i<parentActivity.viewModel.getCategories().size(); i++) {
            CategorySpinnerObject categorySpinnerObject = new CategorySpinnerObject();
            categorySpinnerObject.setCategoryName(parentActivity.viewModel.getCategoriesToString().get(i));
            categorySpinnerObject.setCategorySelected(false);
            categories.add(categorySpinnerObject);
        }

        CategorySpinnerAdapter adapter;
        adapter = new CategorySpinnerAdapter(getContext(), 0, categories);
        exerciseCategorySpinner.setAdapter(adapter);
    }

    private void initListeners() {
        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NecessaryFieldsFilled()) {
                    createExercise();
                    navigationManager.goBack();
                    Toast.makeText(getContext(), "New exercise created!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Add both name and category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NecessaryFieldsFilled()) {
                    saveExercise();
                    navigationManager.goBack();
                } else {
                    Toast.makeText(getContext(), "Add both name and category", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelEditExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.goBack();
            }
        });
    }

    private void createExercise() {
        parentActivity.viewModel.addCustomExercise(createExerciseFromFields());
    }

    private void saveExercise() {
        parentActivity.viewModel.editCustomExercise(editableExercise, createExerciseFromFields());
    }

    private boolean NecessaryFieldsFilled() {
        return !exerciseNameEditText.getText().toString().isEmpty() && !categoriesSelected().isEmpty();
    }

    private IExercise createExerciseFromFields() {
        String name = exerciseNameEditText.getText().toString();
        String unit = exerciseUnitEditText.getText().toString();
        String description = exerciseDescriptionEditText.getText().toString();
        String instructions = exerciseInstructionsEditText.getText().toString();
        List<ExerciseCategory> categories = categoriesSelected();
        return new Exercise(name, unit, description, instructions, categories);
    }



    //Returns categories checked in the category spinner
    private List<ExerciseCategory> categoriesSelected() {
        List<ExerciseCategory> categoriesSelected = new ArrayList<>();
        for(int i=0; i<categories.size(); i++) {
            if(categories.get(i).isCategorySelected()) {
                categoriesSelected.add(ExerciseCategory.valueOf(categories.get(i).getCategoryName()));
            }
        }
        return categoriesSelected;
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

    public void setEditableExercise(IExercise editableExercise) {
        this.editableExercise = editableExercise;
    }
}
