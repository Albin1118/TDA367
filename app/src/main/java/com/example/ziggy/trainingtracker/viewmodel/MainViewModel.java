package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.Services.LoadExercisesService;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.TrainingTracker;

import java.util.List;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    public MainViewModel() {
        trainingTracker = new TrainingTracker();
        loadExercises();
    }

    /**
     * Loads a list of base exercises from a text file into the TrainingTracker.
     */
    private void loadExercises() {
        LoadExercisesService les = new LoadExercisesService();
        List<Exercise> loadedExercises = les.loadExercises();
        trainingTracker.getExercises().addAll(loadedExercises);
    }
}
