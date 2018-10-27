package com.example.ziggy.trainingtracker.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface IUser {


    void addWorkoutToStatistics(IWorkout workout);

    /**
     * Add a finished workout to the list of finished workouts.
     * @param finishedWorkout The workout to add
     */
    void addFinishedWorkout(IWorkout finishedWorkout);

    /**
     * Add a new exercise to the list of custom exercises.
     * @param e The exercise to add
     */
    void addCustomExercise(IExercise e);

    /**
     * Remove a custom exercise.
     * @param e The exercise to remove
     */
    void removeCustomExercise(IExercise e);

    /**
     * Add a new workout to the list of custom workouts.
     * @param w The workout to add
     */
    void addCustomWorkout(IWorkout w);

    /**
     * Remove a custom workout.
     * @param w The workout to remove
     */
    void removeCustomWorkout(IWorkout w);

    /**
     * Add a new achievement in progress
     * @param achievement The new achievement
     */
    void addAchievement(Achievement achievement);

    /**
     * Add a finished challenge to the list of finished challenges.
     * @param challenge The challenge to add
     */
    void addChallenge(IChallenge challenge);

    /**
     * @return the username
     */
    String getUsername();

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the user's weight
     */
    double getWeight();

    /**
     * @return the user's height
     */
    int getHeight();

    /**
     * @return the custom exercises
     */
    List<IExercise> getCustomExercises();

    /**
     * @return the custom workouts
     */
    List<IWorkout> getCustomWorkouts();

    /**
     * @return the finished workouts
     */
    List<IWorkout> getFinishedWorkouts();

    /**
     * @return the finished challenges
     */
    List<IChallenge> getFinishedChallenges();

    List<ExerciseStatistic> getExerciseStatistics();

    List <IExercise> getExercisesWithStatisticsAvailable();

    LinkedHashMap<Date, Double> generateStatisticsForExercise(IExercise e, int sets, int reps);

    /**
     * Updates the status of all the achievements before returning them.
     * @return all the achievements
     */
    List<Achievement> getAchievements();

    void setCustomExercises(List<IExercise> customExercises);

    void setCustomWorkouts(List<IWorkout> customWorkouts);

    /**
     * @param weight The user's new weight
     */
    void setWeight(double weight);
}
