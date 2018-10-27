package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;
import com.example.ziggy.trainingtracker.model.IUser;

public class UserInfoViewModel extends ViewModel {
    private ITrainingTracker model;
    private IUser user;

    public void init(ITrainingTracker model) {
        this.model = model;
        user = model.getUser();
    }

    public String getUsername(){
        return user.getUsername();
    }

    public String getName(){
        return user.getName();
    }

    public String getWeight(){
        return String.valueOf(user.getWeight());
    }

    public String getHeight(){
        return String.valueOf(user.getHeight());
    }

}
