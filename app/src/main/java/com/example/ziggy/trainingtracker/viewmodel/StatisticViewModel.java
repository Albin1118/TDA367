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
    List<ExerciseStatistic> exercises = new ArrayList<>();



    public void init(ITrainingTracker model) {
        this.model = model;
        createStatisticalExercises();
    }


    /**
     * Takes finished workouts in model, converts them to ExerciseStatistic objects and adds them to the list of ExerciseStatistics
     */

    private void createStatisticalExercises() {
        try {
            for (IWorkout workout : model.getUser().getFinishedWorkouts()) {
                for (IWorkoutBlock block : workout.getBlocks()) {
                    exercises.addAll(blockToStatisticalExercises(block));
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No finished workouts available");
        }
    }

    /**
     * Takes a WorkoutBlock and converts its exercises to ExerciseStatistic objects
     * @param block The Workout block that will be converted
     * @return List of the converted ExerciseStatistic objects
     */

    private List<ExerciseStatistic> blockToStatisticalExercises(IWorkoutBlock block) {
        List<ExerciseStatistic> exercisesInBlock = new ArrayList<>();
        for(int i = 0; i<block.getExercises().size(); i++) {

            int reps = block.getAmounts().get(i);
            int multiplier = block.getMultiplier();
            String exerciseName = block.getExercises().get(i).getName();
            exercisesInBlock.add(new ExerciseStatistic(reps, multiplier, exerciseName));
        }
        return exercisesInBlock;
    }
}
