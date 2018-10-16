package com.example.ziggy.trainingtracker.model;

import com.google.gson.annotations.SerializedName;

public class Exercise {

    @SerializedName("exercise_name")
    private String name;
    @SerializedName("exercise_description")
    private String description;
    @SerializedName("exercise_instructions")
    private String instructions;
    @SerializedName("exercise_unit")
    private String unit;
    @SerializedName("exercise_has_weight")
    private boolean hasWeight;


    public Exercise(String name, String description, String instructions, String unit) {
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.unit = unit;
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

    @Override
    public String toString(){
        return name + " - " + description;
    }
}
