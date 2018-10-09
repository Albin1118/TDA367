package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.example.ziggy.trainingtracker.service.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.service.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.google.gson.Gson;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;
    private List<Exercise> exercises;
    private List<Workout> workouts;
    private Gson gson;

    public MainViewModel() {
        trainingTracker = new TrainingTracker();
        gson = new Gson();
    }



    public String convertUserToJson(){
        String json = gson.toJson(trainingTracker.getUser());

        return json;
    }

    
    public List<Exercise> getExercises() {
        if (exercises == null) {
            exercises = trainingTracker.getExercises();
            loadExercises();
        }
        return exercises;
    }

    public List<Workout> getWorkouts() {
        if (workouts == null) {
            workouts = trainingTracker.getWorkouts();
            loadWorkouts();
        }
        return workouts;
    }

    public List<Exercise> getCustomExercises() {
        return trainingTracker.getCustomExercises();
    }

    public List<Workout> getCustomWorkouts() {
        return trainingTracker.getCustomWorkouts();
    }

    // Methods for adding removing and editing custom Exercises

    public void addCustomExercise(String name, String description, String instructions, String unit) {
        Exercise e = new Exercise(name, description, instructions, unit);
        trainingTracker.addCustomExercise(e);
    }

    public void removeCustomExercise(Exercise e) {
        trainingTracker.removeCustomExercise(e);
    }

    public  void editCustomExercise(Exercise e, String name, String description, String instructions, String unit) {
        e.setName(name);
        e.setDescription(description);
        e.setInstructions(instructions);
        e.setUnit(unit);
    }

    //Method for adding removing and editing custom Workouts

    public void addCustomWorkout(String name, String description, List<WorkoutBlock> blocks){
        Workout w = new Workout(name, description, blocks);
        trainingTracker.addCustomWorkout(w);
    }

    public void removeCustomWorkout(Workout w) {
        trainingTracker.removeCustomWorkout(w);
    }

    public void editCustomWorkout(Workout w, String name, String description, List<WorkoutBlock> blocks) {
        w.setName(name);
        w.setDescription(description);
        w.setBlocks(blocks);
    }

    /**
     * Loads a list of base exercises from an xml file into the TrainingTracker.
     */
    private void loadExercises() {
        System.out.println("Loading exercises...");
        ReadExercisesFromXMLService reader = new ReadExercisesFromXMLService();
        trainingTracker.getExercises().addAll(reader.readExercises());
    }

    /**
     * Loads a list of base workouts from an xml file into the TrainingTracker.
     */
    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(getExercises());
        trainingTracker.getWorkouts().addAll(reader.readWorkouts());
    }

    public void updateUserWeight(double weight){
        trainingTracker.getUser().setWeight(weight);
    }
}
