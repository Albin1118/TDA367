package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.TrainingTracker;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    public MainViewModel() {
        trainingTracker = new TrainingTracker();
    }
}
