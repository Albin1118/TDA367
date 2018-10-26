package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IUser {


    void addActiveWorkoutToStatistics();

    void addActiveWorkoutToFinishedWorkouts();

    void addCustomExercise(IExercise e);

    void removeCustomExercise(IExercise e);

    void addCustomWorkout(IWorkout w);

    void removeCustomWorkout(IWorkout w);

    void addAchievement(Achievement achievement);

    void addChallenge(IChallenge challenge);

    String getUsername();

    String getName();

    double getWeight();

    int getHeight();

    IWorkout getActiveWorkout();

    List<IExercise> getCustomExercises();

    List<IWorkout> getCustomWorkouts();

    List<IWorkout> getFinishedWorkouts();

    List<IChallenge> getFinishedChallenges();





    /**
     * Updates the status of all the achievements before returning them.
     * @return the list all the achievements
     */
    List<Achievement> getAchievements();

    void setCustomExercises(List<IExercise> customExercises);

    void setCustomWorkouts(List<IWorkout> customWorkouts);

    void setActiveWorkout(IWorkout activeWorkout);

    void setWeight(double weight);
}
