package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;


import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Map;

public class StatisticViewModel extends ViewModel {
    ITrainingTracker model;

    public void init(ITrainingTracker model) {
        this.model = model;
    }

    /**
     * Creates a LineGraphSeries of DataPoints for the corresponding Exercise, number of sets and reps
     * @param e The exercise to find statistics for
     * @param sets The amount of sets to find statistics for
     * @param reps The amount of reps to find statistics for
     * @return LineGraphSeries for use with the GraphView library in order to plot data on a graph
     */

    public LineGraphSeries<DataPoint> generateStatisticLineGraphData(IExercise e, int sets, int reps){
        LineGraphSeries<DataPoint> graphPoints = new LineGraphSeries<>();

        try {
            LinkedHashMap<Date, Double> values = model.getUser().generateStatisticsForExercise(e, sets, reps);

            for (Map.Entry<Date, Double> entry : values.entrySet()) {
                graphPoints.appendData(new DataPoint(entry.getKey(), entry.getValue()), true, values.size());
            }
        }

        catch (NullPointerException except) {
            except.printStackTrace();
        }

        return graphPoints;
    }

    /**
     * @return List of exercises that have statistic data stored
     */

    public List<IExercise> getAvailableExercisesWithStatistics(){
        return new ArrayList<>(model.getUser().getExercisesWithStatisticsAvailable());
    }
}
