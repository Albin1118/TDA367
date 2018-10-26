package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.Achievement;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

import java.util.List;

public class AchievementsViewModel extends ViewModel {
    private ITrainingTracker model;

    public void init(ITrainingTracker model) {
        this.model = model;
    }

    public List<Achievement> getAchievements() {
        return model.getAchievements();
    }
}
