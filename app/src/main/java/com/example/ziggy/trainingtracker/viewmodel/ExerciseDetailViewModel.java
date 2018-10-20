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
     * Remove the current exercise from the custom exercises.
     */
    public void removeExercise() {
        model.removeCustomExercise(exercise);
    }

    /**
     * Check if the current exercise is custom made or part of the base exercises.
     * @return True if custom, false if base exercise
     */
    public boolean isCustomExercise() {
        return model.getCustomExercises().contains(exercise);
    }

    public IExercise getExercise() {
        return this.exercise;
    }
}
