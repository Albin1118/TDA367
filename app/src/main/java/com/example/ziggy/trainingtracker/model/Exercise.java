package com.example.ziggy.trainingtracker.model;

public class Exercise {

    private String name;
    private String description;
    private String instructions;
    private String unit;
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
