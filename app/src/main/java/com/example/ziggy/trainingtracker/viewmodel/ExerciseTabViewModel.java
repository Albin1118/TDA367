package com.example.ziggy.trainingtracker.viewmodel;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.TrainingTracker;

import java.util.ArrayList;
import java.util.List;

public class ExerciseTabViewModel {
    private TrainingTracker model = TrainingTracker.getInstance();
    private List<Exercise> exercises = new ArrayList<>(model.getExercises());

    public ExerciseTabViewModel() {
    }

    public void sortExercisesByCategory(String categoryString) {
        ExerciseCategory category = ExerciseCategory.valueOf(categoryString);

        List<Exercise> sortedExercises = new ArrayList<>();
        for (Exercise e : model.getExercises()) {
            if (e.getCategories().contains(category))
                sortedExercises.add(e);
        }
        exercises.clear();
        exercises.addAll(sortedExercises);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public List<Exercise> getAllExercises() {
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
}
