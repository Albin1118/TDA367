package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

public class WorkoutDetailViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout workout;

    public void init(ITrainingTracker model, IWorkout workout) {
        this.model = model;
        this.workout = workout;
    }

    /**
     * Remove the current workout from the custom workouts.
     */
    public void removeWorkout() {
        model.removeCustomWorkout(workout);
    }

    /**
     * Check if the current workout is custom made or part of the base workouts.
     * @return True if custom, false if base workout
     */
    public boolean isCustomWorkout() {
        return model.getCustomWorkouts().contains(workout);
    }

    public IWorkout getWorkout() {
        return workout;
    }
}
