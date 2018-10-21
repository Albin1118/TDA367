package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

import java.util.List;

public class ActiveWorkoutViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout activeWorkout;
    private boolean workoutActive;

    public void init(ITrainingTracker model) {
        this.model = model;
    }

    public void init(ITrainingTracker model, IWorkout workout) {
        this.model = model;
        this.activeWorkout = workout;
    }

    public void startWorkout() {
        workoutActive = true;
    }

    public void finishWorkout() {
        workoutActive = false;
    }

    public IWorkout getActiveWorkout() {
        return activeWorkout;
    }

    public boolean isWorkoutActive() {
        return workoutActive;
    }

    public List<IWorkout> getWorkouts() {
        return model.getWorkouts();
    }
}
