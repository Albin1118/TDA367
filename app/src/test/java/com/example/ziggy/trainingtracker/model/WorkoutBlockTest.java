package com.example.ziggy.trainingtracker.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockTest {
    private IExercise exercise1, exercise2, exercise3;
    private int amount1 = 1;
    private int amount2 = 2;
    private int amount3 = 3;

    
    private void createTestVariables() {
        String name1 = "Pushups", description1 = "Very hard", instructions1 = "Don´t hit your nose", unit1 = "reps";
        List<ExerciseCategory> categories1 = new ArrayList<>();
        categories1.add(ExerciseCategory.ARMS);
        exercise1 = new Exercise(name1, unit1, description1, instructions1, categories1, false);

        String name2 = "Pullups", description2 = "Very very hard", instructions2 = "Don´t hit your head", unit2 = "reps^2";
        List<ExerciseCategory> categories2 = new ArrayList<>();
        categories2.add(ExerciseCategory.BACK);
        exercise2 = new Exercise(name2, unit2, description2, instructions2, categories2, false);

        String name3 = "Situps", description3 = "Very very very hard", instructions3 = "Don´t hit your back", unit3 = "reps^3";
        List<ExerciseCategory> categories3 = new ArrayList<>();
        categories3.add(ExerciseCategory.ABS);
        exercise3 = new Exercise(name3, unit3, description3, instructions3, categories3, false);
    }


    @Test
    public void addExercise_isCorrect() {
        createTestVariables();

        IWorkoutBlock block = new WorkoutBlock();

        block.addExercise(exercise1, amount1, 0);
        block.addExercise(exercise2, amount2, 0);
        assertNotEquals(block.getExercises().get(0), block.getExercises().get(1));
        assertEquals(block.getAmounts().get(0), (Integer) amount1);
        assertEquals(block.getExercises().get(0), exercise1);
        assertEquals(block.getAmounts().get(1), (Integer) amount2);
        assertEquals(block.getExercises().get(1), exercise2);

    }

    @Test public void removeExercise_isCorrect() {

        createTestVariables();

        IWorkoutBlock block = new WorkoutBlock();

        block.addExercise(exercise1, amount1, 0);
        block.addExercise(exercise2, amount2, 0);
        block.addExercise(exercise3, amount3, 0);
        assertEquals(block.getExercises().get(1), exercise2);
        assertEquals(block.getAmounts().get(1), (Integer) amount2);

        block.removeExercise(exercise2);
        assertEquals(block.getExercises().size(), 2);
        assertEquals(block.getExercises().get(1), exercise3);
        assertEquals(block.getAmounts().get(1), (Integer) amount3);
    }

    @Test public void isEmpty_isCorrect(){

        createTestVariables();

        IWorkoutBlock block = new WorkoutBlock();

        assertTrue(block.isEmpty());

        block.addExercise(exercise1, amount1, 0);
        block.addExercise(exercise2, amount2, 0);
        block.addExercise(exercise3, amount3, 0);

        assertFalse(block.isEmpty());

        block.removeExercise(exercise1);
        block.removeExercise(exercise2);
        block.removeExercise(exercise3);

        assertTrue(block.isEmpty());

    }
}
