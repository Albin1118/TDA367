package com.example.ziggy.trainingtracker.model;

import java.util.List;

public class Workout {

    private String name;
    private String description;
    private List<Exercise> exercises;

    public Workout(String name, String description, List<Exercise> exercises) {
        this.name = name;
        this.description = description;
        this.exercises = exercises;
    }

    public void edit(){

    }

    @Override
    public String toString(){
        return name + " - " + description + " - " + exercises.size() + " exercises";
    }
}
