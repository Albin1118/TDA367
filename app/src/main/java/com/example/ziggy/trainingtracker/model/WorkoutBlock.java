package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds a set of exercises together with their respective amount variables and has the variable
 * multiplier to represent how many times to repeat this block of exercises
 */
public class WorkoutBlock implements IWorkoutBlock {

    @SerializedName("workout_block_exercises")
    private List<IExercise> exercises = new ArrayList<>();
    @SerializedName("workout_block_amounts")
    private List<Integer> amounts = new ArrayList<>();
    /**
     * Amount of times to repeat the this block of exercises
     */
    private int multiplier = 1;

    public WorkoutBlock() {
    }

    /**
     * Adds an exercise along with its amount to the block
     * @param exercise exercise to be added to the block
     * @param amount the amount connected to the exercise
     */
    public void addExercise(IExercise exercise, Integer amount) {
        exercises.add(exercise);
        amounts.add(amount);
    }


    /**
     * Removes an exercise along with its amount from the block
     * @param exercise exercise to be removed
     */
    public void removeExercise(IExercise exercise){
        int index = exercises.indexOf(exercise);
        exercises.remove(index);
        amounts.remove(index);
    }

    public void setMultiplier(int n) {
        multiplier = n;
    }

    public void setExercises(List<IExercise> exercises) {
        this.exercises = exercises;
    }
    public void setAmounts(List<Integer> amounts) {
        this.amounts = amounts;
    }

    public List<IExercise> getExercises() {
        return exercises;
    }
    public List<Integer> getAmounts() {
        return amounts;
    }
    public int getMultiplier() {
        return multiplier;
    }
    public String getMultiplierString(){
        return "x" + multiplier;
    }
    public Boolean isEmpty(){
        return exercises.isEmpty();
    }

    @Override
    public String toString() {
        return "WorkoutBlock{" +
                "exercises=" + exercises +
                ", amounts=" + amounts +
                ", multiplier=" + multiplier +
                '}';
    }
}
