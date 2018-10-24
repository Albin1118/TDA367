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

    @Override
    public String toString(){
        return name + " - " + description + " - " + blocks.size() + " blocks";
    }

    @Override
    public void addBlock(IWorkoutBlock block) {
        blocks.add(block);
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public List<IWorkoutBlock> getBlocks() {
        return blocks;
    }
    @Override
    public String getNumberofBlocks(){
        return blocks.size() + " blocks";
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public void setBlocks(List<IWorkoutBlock> blocks) {
        this.blocks = blocks;
    }
}
