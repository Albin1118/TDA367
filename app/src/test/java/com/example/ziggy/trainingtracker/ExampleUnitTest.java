package com.example.ziggy.trainingtracker;

import android.widget.EditText;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void addExercise_isCorrect() {
        CreateExerciseActivity c = new CreateExerciseActivity();
        String nameTest = "Name";
        String descriptionTest = "Description";
        c.createExercise(nameTest, descriptionTest);
        assertEquals(c.customExercises.get(0).getName(), nameTest);
    }
}