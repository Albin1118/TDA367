package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

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
        model.finishWorkout(activeWorkout);
        clearElapsedTime();
    }

    public void addWorkoutToStatistics(){
        model.getUser().addWorkoutToStatistics(activeWorkout);
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

    private void clearElapsedTime(){
        elapsedTime = 0;
    }

    public void saveElapsedTime(int elapsedTime){
        this.elapsedTime = elapsedTime;
    }

    /**
     * Due to the chronometer saving the elapsedtime in millis and the starting time chronometer TextView taking a string as input,
     * we will have to do some conversions from millis to whole seconds and format a string using XX:XX format
     *
     * @return Formatted String suitable for the chronometer TextView
     */
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
