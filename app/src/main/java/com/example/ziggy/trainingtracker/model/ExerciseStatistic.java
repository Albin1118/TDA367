package com.example.ziggy.trainingtracker.model;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

public class ExerciseStatistic {

    private IExercise exercise;
    private LinkedHashMap<SetRepsWeightContainer, Date> statisticHolder;


    ExerciseStatistic(IExercise exercise) {
        this.exercise = exercise;
        statisticHolder = new LinkedHashMap<>();
    }

    void addStatistics(int sets, int reps, double weight){
        statisticHolder.put(new SetRepsWeightContainer(sets, reps, weight), new Date());
    }

    /**
     *
     * @param sets The sets to find statistics for
     * @param reps The reps to find statistics for
     * @return List containing Date and weight data for the corresponding sets and reps
     * @throws NoSuchElementException If it doesn't find any data for the supplied sets and reps
     */

    LinkedHashMap<Date, Double> getStatisticForSpecificSetReps(int sets, int reps) throws NoSuchElementException {
        LinkedHashMap<Date, Double> map = new LinkedHashMap<>();

        for (SetRepsWeightContainer s : statisticHolder.keySet()){
            if (s.getReps() == reps && s.getSets() == sets){
                // If sets and reps correspond with stored statistics, add the created date with get.(s) (they date is the key)
                map.put(statisticHolder.get(s), s.getWeight());
            }
        }

        if (map.isEmpty()){
            throw new NoSuchElementException("There are no statistics available for the supplied sets/reps");
        }

        return map;
    }

    public IExercise getExercise() {
        return exercise;
    }

    private class SetRepsWeightContainer{
        private int sets;
        private int reps;
        private double weight;

        SetRepsWeightContainer(int sets, int reps, double weight) {
            this.sets = sets;
            this.reps = reps;
            this.weight = weight;
        }


        public double getWeight() {
            return weight;
        }

        public int getSets() {
            return sets;
        }

        public int getReps() {
            return reps;
        }
    }
}
