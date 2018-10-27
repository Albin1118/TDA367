package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutTabViewModel;

/**
 * Fragment representing the workout-tab, where the list of workouts is displayed
 */
public class WorkoutTabFragment extends Fragment {

    private FloatingActionButton addWorkoutButton;
    private ListView workoutList;

    private ArrayAdapter<IWorkout> adapter;

    private View view;
    private WorkoutTabViewModel viewModel;
    private NavigationManager navigator;

    public static WorkoutTabFragment newInstance(WorkoutTabViewModel viewModel, NavigationManager navigator) {
        WorkoutTabFragment fragment = new WorkoutTabFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(WorkoutTabViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_workouttab, container, false);
        initViews();
        initListeners();
        navigator.setNavBarState(R.id.nav_workouts);

        return view;
    }

    private void initViews() {
        addWorkoutButton = view.findViewById(R.id.addWorkoutButton);
        workoutList = view.findViewById(R.id.workoutList);

        adapter = new WorkoutListAdapter(getContext(), viewModel.getAllWorkouts());

        workoutList.setAdapter(adapter);
    }

    private void initListeners() {
        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.navigateWorkoutDetailView(viewModel.getWorkout(position));
            }
        });

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateWorkoutCreator();
            }
        });
    }

}
