package com.example.ziggy.trainingtracker.viewmodel;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.User;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class MainViewModelTest {
    private MainViewModel viewModel = new MainViewModel();

    // First draft of json tests
    @Test
    public void convertToJson_isCorrect() {
        String expected = "{\"customExercises\":[],\"customWorkouts\":[],\"username\":\"Test\",\"name\":\"Mr Test\",\"weight\":98.5,\"height\":210,\"age\":0}";
        String j = viewModel.convertUserToJson();
        assertEquals(expected, j);

    }


    @Test
    public void deSerialization_isCorrect() {
        Gson gson = new Gson();
        User u = new User("Ree", "Pepe", 1337.0, 190);
        String json = gson.toJson(u);
        User nu = gson.fromJson(json, User.class);
        assertEquals(u.getHeight(), nu.getHeight());

    }


    @Test
    public void addCustomExercise_isCorrect() {
        String name = "MyBenchPressExercise", description = "Hardcore", instructions = "Bench the press", unit = "reps";
        viewModel.addCustomExercise(name, description, instructions, unit);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getName(), name);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getDescription(), description);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getInstructions(), instructions);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getUnit(), unit);
    }

    @Test
    public void removeCustomExercise_isCorrect() {
        String name = "Pull-Ups", description = "Hardcore", instructions = "Pull the ups", unit = "reps";
        List<Exercise> customExercises = viewModel.getCustomExercises();
        int originalListSize = customExercises.size();
        viewModel.addCustomExercise(name, description, instructions, unit);
        assertEquals(customExercises.size(), originalListSize + 1);
        Exercise exerciseToRemove = null;
        for (Exercise e : customExercises) {
            if (e.getName().equals(name))
                exerciseToRemove = e;
        }
        assertNotNull(exerciseToRemove);
        viewModel.removeCustomExercise(exerciseToRemove);
        assertEquals( customExercises.size(), originalListSize);
    }


    @Test
    public void editCustomExercise_isCorrect() {
        String name = "Pull-Ups", description = "Hardcore", instructions = "Pull the ups", unit = "reps";
        String newName = "Pull-UpsChanged", newDescription = "HardcoreChanged", newInstructions = "Pull the ups Changed", newUnit = "repsChanged";
        viewModel.addCustomExercise(name, description, instructions, unit);
        Exercise exercise = null;
        for (Exercise e : viewModel.getCustomExercises()) {
            if (e.getName().equals(name))
                exercise = e;
        }
        assertNotNull(exercise);
        viewModel.editCustomExercise(exercise, newName, newDescription, newInstructions, newUnit);
        assertEquals(exercise.getName(), newName);
        assertEquals(exercise.getDescription(), newDescription);
        assertEquals(exercise.getInstructions(), newInstructions);
        assertEquals(exercise.getUnit(), newUnit);
    }
}

