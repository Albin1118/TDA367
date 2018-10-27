package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

public class ActiveChallengeViewModel extends ViewModel {
    private ITrainingTracker model;
    private IChallenge challenge;

    public void init(ITrainingTracker model, IChallenge challenge) {
        this.model = model;
        this.challenge = challenge;
    }

    public void finishChallenge(int newScore) {
            model.finishChallenge(challenge, newScore);
    }

    public IChallenge getChallenge() {
        return challenge;
    }

    public String getChallengeName(){
        return challenge.getName();
    }

    public String getChallengeDescription(){
        return challenge.getDescription();
    }

    public String getChallengeUnit(){
        return challenge.getUnit();
    }
}
