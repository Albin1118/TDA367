package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Add achievements and goals
    @SerializedName("user_active_workout")
    private ActiveWorkout activeWorkout;
    @SerializedName("user_finished_workouts_list")
    private List<Workout> finishedWorkouts;
    @SerializedName("user_custom_exercises_list")
    private List<Exercise> customExercises = new ArrayList<>();
    @SerializedName("user_custom_workouts")
    private List<Workout> customWorkouts = new ArrayList<>();

    private String username;

    //Data related to human qualities of user
    @SerializedName("user_name")
    private String name;
    @SerializedName("user_weight")
    private double weight;
    @SerializedName("user_height")
    private int height;


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

    public List<Workout> getCustomWorkouts() {
        return customWorkouts;
    }

    public List<Exercise> getCustomExercises() {
        return customExercises;
    }

    public void setCustomExercises(List<Exercise> customExercises) {
        this.customExercises = customExercises;
    }

    public void setCustomWorkouts(List<Workout> customWorkouts) {
        this.customWorkouts = customWorkouts;
    }

    //Weight might change
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
