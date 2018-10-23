package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IUser {


    String getUsername();

    String getName();

    double getWeight();

    int getHeight();

    List<IWorkout> getFinishedWorkouts();

    List<IWorkout> getCustomWorkouts();

    List<IExercise> getCustomExercises();

    List<Achievement> getAchievements();

    void setCustomExercises(List<IExercise> customExercises);

    void setCustomWorkouts(List<IWorkout> customWorkouts);

    void setWeight(double weight);
}
