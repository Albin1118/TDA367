package com.example.ziggy.trainingtracker.model;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private String name;
    private String description;
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

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public List<WorkoutBlock> getBlocks() {
        return blocks;
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
