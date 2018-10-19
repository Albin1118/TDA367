package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

import java.util.ArrayList;
import java.util.List;

public class ExerciseCreatorViewModel extends ViewModel {
    private ITrainingTracker model;
    private IExercise editableExercise = null;
    private boolean editMode;

    /**
     * Init for creating mode.
     * @param model The model to work on
     */
    public void init(ITrainingTracker model) {
        this.model = model;
        editMode = false;
    }

    /**
     * Init for editing mode.
     * @param model The model to work on
     * @param editableExercise The exercise to edit
     */
    public void init(ITrainingTracker model, IExercise editableExercise) {
        this.model = model;
        this.editableExercise = editableExercise;
        editMode = true;
    }

    /**
     * Returns all the ExerciseCategories.
     * @return A list of Strings representing the categories
     */
    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        for (ExerciseCategory exerciseCategory : ExerciseCategory.values()) {
            categories.add(exerciseCategory.toString());
        }
        return categories;
    }

    /**
     * Create a new exercise and add it to the list of custom exercises.
     * @param name Name for the new exercise
     * @param description Description for the new exercise
     * @param instructions Instructions for the new exercise
     * @param unit Unit for the new exercise
     * @param selectedCategories Categories for the new exercise
     */
    public void createExercise(String name, String description, String instructions, String unit, List<String> selectedCategories) {
        List<ExerciseCategory> exerciseCategories = new ArrayList<>();
        for (String selectedCategory : selectedCategories) {
            exerciseCategories.add(ExerciseCategory.valueOf(selectedCategory));
        }
        IExercise exercise = new Exercise(name, description, instructions, unit, exerciseCategories);
        model.addCustomExercise(exercise);
    }

    /**
     * Edit an existing exercise.
     * @param name New name
     * @param description New description
     * @param instructions New instructions
     * @param unit New unit
     * @param selectedCategories New categories
     */
    public void saveExercise(String name, String description, String instructions, String unit, List<String> selectedCategories) {
        List<ExerciseCategory> exerciseCategories = new ArrayList<>();
        for (String selectedCategory : selectedCategories) {
            exerciseCategories.add(ExerciseCategory.valueOf(selectedCategory));
        }
        editableExercise.setName(name);
        editableExercise.setDescription(description);
        editableExercise.setInstructions(instructions);
        editableExercise.setUnit(unit);
        editableExercise.setCategories(exerciseCategories);
    }

    /**
     * Check if editing an existing exercise or creating a new one.
     * @return True if in edit mode, false if in create mode
     */
    public boolean isEditMode() {
        return editMode;
    }

    public IExercise getEditableExercise() {
        return editableExercise;
    }
}
