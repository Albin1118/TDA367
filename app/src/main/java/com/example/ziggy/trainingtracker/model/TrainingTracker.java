package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrainingTracker implements ITrainingTracker{
    private static ITrainingTracker instance = null;
    private IUser user = new User("Test", "Mr Test", 98.5, 210);
    private List<IWorkout> baseWorkouts = new ArrayList<>();
    private List<IExercise> baseExercises = new ArrayList<>();
    private List<IChallenge> baseChallenges = new ArrayList<>();

    public TrainingTracker() {

    }

    /**
     * Adds an Exercise to the user's list of custom exercises.
     * @param e Exercise to be added
     */
    public void addCustomExercise(IExercise e) {
        user.addCustomExercise(e);
    }

    /**
     * Removes an Exercise from the user's list of custom Exercises.
     * @param e Exercise to be removed
     */
    public void removeCustomExercise(IExercise e) {
        user.removeCustomExercise(e);
    }

    /**
     * Adds a Workout to the user's list of custom workouts.
     * @param w Workout to be added
     */
    public void addCustomWorkout(IWorkout w) {
        user.addCustomWorkout(w);
    }

    /**
     * Removes a Workout from the user's list of custom Workouts.
     * @param w Workout to be removed
     */
    public void removeCustomWorkout(IWorkout w) {
        user.removeCustomWorkout(w);
    }

    /**
     * @param baseExercises the list of exercises to add to the base exercises
     */
    public void loadBaseExercises(List<IExercise> baseExercises) {
        this.baseExercises.addAll(baseExercises);
    }

    /**
     * @param baseWorkouts the list of workouts to add to the base workouts
     */
    public void loadBaseWorkouts(List<IWorkout> baseWorkouts) {
        this.baseWorkouts.addAll(baseWorkouts);
    }

    /**
     * @param baseChallenges the list of challenges to add to the base challenges
     */
    public void loadBaseChallenges(List<IChallenge> baseChallenges) {
        this.baseChallenges.addAll(baseChallenges);
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

    /**
     * @return an unmodifiable list of the base exercises + the users custom exercises
     */
    public List<IExercise> getExercises() {
        List<IExercise> allExercises = new ArrayList<>(baseExercises);
        allExercises.addAll(user.getCustomExercises());
        return Collections.unmodifiableList(allExercises);
    }

    /**
     * @return an unmodifiable list of the base workouts + the users custom workouts
     */
    public List<IWorkout> getWorkouts() {
        List<IWorkout> allWorkouts = new ArrayList<>(baseWorkouts);
        allWorkouts.addAll(user.getCustomWorkouts());
        return Collections.unmodifiableList(allWorkouts);
    }

    /**
     * @return an unmodifiable list of the challenges
     */
    public List<IChallenge> getChallenges() {
        return Collections.unmodifiableList(baseChallenges);
    }

    public void setCustomWorkouts(List<IWorkout> w){
        user.setCustomWorkouts(w);
    }

    public void setCustomExercises(List<IExercise> e){ user.setCustomExercises(e);
    }

    public void setWorkouts(List<IWorkout> workouts) {
        this.baseWorkouts = workouts;
    }

    public void setExercises(List<IExercise> exercises) {
        this.baseExercises = exercises;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IUser getUser() {
        return user;
    }
}
