package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IChallenge;

public class ActiveChallengeViewModel extends ViewModel {
    private IChallenge challenge;

    public void init(IChallenge challenge) {
        this.challenge = challenge;
    }

    public boolean isNewHighScore(int newScore) {
        if (challenge.getScore() < newScore) {
            return true;
        }
        return false;
    }

    public IChallenge getChallenge() {
        return challenge;
    }
}
