package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseStatistic;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.viewmodel.StatisticViewModel;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutDetailViewModel;
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
    private String workoutName1;
    private List<ExerciseStatistic> exercises;
    private LineGraphSeries<DataPoint> exerciseDataSeries;
    private EditText exerciseEditText, repEditText, setEditText;
    private Button searchStatisticsButton;

    private StatisticViewModel viewModel;
    private NavigationManager navigator;

    private GraphView graph;
    private Spinner exerciseSpinner;


    public static StatisticsFragment newInstance(StatisticViewModel viewModel, NavigationManager navigator) {
        StatisticsFragment fragment = new StatisticsFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(StatisticViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_statistics, container, false);
        parentActivity = (MainActivity)getActivity();
        navigationManager = (MainActivity)getActivity();
        navigationManager.setNavBarState(R.id.nav_more);
        exerciseEditText = view.findViewById(R.id.exerciseEditText);
        repEditText = view.findViewById(R.id.repEditText);
        setEditText = view.findViewById(R.id.setEditText);
        searchStatisticsButton = view.findViewById(R.id.searchStatisticsButton);

        workoutName = "Deadlift 3x10";

        initViews();
        initDataSeries();
        initGraph(exerciseDataSeries ,workoutName);
        initListeners();

        return view;
    }




    private void initViews(){
       graph = (GraphView) view.findViewById(R.id.graph);
       //graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getActivity()));
       graph.getGridLabelRenderer().setNumHorizontalLabels(4); // only 4 because of the space

       exerciseSpinner = view.findViewById(R.id.exercise_spinner);
    }

    private void initListeners() {
        searchStatisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
                String name = exerciseEditText.getText().toString();
                int reps = Integer.parseInt(repEditText.getText().toString());
                int sets = Integer.parseInt(setEditText.getText().toString());
                List<ExerciseStatistic> exerciseStatistics = viewModel.getStatisticsFromSearch(name, reps, sets);
                initGraph(viewModel.DataPointsFromStatisticsList(exerciseStatistics), workoutName);
            }
        });
    }

    private void initDataSeries(){
         // Currently only sample data is created
        exerciseDataSeries = new LineGraphSeries<>();
        exerciseDataSeries.appendData(new DataPoint(1,1), true, 5);
        exerciseDataSeries.appendData(new DataPoint(2,2), true, 5);
        exerciseDataSeries.appendData(new DataPoint(3,3), true, 5);
        exerciseDataSeries.appendData(new DataPoint(4,4), true, 5);
        exerciseDataSeries.appendData(new DataPoint(5,5), true, 5);
    }




    private void initGraph(LineGraphSeries<DataPoint> series, String title){

        graph.setTitle(title);
        graph.setTitleTextSize(100);
        graph.addSeries(series);

    }
}
