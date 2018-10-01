package com.example.ziggy.trainingtracker.viewmodel;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainViewModelTest {
    MainViewModel viewModel = new MainViewModel();


    @Test
    public void addCustomExercise_isCorrect() {
        viewModel.addCustomExercise("BenchPress", "Hardcore", "reps");
        assertEquals( viewModel.getTrainingTracker().getUser().getCustomExercises().get(0).getName(), "BenchPress");
    }

    @Test
    public void removeCustomExercise_isCorrect() {
        viewModel.addCustomExercise("Pullups", "Hardcore", "reps");
        viewModel.addCustomExercise("Pullups", "Hardcore", "reps");
        viewModel.removeCustomExercise(0);
        assertEquals( viewModel.getTrainingTracker().getUser().getCustomExercises().size(), 1);
    }


    @Test
    public void editCustomExercise_isCorrect() {
        int index = 0;
        viewModel.addCustomExercise("Pullups", "Hardcore", "reps");
        viewModel.editCustomExercise(index, "PullupsChanged", "HardCore", "reps");
        assertEquals( viewModel.getTrainingTracker().getUser().getCustomExercises().get(index).getName(), "PullupsChanged");
    }
}

