package com.example.ziggy.trainingtracker.model;

public class Exercise {

    private String name;
    private String description;
    private String unit;
    private boolean hasWeight;


    public Exercise(String name, String description, String unit) {
        this.name = name;
        this.description = description;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getUnit() {
        return unit;
    }

    @Override
    public String toString(){
        return name + " - " + description;
    }
}
