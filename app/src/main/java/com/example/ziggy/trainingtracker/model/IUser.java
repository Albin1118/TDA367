package com.example.ziggy.trainingtracker.model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public interface IUser {


    void addWorkoutToStatistics(IWorkout workout);

    void addFinishedWorkout(IWorkout finishedWorkout);

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

    List<IExercise> getCustomExercises();

    List<IWorkout> getCustomWorkouts();

    List<IWorkout> getFinishedWorkouts();

    List<IChallenge> getFinishedChallenges();

    List<ExerciseStatistic> getExerciseStatistics();

    List <IExercise> getExercisesWithStatisticsAvailable();

    LinkedHashMap<Date, Double> generateStatisticsForExercise(IExercise e, int sets, int reps);

    /**
     * Updates the status of all the achievements before returning them.
     * @return the list all the achievements
     */
    List<Achievement> getAchievements();

    void setCustomExercises(List<IExercise> customExercises);

    void setCustomWorkouts(List<IWorkout> customWorkouts);

    void setWeight(double weight);
}
