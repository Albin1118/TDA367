package com.example.ziggy.trainingtracker.model;

import java.util.List;

public class User {

    //Add achievements and goals
    private ActiveWorkout activeWorkout;
    private List<Workout> finishedWorkouts;
    private List<Exercise> customExercises;

    public List<Exercise> getCustomExercises() {
        return customExercises;
    }
}
