package com.example.ziggy.trainingtracker.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Add achievements and goals
    private ActiveWorkout activeWorkout;
    private List<Workout> finishedWorkouts;
    private List<Exercise> customExercises = new ArrayList<>();
    private List<Workout> customWorkouts = new ArrayList<>();

    private String username;

    //Data related to human qualities of user
    private String name;
    private double weight;
    private int height;
    //TODO is age really relevant?
    private int age;


    public User(String username, String name, double weight, int height) {
        this.username = username;
        this.name = name;
        this.weight = weight;
        this.height = height;
    }

    void addCustomExercise(Exercise e) {
        customExercises.add(e);
    }

    void removeCustomExercise(Exercise e) {
        customExercises.remove(e);
    }

    void addCustomWorkout(Workout w) {
        customWorkouts.add(w);
    }

    void removeCustomWorkout(Workout w) {
        customWorkouts.remove(w);
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    List<Workout> getFinishedWorkouts() {
        return finishedWorkouts;
    }

    List<Workout> getCustomWorkouts() {
        return customWorkouts;
    }

    List<Exercise> getCustomExercises() {
        return customExercises;
    }

    //Weight might change
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
