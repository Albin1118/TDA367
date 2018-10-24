package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ExerciseStatistic;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class StatisticViewModel extends ViewModel {
    ITrainingTracker model;
    List<ExerciseStatistic> exercises;



    public void init(ITrainingTracker model) {
        this.model = model;
        //createStatisticalExercises();
    }

    //TODO Exception for when finished workouts is empty 
    private void createStatisticalExercises() {
        for(IWorkout w : model.getUser().getFinishedWorkouts()) {
            for(IWorkoutBlock wb : w.getBlocks()) {
                for(int i=0; i<wb.getExercises().size(); i++) {

                    int reps = wb.getAmounts().get(i);
                    int multiplier = wb.getMultiplier();
                    String exerciseName = wb.getExercises().get(i).getName();

                    exercises.add(new ExerciseStatistic(reps, multiplier, exerciseName));
                }
            }
        }
    }
}
