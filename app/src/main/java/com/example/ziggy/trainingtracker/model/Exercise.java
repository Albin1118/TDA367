package com.example.ziggy.trainingtracker.model;

public class Exercise {

    private String name;
    private String description;
    private String unit;
    private boolean hasWeight;


    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return name + " - " + description;
    }
}
