package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

import java.util.ArrayList;
import java.util.List;

public class WorkoutTabViewModel extends ViewModel {
    private ITrainingTracker model;
    private List<IWorkout> workouts;

    public void init(ITrainingTracker model) {
        this.model = model;
        workouts = new ArrayList<>(model.getWorkouts());
    }

    public List<IWorkout> getWorkouts() {
        return workouts;
    }

    public IWorkout getWorkout(int index){
        return workouts.get(index);
    }

    public List<IWorkout> getAllWorkouts() {
        workouts.clear();
        workouts.addAll(model.getWorkouts());
        return workouts;
    }
}
