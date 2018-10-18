package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Workout implements IWorkout{

    @SerializedName("workout_name")
    private String name;
    @SerializedName("workout_description")
    private String description;
    @SerializedName("workout_blocks")
    private List<WorkoutBlock> blocks;

    public Workout(String name, String description, List<WorkoutBlock> blocks) {
        this.name = name;
        this.description = description;
        this.blocks = blocks;
    }

    @Override
    public String toString(){
        return name + " - " + description + " - " + blocks.size() + " blocks";
    }

    public void addBlock(WorkoutBlock block) {
        blocks.add(block);
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<WorkoutBlock> getBlocks() {
        return blocks;
    }
    public String getNumberofBlocks(){
        return blocks.size() + " blocks";
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setBlocks(List<WorkoutBlock> blocks) {
        this.blocks = blocks;
    }
}
