package com.example.ziggy.trainingtracker;

import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private TrainingTracker trainingTracker;

    MainViewModel() {
        trainingTracker = new TrainingTracker();
    }
}
