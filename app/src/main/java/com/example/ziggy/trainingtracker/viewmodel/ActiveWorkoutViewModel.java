package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.support.v4.math.MathUtils;

import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;

import java.util.List;

public class ActiveWorkoutViewModel extends ViewModel {
    private ITrainingTracker model;
    private IWorkout activeWorkout;
    private boolean workoutActive;
    private int elapsedTime;

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

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void clearElapsedTime(){
        elapsedTime = 0;
    }

    public void saveElapsedTime(int elapsedTime){
        this.elapsedTime = elapsedTime;
    }

    public String getFormattedElapsedTime(){
        StringBuilder sb = new StringBuilder();

        if(elapsedTime/60000 <10){
            sb.append("0");
        }

        sb.append(elapsedTime/60000);
        sb.append(":");

        if((elapsedTime/1000)%60 < 10){
            sb.append("0");
        }
        sb.append((elapsedTime/1000)%60);

       return sb.toString();
    }
}
