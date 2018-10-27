package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;

import java.util.List;

public class WorkoutDetailViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout workout;

    public void init(ITrainingTracker model, IWorkout workout) {
        this.model = model;
        this.workout = workout;
    }

    /**
     * Removes the current workout from the custom workouts.
     */
    public void removeWorkout() {
        model.removeCustomWorkout(workout);
    }

    /**
     * Checks if the current workout is custom made or part of the base workouts.
     * @return True if custom, false if base workout
     */
    public boolean isCustomWorkout() {
        return model.checkIfCustom(workout);
    }

    public IWorkout getWorkout() {
        return workout;
    }

    public String getWorkoutName(){
        return workout.getName();
    }

    public String getWorkoutDescription(){
        return workout.getDescription();
    }

    public List<IWorkoutBlock> getWorkoutBlocks(){
        return workout.getBlocks();
    }
}
