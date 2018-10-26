package com.example.ziggy.trainingtracker.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.ITrainingTracker;


import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;
import java.util.LinkedHashMap;

import java.util.Map;

public class StatisticViewModel extends ViewModel {
    ITrainingTracker model;


    public void init(ITrainingTracker model) {
        this.model = model;
    }


    public LineGraphSeries<DataPoint> generateStatisticLineGraphData(IExercise e, int sets, int reps){
        LineGraphSeries<DataPoint> graphPoints = new LineGraphSeries<>();
        LinkedHashMap<Date, Double> values = model.getUser().generateStatisticsForExercise(e, sets, reps);

        for (Map.Entry <Date, Double> entry : values.entrySet()){
            graphPoints.appendData(new DataPoint(entry.getKey(), entry.getValue()), true, values.size());
        }

        return graphPoints;
    }

}
