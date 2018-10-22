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
    private List<IExercise> exercises;
    private MutableLiveData<List<IExercise>> exercisesLiveData = new MutableLiveData<>();

    public void init(ITrainingTracker model) {
        this.model = model;

        exercises = new ArrayList<>(model.getExercises());
        exercisesLiveData.setValue(exercises);
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
        exercisesLiveData.setValue(exercises);
    }

    public void clearExerciseSorting() {
        exercises.clear();
        exercises.addAll(model.getExercises());
        exercisesLiveData.setValue(exercises);
    }

    public List<IExercise> getExercises() {
        return exercises;
    }

    public List<IExercise> getAllExercises() {
        clearExerciseSorting();
        return exercises;
    }

    public LiveData<List<IExercise>> getExercisesLiveData() {
        return exercisesLiveData;
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        for (ExerciseCategory category : ExerciseCategory.values()) {
            categories.add(category.name());
        }
        return categories;
    }

    public List<IChallenge> getChallenges() {
        return model.getChallenges();
    }
}
