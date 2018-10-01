package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Add achievements and goals
    private ActiveWorkout activeWorkout;
    private List<Workout> finishedWorkouts;
    private List<Exercise> customExercises = new ArrayList<Exercise>();

    public List<Exercise> getCustomExercises() {
        return customExercises;
    }
}
