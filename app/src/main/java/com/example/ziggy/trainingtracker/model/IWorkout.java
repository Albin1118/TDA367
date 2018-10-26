package com.example.ziggy.trainingtracker.model;

import com.example.ziggy.trainingtracker.service.IWorkoutDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(using = IWorkoutDeserializer.class)
public interface IWorkout {

    String toString();

    void addBlock(int multiplier, List<IExercise> exercises, List<Integer> amounts);
    void removeBlock(int index);

    String getName();
    String getDescription();
    List<IWorkoutBlock> getBlocks();
    String getNumberofBlocks();
    int getBlockListSize();

    void setName(String name);
    void setDescription(String description);
    void setBlocks(List<IWorkoutBlock> blocks);
}
