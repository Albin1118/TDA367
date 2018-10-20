package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IWorkout {

    String toString();

    void addBlock(IWorkoutBlock block);

    String getName();
    String getDescription();
    List<IWorkoutBlock> getBlocks();
    String getNumberofBlocks();

    void setName(String name);
    void setDescription(String description);
    void setBlocks(List<IWorkoutBlock> blocks);
}
