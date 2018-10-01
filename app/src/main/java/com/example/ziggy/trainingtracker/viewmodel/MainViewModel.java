package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.Services.ReadExercisesFromXMLService;
import com.example.ziggy.trainingtracker.Services.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.TrainingTracker;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    public MainViewModel() {
        trainingTracker = new TrainingTracker();
        //loadExercises();
        //loadWorkouts();
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
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(trainingTracker.getExercises());
        trainingTracker.getWorkouts().addAll(reader.readWorkouts());
    }

    public List<Exercise> getExercises() {
        return trainingTracker.getExercises();
    }

    public TrainingTracker getTrainingTracker() { //TODO: remove this method, TrainingTracker should not be visible outside this object
        return trainingTracker;
    }

    // Methods for adding removing and editing custom Exercises
    
    public void addCustomExercise(String name, String description, String unit) {
        trainingTracker.getUser().getCustomExercises().add(new Exercise(name, description, unit));
    }

    public void removeCustomExercise(int index) {
        trainingTracker.getUser().getCustomExercises().remove(index);
    }

    public  void editCustomExercise(int index, String name, String description, String unit) {
        trainingTracker.getUser().getCustomExercises().set(index, new Exercise(name, description, unit));
    }
}
