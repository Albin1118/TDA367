package com.example.ziggy.trainingtracker.model;

import com.example.ziggy.trainingtracker.model.Achievements.Achievement;

import java.util.List;

public interface IUser {

    void addCustomExercise(IExercise e);

    void removeCustomExercise(IExercise e);

    void addCustomWorkout(IWorkout w);

    void removeCustomWorkout(IWorkout w);

    void addAchievement(Achievement achievement);

    String getUsername();

    String getName();

    double getWeight();

    int getHeight();

    List<IWorkout> getFinishedWorkouts();

    List<IWorkout> getCustomWorkouts();

    List<IExercise> getCustomExercises();

    /**
     * Updates the status of all the achievements before returning them.
     * @return the list all the achievements
     */
    List<Achievement> getAchievements();

    void setCustomExercises(List<IExercise> customExercises);

    void setCustomWorkouts(List<IWorkout> customWorkouts);

    void setWeight(double weight);
}
