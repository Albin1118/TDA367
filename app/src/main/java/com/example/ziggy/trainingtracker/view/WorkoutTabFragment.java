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
        view = inflater.inflate(R.layout.fragment_workout_tab, container, false);
        initViews();
        initListeners();
        navigator.setNavBarState(R.id.nav_workouts);

        return view;
    }

    private void initViews() {
        addWorkoutButton = view.findViewById(R.id.addWorkoutButton);
        workoutList = view.findViewById(R.id.workoutList);

        adapter = new ArrayAdapter<IWorkout>(getContext(), R.layout.workout_list_item, R.id.workoutNameTextView, viewModel.getAllWorkouts()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView workoutNameTextView = (TextView) view.findViewById(R.id.workoutNameTextView);
                TextView workoutDescriptionTextView = (TextView) view.findViewById(R.id.workoutDescriptionTextView);
                TextView workoutBlocksTextView = (TextView) view.findViewById(R.id.workoutBlocksTextView);

                workoutNameTextView.setText(viewModel.getWorkouts().get(position).getName());
                workoutDescriptionTextView.setText(viewModel.getWorkouts().get(position).getDescription());
                workoutBlocksTextView.setText(viewModel.getWorkouts().get(position).getNumberofBlocks());
                return view;
            }
        };
        workoutList.setAdapter(adapter);
    }

    private void initListeners() {
        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.navigateWorkoutDetailView(viewModel.getWorkouts().get(position));
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
