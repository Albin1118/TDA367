package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.Challenge;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTabViewModel extends ViewModel {
    private ITrainingTracker model;
    private List<IExercise> exercises;

    //For testing
    List<IChallenge> challenges = new ArrayList<>();

    public void init(ITrainingTracker model) {
        this.model = model;
        exercises = new ArrayList<>(model.getExercises());

        //For testing
        challenges.add(new Challenge());
        challenges.add(new Challenge());
        challenges.add(new Challenge());
    }

    public void sortExercisesByCategory(String categoryString) {
        ExerciseCategory category = ExerciseCategory.valueOf(categoryString);

        List<IExercise> sortedExercises = new ArrayList<>();
        for (IExercise e : model.getExercises()) {
            if (e.getCategories().contains(category))
                sortedExercises.add(e);
        }
        exercises.clear();
        exercises.addAll(sortedExercises);
    }

    public List<IExercise> getExercises() {
        return exercises;
    }

    public List<IExercise> getAllExercises() {
        exercises.clear();
        exercises.addAll(model.getExercises());
        return exercises;
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
