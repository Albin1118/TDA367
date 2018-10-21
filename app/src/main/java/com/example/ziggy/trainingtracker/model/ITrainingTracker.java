package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface ITrainingTracker {
    void addCustomExercise(IExercise e);

    /**
     * Remove an Exercise from the list of Exercises and discard it from the users custom Exercises.
     * @param e Exercise to be removed
     */
    void removeCustomExercise(IExercise e);

    /**
     * Add a Workout to the list of Workouts and store it among the users custom Workouts.
     * @param w Workout to be added
     */
    void addCustomWorkout(IWorkout w);

    /**
     * Remove a Workout from the list of Workouts and discard it from the users custom Workouts.
     * @param w Workout to be removed
     */
    void removeCustomWorkout(IWorkout w);

    List<IWorkout> getWorkouts();
    List<IExercise> getExercises();
    List<IExercise> getCustomExercises();
    List<IWorkout> getCustomWorkouts();
    List<IChallenge> getChallenges();

    void setCustomWorkouts(List<IWorkout> w);

    void setCustomExercises(List<IExercise> e);
    IUser getUser();
}
