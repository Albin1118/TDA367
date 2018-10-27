package com.example.ziggy.trainingtracker.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;

@JsonDeserialize(contentAs=Exercise.class)
public interface IExercise {

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the description
     */
    String getDescription();

    /**
     * @return the instructions
     */
    String getInstructions();

    /**
     * @return the unit (e.g. reps, meters, seconds)
     */
    String getUnit();

    /**
     * @return the exercise's muscle group categories
     */
    List<ExerciseCategory> getCategories();

    /**
     * @return the categories in string format
     */
    String getCategoriesString();

    /**
     * @return true if the exercise is weight based
     */
    boolean isWeightBased();

    /**
     * @param name Name to set
     */
    void setName(String name);

    /**
     * @param description Description to set
     */
    void setDescription(String description);

    /**
     * @param instructions Instructions to set
     */
    void setInstructions(String instructions);

    /**
     * @param unit Unit to set (e.g. reps, meters, seconds)
     */
    void setUnit(String unit);

    /**
     * @param categories Muscle group categories to set
     */
    void setCategories(List<ExerciseCategory> categories);

    /**
     * @param weightBased Set if the exercise is weight based
     */
    void setWeightBased(boolean weightBased);

    String toString();
}
