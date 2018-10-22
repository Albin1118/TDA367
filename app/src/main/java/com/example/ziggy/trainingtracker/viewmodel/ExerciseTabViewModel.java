package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTabViewModel extends ViewModel {
    private ITrainingTracker model;
    private List<IChallenge> challenges;
    private List<IExercise> exercises;
    private MutableLiveData<List<IExercise>> exercisesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<IChallenge>> challengesLiveData = new MutableLiveData<>();

    public void init(ITrainingTracker model) {
        this.model = model;

        challenges = new ArrayList<>(model.getChallenges());
        exercises = new ArrayList<>(model.getExercises());
        exercisesLiveData.setValue(exercises);
        challengesLiveData.setValue(challenges);
    }

    public void sortByExerciseCategory(String categoryString) {
        ExerciseCategory category = ExerciseCategory.valueOf(categoryString);

        List<IExercise> sortedExercises = new ArrayList<>();
        List<IChallenge> sortedChallenges = new ArrayList<>();
        for (IExercise e : model.getExercises()) {
            if (e.getCategories().contains(category)) {
                sortedExercises.add(e);
                for (IChallenge challenge : model.getChallenges()) {
                    if (challenge.getName().equals(e.getName()))
                        sortedChallenges.add(challenge);
                }
            }
        }
        challenges.clear();
        challenges.addAll(sortedChallenges);
        exercises.clear();
        exercises.addAll(sortedExercises);
        exercisesLiveData.setValue(exercises);
        challengesLiveData.setValue(challenges);
    }

    public void clearSorting() {
        exercises.clear();
        exercises.addAll(model.getExercises());
        challenges.clear();
        challenges.addAll(model.getChallenges());
        exercisesLiveData.setValue(exercises);
        challengesLiveData.setValue(challenges);
    }

    public List<IExercise> getExercises() {
        return exercises;
    }

    public List<IExercise> getAllExercises() {
        clearSorting();
        return exercises;
    }

    public LiveData<List<IExercise>> getExercisesLiveData() {
        return exercisesLiveData;
    }

    public LiveData<List<IChallenge>> getChallengesLiveData() {
        return challengesLiveData;
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        for (ExerciseCategory category : ExerciseCategory.values()) {
            categories.add(category.name());
        }
        return categories;
    }

    public List<IChallenge> getChallenges() {
        return challenges;
    }
}
