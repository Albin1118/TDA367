package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IWorkoutBlock {

    void addExercise(IExercise exercise, Integer amount);
    void removeExercise(IExercise exercise);

    void setMultiplier(int n);

    List<IExercise> getExercises();
    List<Integer> getAmounts();
    int getMultiplier();
    String getMultiplierString();
    Boolean isEmpty();

    String toString();
}
