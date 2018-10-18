package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseStatistic;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;
import java.util.List;

/**
 * Fragment responsible for creating graphs and showing the user statistics
 */

public class StatisticsFragment extends Fragment {

    private MainActivity parentActivity;
    private NavigationManager navigationManager;
    private View view;

    private String workoutName;
    private List<ExerciseStatistic> exercises;
    private Exercise chosenExercise;
    private LineGraphSeries<DataPoint> exerciseDataSeries;

    private GraphView graph;
    private Spinner exerciseSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_statistics, container, false);
        parentActivity = (MainActivity)getActivity();
        navigationManager = (MainActivity)getActivity();
        navigationManager.setNavBarState(R.id.nav_more);

        //exercises = parentActivity.viewModel.getExercises();

        workoutName = "Deadlift 3x10";


        initViews();
        initDataSeries();
        initGraph(exerciseDataSeries);

        return view;
    }




    private void initViews(){
       graph = (GraphView) view.findViewById(R.id.graph);
       graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
       graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space


       exerciseSpinner = view.findViewById(R.id.exercise_spinner);


    }

    private void initDataSeries(){
         // Currently only sample data is created

        Date date = new Date();
        Date date2 = new Date();


        exerciseDataSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(date, 1),
                new DataPoint(date2, 5),
                new DataPoint(date2, 3),
                new DataPoint(date2, 2),
                new DataPoint(date2, 6)
        });

    }




    private void initGraph(LineGraphSeries<DataPoint> series){

        graph.setTitle(workoutName);
        graph.setTitleTextSize(100);
        graph.addSeries(series);

    }

    public void setChosenExercise(Exercise chosenExercise) {
        this.chosenExercise = chosenExercise;
    }
}
