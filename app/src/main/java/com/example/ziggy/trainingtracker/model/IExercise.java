package com.example.ziggy.trainingtracker.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(contentAs=Exercise.class)
public interface IExercise {

    String getName();
    String getDescription();
    String getInstructions();
    String getUnit();
    List<ExerciseCategory> getCategories();

    void setName(String name);
    void setDescription(String description);
    void setInstructions(String instructions);
    void setUnit(String unit);
    void setCategories(List<ExerciseCategory> categories);

    String toString();
}
