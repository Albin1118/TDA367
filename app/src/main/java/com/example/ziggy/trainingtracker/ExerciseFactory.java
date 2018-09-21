package com.example.ziggy.trainingtracker;

public class ExerciseFactory {

    public Exercise createExercise(String exercise){

        Exercise exercise1= null;

        if (exercise.equals("CardioExercise")){
            exercise1 = new CardioExercise("name", "description");
        }
        else if(exercise.equals("BodyWeightExercise")){
            exercise1 = new BodyWeightExercise("name", "description");

        }else if(exercise.equals("LiftingExercise")){
            exercise1 = new LiftingExercise("name", "description");
        }


        return exercise1;
    }
}
