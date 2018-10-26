package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.ExerciseStatistic;
import com.example.ziggy.trainingtracker.viewmodel.StatisticViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

    private Button  showStatisticsButton;

    private StatisticViewModel viewModel;
    private NavigationManager navigator;

    private GraphView graph;

    private Spinner exerciseSpinner;
    private Spinner repSpinner;
    private Spinner setSpinner;

    private int chosenSets;
    private int chosenReps;


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
        showStatisticsButton = view.findViewById(R.id.showStatisticsButton);

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

        initSpinners();



    }

    private void initListeners() {

        setSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenSets = Integer.parseInt(setSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        repSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenReps = Integer.parseInt(repSpinner.getSelectedItem().toString());
                System.out.println(chosenSets +  "   "  + chosenReps);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initSpinners(){
        exerciseSpinner = view.findViewById(R.id.exercise_spinner);
        setSpinner = view.findViewById(R.id.set_spinner);
        repSpinner = view.findViewById(R.id.reps_spinner);

        ArrayAdapter<CharSequence> setSpinnerAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.set_spinner_values, android.R.layout.simple_spinner_item);
        setSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> repSpinnerAdapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.rep_spinner_values, android.R.layout.simple_spinner_item);
        setSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        setSpinner.setAdapter(setSpinnerAdapter);
        repSpinner.setAdapter(repSpinnerAdapter);

    }

    private void initDataSeries(){
         // Currently only sample data is created
        exerciseDataSeries = new LineGraphSeries<>();
    }




    private void initGraph(LineGraphSeries<DataPoint> series, String title){

        graph.setTitle(title);
        graph.setTitleTextSize(100);
        graph.addSeries(series);

    }
}
