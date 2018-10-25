package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing an exercise, with a name, unit, description...
 */
public class Exercise implements IExercise {

    @SerializedName("exercise_name")
    private String name;
    @SerializedName("exercise_unit")
    private String unit;
    @SerializedName("exercise_description")
    private String description;
    @SerializedName("exercise_instructions")
    private String instructions;
    @SerializedName("exercise_has_weight")
    private boolean hasWeight;
    @SerializedName("exercise_category")
    private List<ExerciseCategory> categories = new ArrayList<ExerciseCategory>();


    public Exercise(String name, String unit, String description, String instructions, List<ExerciseCategory> categories) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.unit = unit;
        this.categories = categories;
    }

    public Exercise() {
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getInstructions() {
        return instructions;
    }
    public String getUnit() {
        return unit;
    }
    public List<ExerciseCategory> getCategories() {
        return categories;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public void setCategories(List<ExerciseCategory> categories) {
        this.categories = categories;
    }
    public void setHasWeight(boolean hasWeight) {
        this.hasWeight = hasWeight;
    }

    @Override
    public String toString(){
        return name + " - " + description;
    }
}
