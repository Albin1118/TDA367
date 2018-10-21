package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainingTracker implements ITrainingTracker{
    private static ITrainingTracker instance = null;
    @SerializedName("trainingtracker_user")
    private User user = new User("Test", "Mr Test", 98.5, 210);
    @SerializedName("trainingtracker_workouts_list")
    private List<IWorkout> workouts = new ArrayList<>();
    @SerializedName("trainingtracker_exercises_list")
    private List<IExercise> exercises = new ArrayList<>();
    private List<IChallenge> challenges = new ArrayList<>();

    public TrainingTracker() {
    }

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
    public void addCustomWorkout(IWorkout w) {
        workouts.add(w);
        user.addCustomWorkout(w);
    }

    /**
     * Remove a Workout from the list of Workouts and discard it from the users custom Workouts.
     * @param w Workout to be removed
     */
    public void removeCustomWorkout(IWorkout w) {
        workouts.remove(w);
        user.removeCustomWorkout(w);
    }

    public List<IWorkout> getWorkouts() {
        return workouts;
    }
    public List<IExercise> getExercises() {
        return exercises;
    }
    public List<IExercise> getCustomExercises() {
        return user.getCustomExercises();
    }
    public List<IWorkout> getCustomWorkouts() {
        return user.getCustomWorkouts();
    }
    public List<IChallenge> getChallenges() {
        return challenges;
    }

    public void setCustomWorkouts(List<IWorkout> w){
        user.setCustomWorkouts(w);
    }

    public void setCustomExercises(List<IExercise> e){
        user.setCustomExercises(e);
    }

    public IUser getUser() {
        return user;
    }
}
