package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Workout, with a name, a description and a list of WorkoutBlocks,
 * containing exercises and their respective amounts
 */
public class Workout implements IWorkout{

    @SerializedName("workout_name")
    private String name;
    @SerializedName("workout_description")
    private String description;
    @SerializedName("workout_blocks")
    private List<IWorkoutBlock> blocks;

    public Workout(String name, String description, List<IWorkoutBlock> blocks) {
        this.name = name;
        this.description = description;
        this.blocks = blocks;
    }

    public Workout() {
    }

    /**
     * Add a block to the workout
     * @param multiplier The amount of sets
     * @param exercises The block exercises
     * @param amounts The amounts for all the exercises
     */
    @Override
    public void addBlock(int multiplier, List<IExercise> exercises, List<Integer> amounts, List<Double> weights) {
        IWorkoutBlock block = new WorkoutBlock();
        block.setMultiplier(multiplier);
        block.setExercises(exercises, amounts);
        block.setWeights(weights);
        blocks.add(block);
    }

    /**
     * Remove a block from the workout
     * @param index The index of the block to remove
     */
    @Override
    public void removeBlock(int index){
        blocks.remove(index);
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * @return the workout blocks
     */
    @Override
    public List<IWorkoutBlock> getBlocks() {
        return blocks;
    }

    /**
     * @return the amount of blocks
     */
    @Override
    public int getNumberOfBlocks(){
        return blocks.size();
    }

    /**
     * @param name The name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description The description to set
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param blocks The workout blocks to set
     */
    @Override
    public void setBlocks(List<IWorkoutBlock> blocks) {
        this.blocks = blocks;
    }

    @Override
    public String toString(){
        return name + " - " + description + " - " + blocks.size() + " blocks";
    }

}
