package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

public class TrainingTracker {
    private User user = new User("username", "name", 70, 180);
    private List<Workout> workouts = new ArrayList<>();
    private List<Exercise> exercises = new ArrayList<>();

    public TrainingTracker() {

    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
    public List<Exercise> getExercises() {
        return exercises;
    }

    public User getUser() {
        return user;
    }
}
