package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.model.Workout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutCreatorViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout buildWorkout;
    private IWorkout editableWorkout = null;
    private boolean editMode;

    /**
     * Init for creating mode.
     * @param model The model to work on
     */
    public void init(ITrainingTracker model) {
        this.model = model;
        editMode = false;
        buildWorkout = new Workout("", "", new ArrayList<>());
    }

    /**
     * Init for editing mode.
     * @param model The model to work on
     * @param editableWorkout The workout to edit
     */
    public void init(ITrainingTracker model, IWorkout editableWorkout) {
        this.model = model;
        this.editableWorkout = editableWorkout;
        editMode = true;

        String name = editableWorkout.getName();
        String description = editableWorkout.getDescription();
        List<IWorkoutBlock> blocks = new ArrayList<>(editableWorkout.getBlocks());
        buildWorkout = new Workout(name, description, blocks);
    }

    /**
     * Add the built workout to the list of custom workouts.
     */
    public void createWorkout() {
        model.addCustomWorkout(buildWorkout);
    }

    /**
     * Save the editable workout by getting the values from the built workout.
     */
    public void saveWorkout() {
        editableWorkout.setName(buildWorkout.getName());
        editableWorkout.setDescription(buildWorkout.getDescription());
        editableWorkout.setBlocks(buildWorkout.getBlocks());
    }

    /**
     * Check if editing an existing workout or creating a new one.
     * @return True if in edit mode, false if in create mode
     */
    public boolean isEditMode() {
        return editMode;
    }

    /**
     * Return the workout used to keep the state of the workout being dynamically built.
     * @return The workout being built
     */
    public IWorkout getBuildWorkout() {
        return buildWorkout;
    }
}
