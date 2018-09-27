package com.example.ziggy.trainingtracker.model;

import java.util.List;

public class ExerciseHandler {

    private List<Exercise> exercises;

    public ExerciseHandler(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    private void createExercise(){
        Exercise example = new Exercise("name", "description");
        exercises.add(example);
    }

    private void removeExercise(Exercise toRemove){
        exercises.remove(toRemove);
    }

    private void editExercise(Exercise toEdit){
        toEdit.edit();
    }


}
