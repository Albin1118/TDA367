package com.example.ziggy.trainingtracker.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(contentAs=WorkoutBlock.class)
public interface IWorkoutBlock {

    void addExercise(IExercise exercise, int amount);
    void addExercise(IExercise exercise, int amount, double weight);
    void removeExercise(IExercise exercise);
    void setMultiplier(int n);
    void setExercises(List<IExercise> exercises);
    void setAmounts(List<Integer> amounts);
    void setWeights(List<Double> weights);



    List<Double> getWeights();
    List<IExercise> getExercises();
    List<Integer> getAmounts();
    int getMultiplier();
    String getMultiplierString();
    Boolean isEmpty();

    void setExercises(List<IExercise> exercises, List<Integer> amounts);

    String toString();
}
