package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    //Add achievements and goals
    @SerializedName("user_active_workout")
    private ActiveWorkout activeWorkout;
    @SerializedName("user_finished_workouts_list")
    private List<IWorkout> finishedWorkouts;
    @SerializedName("user_custom_exercises_list")
    private List<IExercise> customExercises = new ArrayList<>();
    @SerializedName("user_custom_workouts")
    private List<IWorkout> customWorkouts = new ArrayList<>();

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

    void addCustomExercise(IExercise e) {
        customExercises.add(e);
    }

    void removeCustomExercise(IExercise e) {
        customExercises.remove(e);
    }

    void addCustomWorkout(IWorkout w) {
        customWorkouts.add(w);
    }

    void removeCustomWorkout(IWorkout w) {
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

    List<IWorkout> getFinishedWorkouts() {
        return finishedWorkouts;
    }

    public List<IWorkout> getCustomWorkouts() {
        return customWorkouts;
    }

    public List<IExercise> getCustomExercises() {
        return customExercises;
    }

    public void setCustomExercises(List<IExercise> customExercises) {
        this.customExercises = customExercises;
    }

    public void setCustomWorkouts(List<IWorkout> customWorkouts) {
        this.customWorkouts = customWorkouts;
    }

    //Weight might change
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
