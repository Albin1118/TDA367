package com.example.ziggy.trainingtracker.model;

import com.example.ziggy.trainingtracker.service.IWorkoutDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = IWorkoutDeserializer.class)
public interface IWorkout {

    /**
     * Add a block to the workout
     * @param multiplier The amount of sets
     * @param exercises The block exercises
     * @param amounts The amounts for all the exercises
     */
    void addBlock(int multiplier, List<IExercise> exercises, List<Integer> amounts, List<Double> weights);

    /**
     * Remove a block from the workout
     * @param index The index of the block to remove
     */
    void removeBlock(int index);

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @return the workout blocks
     */
    List<IWorkoutBlock> getBlocks();

    /**
     * @return the amount of blocks
     */
    int getNumberOfBlocks();

    /**
     * @param name The name to set
     */
    void setName(String name);

    /**
     * @param description The description to set
     */
    void setDescription(String description);

    /**
     * @param blocks The workout blocks to set
     */
    void setBlocks(List<IWorkoutBlock> blocks);

    String toString();
}
