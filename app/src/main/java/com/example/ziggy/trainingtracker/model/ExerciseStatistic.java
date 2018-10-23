package com.example.ziggy.trainingtracker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;


public class ExerciseStatistic {

    private int reps;
    private int sets;
    private IExercise exercise;

    Calendar calendar;
    LinkedHashMap<Date, Integer> weightHistoryMap;


    public ExerciseStatistic(int reps, int sets, IExercise exercise) {
        calendar = Calendar.getInstance();
        weightHistoryMap = new LinkedHashMap<>();
        this.reps = reps;
        this.sets = sets;
        this.exercise = exercise;

    }


    public void addExerciseStats(int weight){
        Date date = new Date();
        weightHistoryMap.put(date, weight);
    }

    public String toString(){
        return exercise.getName() + " " + sets + "x" + reps;
    }



}
