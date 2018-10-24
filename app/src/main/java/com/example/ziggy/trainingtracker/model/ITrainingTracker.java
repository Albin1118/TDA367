package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

public interface ITrainingTracker {

    /**
     * Adds an Exercise to the user's list of custom Exercises.
     * @param e Exercise to be added
     */
    void addCustomExercise(IExercise e);

    /**
     * Removes an Exercise from the user's list of custom Exercises.
     * @param e Exercise to be removed
     */
    void removeCustomExercise(IExercise e);


    /**
     * Adds a Workout to the user's list of custom Workouts.
     * @param w Workout to be added
     */
    void addCustomWorkout(IWorkout w);


    /**
     * Removes a Workout from the user's list of custom Workouts.
     * @param w Workout to be removed
     */
    void removeCustomWorkout(IWorkout w);

    /**
     * @param baseExercises the list of exercises to add to the base exercises
     */
    void loadBaseExercises(List<IExercise> baseExercises);

    /**
     * @param baseWorkouts the list of workouts to add to the base workouts
     */
    void loadBaseWorkouts(List<IWorkout> baseWorkouts);

    /**
     * @param baseChallenges the list of challenges to add to the base challenges
     */
    void loadBaseChallenges(List<IChallenge> baseChallenges);

    /**
     * Checks if specified exercise is custom made by the user.
     * @param e Exercise to be checked
     * @return True if custom made
     */
    boolean checkIfCustom(IExercise e);

    /**
     * Checks if specified workout is custom made by the user.
     * @param w Workout to be checked
     * @return True if custom made
     */
    boolean checkIfCustom(IWorkout w);

    /**
     * @return an unmodifiable list of the base exercises + the users custom exercises
     */
    List<IExercise> getExercises();

    /**
     * @return an unmodifiable list of the base workouts + the users custom workouts
     */
    List<IWorkout> getWorkouts();

    List<IChallenge> getChallenges();

    void setCustomWorkouts(List<IWorkout> w);

    void setCustomExercises(List<IExercise> e);

    void setWorkouts(List<IWorkout> w);

    void setExercises(List<IExercise> e);

    IUser getUser();

    void setUser(User user);
}
