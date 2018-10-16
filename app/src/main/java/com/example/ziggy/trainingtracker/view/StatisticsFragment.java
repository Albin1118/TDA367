package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

/**
 * Fragment responsible for creating graphs and showing the user statistics
 */

public class StatisticsFragment extends Fragment {

    private MainActivity parentActivity;
    private View view;

    private String workoutName;
    private List<Exercise> exercises;
    private Exercise chosenExercise;
    private LineGraphSeries<DataPoint> exerciseDataSeries;
    private GraphView graph;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_statistics, container, false);
        parentActivity = (MainActivity)getActivity();
        parentActivity.setNavBarState(R.id.nav_more);

        exercises = parentActivity.viewModel.getExercises();

        workoutName = "Deadlift 3x10";


        initViews();
        initDataSeries();
        initGraph(exerciseDataSeries);

        return view;
    }




    private void initViews(){
       graph = (GraphView) view.findViewById(R.id.graph);

    }

    private void initDataSeries(){
         // Currently only sample data is created
        exerciseDataSeries = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
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
