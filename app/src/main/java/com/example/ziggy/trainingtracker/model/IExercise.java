package com.example.ziggy.trainingtracker.model;

import java.util.List;

public interface IExercise {

    String getName();
    String getDescription();
    String getInstructions();
    String getUnit();
    List<ExerciseCategory> getCategories();
    String getCategoriesString();
    boolean isWeightBased();
    int getWeight();

    void setName(String name);
    void setDescription(String description);
    void setInstructions(String instructions);
    void setUnit(String unit);
    void setCategories(List<ExerciseCategory> categories);
    void setWeightBased(boolean weightBased);
    void setWeight(int weight);

    String toString();
}
