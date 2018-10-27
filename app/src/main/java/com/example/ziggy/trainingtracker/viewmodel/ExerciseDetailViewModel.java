package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

public class ExerciseDetailViewModel extends ViewModel {
    private ITrainingTracker model;
    private IExercise exercise;

    public void init(ITrainingTracker model, IExercise exercise) {
        this.model = model;
        this.exercise = exercise;
    }

    /**
     * Removes the current exercise from the custom exercises.
     */
    public void removeExercise() {
        model.removeCustomExercise(exercise);
    }

    /**
     * Checks if the current exercise is custom made or part of the base exercises.
     * @return True if custom, false if base exercise
     */
    public boolean isCustomExercise() {
        return model.checkIfCustom(exercise);
    }

    public IExercise getExercise() {
        return this.exercise;
    }

    public String getExerciseName(){
        return exercise.getName();
    }

    public String getExerciseUnit(){
        return exercise.getUnit();
    }

    public String getExerciseCategories(){
        return exercise.getCategoriesString();
    }

    public String getExerciseDescription(){
        return exercise.getDescription();
    }

    public String getExerciseInstructions(){
        return exercise.getInstructions();
    }

}
