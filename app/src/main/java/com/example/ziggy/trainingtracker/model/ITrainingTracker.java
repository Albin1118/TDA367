package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

public interface ITrainingTracker {

    /**
     * Adds an Exercise to the list of Exercises and stores it among the users custom Exercises.
     * @param e Exercise to be added
     */
    void addCustomExercise(IExercise e);

    /**
     * Removes an Exercise from the list of Exercises and discards it from the users custom Exercises.
     * @param e Exercise to be removed
     */
    void removeCustomExercise(IExercise e);


    /**
     * Adds a Workout to the list of Workouts and stores it among the users custom Workouts.
     * @param w Workout to be added
     */
    void addCustomWorkout(IWorkout w);


    /**
     * Removes a Workout from the list of Workouts and discards it from the users custom Workouts.
     * @param w Workout to be removed
     */
    void removeCustomWorkout(IWorkout w);

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

    List<IWorkout> getWorkouts();
    List<IExercise> getExercises();
    List<IExercise> getCustomExercises();
    List<IWorkout> getCustomWorkouts();
    List<IChallenge> getChallenges();

    void setCustomWorkouts(List<IWorkout> w);

    void setCustomExercises(List<IExercise> e);

    void setWorkouts(List<IWorkout> w);

    void setExercises(List<IExercise> e);

    IUser getUser();

    void setUser(User user);
}
