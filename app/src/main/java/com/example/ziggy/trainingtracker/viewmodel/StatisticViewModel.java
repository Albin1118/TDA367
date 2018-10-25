package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseStatistic;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
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
        exercises.clear();
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
            for(int j=0; j<block.getMultiplier(); j++) {

                int reps = block.getAmounts().get(i);
                int sets = block.getMultiplier();
                IExercise exercise = (block.getExercises().get(i));
                exercisesInBlock.add(new ExerciseStatistic(exercise, reps, sets));
            }
        }
        return exercisesInBlock;
    }

    /**
     * Iterates though the complete list of ExerciseStatistics and creates a new list containing those matching the enclosed parameters
     * @param exerciseName Name of the Exercises sought for
     * @return A list of the exerciseStatistics matching the enclosed parameters
     */

    public List<ExerciseStatistic>getStatisticsFromSearch(String exerciseName, int sets, int reps) {
        List<ExerciseStatistic> exercisesContainingFields = new ArrayList<>();
        for(ExerciseStatistic es : exercises) {
            if(es.getExerciseName().equals(exerciseName)) {
                exercisesContainingFields.add(es);
            }
        }
        return exercisesContainingFields;
    }

    /**
     * Creates a linegraph series form a list of ExerciseStatistics with respect on amount of repetitions
     * @param statisticsList The list wanted to be converted into a series of DataPoints
     * @return The converted list
     */
    public LineGraphSeries<DataPoint> DataPointsFromStatisticsList(List<ExerciseStatistic> statisticsList) {
        LineGraphSeries<DataPoint> dataPointsFromExercises = new LineGraphSeries<>();
        for (int i = 0; i < statisticsList.size(); i++) {
            int y = statisticsList.get(i).getReps();
            dataPointsFromExercises.appendData(new DataPoint(i, y),true, statisticsList.size());
        }
        return dataPointsFromExercises;
    }

}
