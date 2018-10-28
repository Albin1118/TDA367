package com.example.ziggy.trainingtracker.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ExerciseStatisticTest {

    private IExercise exercise1;
    private int sets = 3;
    private int reps = 10;
    private double weight = 20;

    private void createTestVariables() {
        String name1 = "Pushups", description1 = "Very hard", instructions1 = "Don´t hit your nose", unit1 = "reps";
        List<ExerciseCategory> categories1 = new ArrayList<>();
        categories1.add(ExerciseCategory.ARMS);
        exercise1 = new Exercise(name1, unit1, description1, instructions1, categories1, false);
    }


    @Test
    public void statisticAdditionAndRetrieval_isCorrect(){
        createTestVariables();

        ExerciseStatistic es = new ExerciseStatistic(exercise1);

        es.addStatistics(sets, reps, weight);

        LinkedHashMap<Date, Double> statisticsForSetsReps = es.getStatisticForSpecificSetReps(sets, reps);

        ArrayList <Double> expectedWeightList = new ArrayList<>(statisticsForSetsReps.values());

        double expectedWeight = expectedWeightList.get(0);

        assertEquals(expectedWeight, 20.0, 0.0);
    }
}
