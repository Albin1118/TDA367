package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IWorkout {

    String toString();

    void addBlock(WorkoutBlock block);

    String getName();
    String getDescription();
    List<WorkoutBlock> getBlocks();
    String getNumberofBlocks();

    void setName(String name);
    void setDescription(String description);
    void setBlocks(List<WorkoutBlock> blocks);
}
