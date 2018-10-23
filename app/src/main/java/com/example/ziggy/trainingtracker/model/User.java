package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the user of the application, containing data related to individual use such as
 * the custom made exercises and workouts, the completed workouts and user info.
 */
public class User implements IUser {

    //Add achievements and goals
    private List<IWorkout> finishedWorkouts;
    private List<IExercise> customExercises = new ArrayList<>();
    private List<IWorkout> customWorkouts = new ArrayList<>();
    private List<Achievement> achievements = new ArrayList<>();

    private String username;

    //Data related to human qualities of user
    @Expose
    @SerializedName("user_name")
    private String name;
    @Expose
    @SerializedName("user_weight")
    private double weight;
    @Expose
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

    public List<Achievement> getAchievements() {
        for (Achievement achievement : achievements) {
            achievement.update(this);
        }
        return achievements;
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
