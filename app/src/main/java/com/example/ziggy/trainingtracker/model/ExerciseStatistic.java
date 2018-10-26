package com.example.ziggy.trainingtracker.model;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;


public class ExerciseStatistic {

    private String exerciseName;
    private int reps;
    private int sets;
    private IExercise exercise;
    private Date date;
    private Boolean weightBased;
    private int weight;



    Calendar calendar;
    LinkedHashMap<Date, Integer> weightHistoryMap;


    public ExerciseStatistic(IExercise exercise, int reps, int sets) {
        calendar = Calendar.getInstance();
        weightHistoryMap = new LinkedHashMap<>();
        this.reps = reps;
        this.sets = sets;
        setExerciseInformation(exercise);
    }

    public void setExerciseInformation(IExercise exercise) {
        this.exerciseName = exercise.getName();
        if(exercise.isWeightBased()) {
            this.weightBased = true;
            this.weight = exercise.getWeight();
        }
    }


    public void addExerciseStats(int weight){
        Date date = new Date();
        weightHistoryMap.put(date, weight);
    }

    public String toString(){
        return this.exerciseName + " " + sets + "x" + reps;
    }

    public int getReps() {
        return reps;
    }

    public Boolean getWeightBased() {
        return weightBased;
    }

    public int getSets() {
        return sets;
    }

    public int getWeight() {
        return weight;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
