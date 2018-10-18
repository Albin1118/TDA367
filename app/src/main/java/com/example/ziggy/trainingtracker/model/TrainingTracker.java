package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainingTracker {
    private static TrainingTracker instance = null;
    @SerializedName("trainingtracker_user")
    private User user = new User("Test", "Mr Test", 98.5, 210);
    @SerializedName("trainingtracker_workouts_list")
    private List<Workout> workouts = new ArrayList<>();
    @SerializedName("trainingtracker_exercises_list")
    private List<IExercise> exercises = new ArrayList<>();

    public static TrainingTracker getInstance() {
        if (instance == null) {
            instance = new TrainingTracker();
        }
        return instance;
    }

    private TrainingTracker() {}

    /**
     * Add an Exercise to the list of Exercises and store it among the users custom Exercises.
     * @param e Exercise to be added
     */
    public void addCustomExercise(IExercise e) {
        exercises.add(e);
        user.addCustomExercise(e);
    }

    /**
     * Remove an Exercise from the list of Exercises and discard it from the users custom Exercises.
     * @param e Exercise to be removed
     */
    public void removeCustomExercise(IExercise e) {
        exercises.remove(e);
        user.removeCustomExercise(e);
    }

    /**
     * Add a Workout to the list of Workouts and store it among the users custom Workouts.
     * @param w Workout to be added
     */
    public void addCustomWorkout(Workout w) {
        workouts.add(w);
        user.addCustomWorkout(w);
    }

    /**
     * Remove a Workout from the list of Workouts and discard it from the users custom Workouts.
     * @param w Workout to be removed
     */
    public void removeCustomWorkout(Workout w) {
        workouts.remove(w);
        user.removeCustomWorkout(w);
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }
    public List<IExercise> getExercises() {
        return exercises;
    }
    public List<IExercise> getCustomExercises() {
        return user.getCustomExercises();
    }
    public List<Workout> getCustomWorkouts() {
        return user.getCustomWorkouts();
    }

    public void setCustomWorkouts(List<Workout> w){
        user.setCustomWorkouts(w);
    }

    public void setCustomExercises(List<IExercise> e){
        user.setCustomExercises(e);
    }

    public User getUser() {
        return user;
    }
}
