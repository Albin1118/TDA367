package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.Services.LoadExercisesService;
import com.example.ziggy.trainingtracker.Services.ReadWorkoutsFromXMLService;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.TrainingTracker;
import com.example.ziggy.trainingtracker.model.Workout;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    public MainViewModel() {
        trainingTracker = new TrainingTracker();
        loadExercises();
        loadWorkouts();
    }

    /**
     * Loads a list of base exercises from a text file into the TrainingTracker.
     */
    private void loadExercises() {
        System.out.println("Loading exercises...");
        LoadExercisesService les = new LoadExercisesService();
        List<Exercise> loadedExercises = les.loadExercises();
        trainingTracker.getExercises().addAll(loadedExercises);
    }

    private void loadWorkouts() {
        System.out.println("Loading workouts...");
        ReadWorkoutsFromXMLService reader = new ReadWorkoutsFromXMLService(trainingTracker.getExercises());
        List<Workout> readWorkouts = reader.readWorkouts();
        trainingTracker.getWorkouts().addAll(readWorkouts);
    }

    public List<Exercise> getExercises() {
        return trainingTracker.getExercises();
    }
}
