package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TrainingTracker implements ITrainingTracker{
    private static ITrainingTracker instance = null;
    private IUser user = new User("Test", "Mr Test", 98.5, 210);
    private List<IWorkout> workouts = new ArrayList<>();
    private List<IExercise> exercises = new ArrayList<>();
    private List<IChallenge> challenges = new ArrayList<>();

    public TrainingTracker() {

    }

    /**
     * Adds an Exercise to the list of Exercises and stores it among the users custom Exercises.
     * @param e Exercise to be added
     */
    public void addCustomExercise(IExercise e) {
        exercises.add(e);
        user.addCustomExercise(e);
    }

    /**
     * Removes an Exercise from the list of Exercises and discards it from the users custom Exercises.
     * @param e Exercise to be removed
     */
    public void removeCustomExercise(IExercise e) {
        exercises.remove(e);
        user.removeCustomExercise(e);
    }

    /**
     * Adds a Workout to the list of Workouts and stores it among the users custom Workouts.
     * @param w Workout to be added
     */
    public void addCustomWorkout(IWorkout w) {
        workouts.add(w);
        user.addCustomWorkout(w);
    }

    /**
     * Removes a Workout from the list of Workouts and discards it from the users custom Workouts.
     * @param w Workout to be removed
     */
    public void removeCustomWorkout(IWorkout w) {
        workouts.remove(w);
        user.removeCustomWorkout(w);
    }

    /**
     * Checks if specified exercise is custom made by the user.
     * @param e Exercise to be checked
     * @return True if custom made
     */
    public boolean checkIfCustom(IExercise e) {
        return user.getCustomExercises().contains(e);
    }

    /**
     * Checks if specified workout is custom made by the user.
     * @param w Workout to be checked
     * @return True if custom made
     */
    public boolean checkIfCustom(IWorkout w) {
        return user.getCustomWorkouts().contains(w);
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

    public void setCustomExercises(List<IExercise> e){ user.setCustomExercises(e);
    }

    public void setWorkouts(List<IWorkout> workouts) {
        this.workouts = workouts;
    }

    public void setExercises(List<IExercise> exercises) {
        this.exercises = exercises;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IUser getUser() {
        return user;
    }
}
