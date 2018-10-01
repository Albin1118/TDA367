package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a set of exercises together with their respective amount variables and has the variable
 * multiplier to represent how many times to repeat this block of exercises
 */
public class WorkoutBlock {

    private List<Exercise> exercises = new ArrayList<>();
    private List<Integer> amounts = new ArrayList<>();
    /**
     * Amount of times to repeat the this block of exercises
     */
    private int multiplier = 1;

    public WorkoutBlock() {
    }

    public void addExercise(Exercise exercise, Integer amount) {
        exercises.add(exercise);
        amounts.add(amount);
    }

    public void setMultiplier(int n) {
        multiplier = n;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
    public List<Integer> getAmounts() {
        return amounts;
    }
    public int getMultiplier() {
        return multiplier;
    }
}
