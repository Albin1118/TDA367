package com.example.ziggy.trainingtracker.viewmodel;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IUser;
import com.example.ziggy.trainingtracker.model.User;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class MainViewModelTest {
    /*
    private MainViewModel viewModel = new MainViewModel();

    // First draft of json tests
    @Test
    public void convertToJson_isCorrect() {
        String expected = "{\"customExercises\":[],\"customWorkouts\":[],\"username\":\"Test\",\"name\":\"Mr Test\",\"weight\":98.5,\"height\":210,\"age\":0}";
        //String j = viewModel.convertUserToJson();
        //assertEquals(expected, j);

    }


    @Test
    public void deSerialization_isCorrect() {
        Gson gson = new Gson();
        IUser u = new User("Ree", "Pepe", 1337.0, 190);
        String json = gson.toJson(u);
        IUser nu = gson.fromJson(json, User.class);
        assertEquals(u.getHeight(), nu.getHeight());

    }


    @Test
    public void addCustomExercise_isCorrect() {
        String name = "MyBenchPressExercise", description = "Hardcore", instructions = "Bench the press", unit = "reps";
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ABS);
        viewModel.addCustomExercise(name, description, instructions, unit, categories);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getName(), name);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getDescription(), description);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getInstructions(), instructions);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getUnit(), unit);
        assertEquals(viewModel.getCustomExercises().get(viewModel.getCustomExercises().size()-1).getCategories(), categories);
    }

    @Test
    public void removeCustomExercise_isCorrect() {
        String name = "Pull-Ups", description = "Hardcore", instructions = "Pull the ups", unit = "reps";
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ABS);
        List<IExercise> customExercises = viewModel.getCustomExercises();
        int originalListSize = customExercises.size();
        viewModel.addCustomExercise(name, description, instructions, unit, categories);
        assertEquals(customExercises.size(), originalListSize + 1);
        IExercise exerciseToRemove = null;
        for (IExercise e : customExercises) {
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
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ABS);
        String newName = "Pull-UpsChanged", newDescription = "HardcoreChanged", newInstructions = "Pull the ups Changed", newUnit = "repsChanged";
        List<ExerciseCategory> newCategories = new ArrayList<>();
        newCategories.add(ExerciseCategory.ARMS);
        viewModel.addCustomExercise(name, description, instructions, unit, categories);
        IExercise exercise = null;
        for (IExercise e : viewModel.getCustomExercises()) {
            if (e.getName().equals(name))
                exercise = e;
        }
        assertNotNull(exercise);
        viewModel.editCustomExercise(exercise, newName, newDescription, newInstructions, newUnit, newCategories);
        assertEquals(exercise.getName(), newName);
        assertEquals(exercise.getDescription(), newDescription);
        assertEquals(exercise.getInstructions(), newInstructions);
        assertEquals(exercise.getUnit(), newUnit);
        assertEquals(exercise.getCategories(), newCategories);
    }
    */
}

