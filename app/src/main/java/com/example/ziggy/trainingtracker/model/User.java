package com.example.ziggy.trainingtracker.model;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Add achievements and goals
    private ActiveWorkout activeWorkout;
    private List<Workout> finishedWorkouts;
    private List<Exercise> customExercises = new ArrayList<>();
    private MutableLiveData<Exercise> newCustomExercise = new MutableLiveData<>();
    private List<Exercise> customWorkouts = new ArrayList<Exercise>();

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

    public List<Workout> getFinishedWorkouts() {
        return finishedWorkouts;
    }

    public List<Exercise> getCustomWorkouts() {
        return customWorkouts;
    }

    public List<Exercise> getCustomExercises() {
        return customExercises;
    }

    public MutableLiveData<Exercise> getNewCustomExercise() {
        return newCustomExercise;
    }

    //Weight might change
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
