package com.example.ziggy.trainingtracker;

import java.util.List;

public class WorkoutHandler {

    private List<Workout> workouts;

    public WorkoutHandler(List<Workout> workouts) {
        this.workouts = workouts;
    }

    private void createWorkout(List<Exercise>exercises){
        Workout example = new Workout("name", exercises);
        workouts.add(example);
    }

    private void removeWorkout(Workout toRemove){
        workouts.remove(toRemove);
    }

    private void editWorkout(Workout toEdit){
        toEdit.edit();
    }
}
