package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing the user of the application, containing data related to individual use such as
 * the custom made exercises and workouts, the completed workouts and user info.
 */
public class User implements IUser {

    //Add achievements and goals
    private List<IWorkout> finishedWorkouts = new ArrayList<>();
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
        addAchievement(new CreatedExercisesAchievement());
        addAchievement(new FinishedWorkoutsAchievement());
    }

    @Override
    public void addCustomExercise(IExercise e) {
        customExercises.add(e);
    }

    @Override
    public void removeCustomExercise(IExercise e) {
        customExercises.remove(e);
    }

    @Override
    public void addCustomWorkout(IWorkout w) {
        customWorkouts.add(w);
    }

    @Override
    public void removeCustomWorkout(IWorkout w) {
        customWorkouts.remove(w);
    }

    @Override
    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public List<IWorkout> getFinishedWorkouts() {
        return Collections.unmodifiableList(finishedWorkouts);
    }

    @Override
    public List<IExercise> getCustomExercises() {
        return Collections.unmodifiableList(customExercises);
    }

    @Override
    public List<IWorkout> getCustomWorkouts() {
        return Collections.unmodifiableList(customWorkouts);
    }

    /**
     * Updates the status of all the achievements before returning them.
     * @return the list all the achievements
     */
    @Override
    public List<Achievement> getAchievements() {
        for (Achievement achievement : achievements) {
            achievement.update(this);
        }
        return Collections.unmodifiableList(achievements);
    }

    @Override
    public void setCustomExercises(List<IExercise> customExercises) {
        this.customExercises = customExercises;
    }

    @Override
    public void setCustomWorkouts(List<IWorkout> customWorkouts) {
        this.customWorkouts = customWorkouts;
    }

    //Weight might change
    @Override
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
