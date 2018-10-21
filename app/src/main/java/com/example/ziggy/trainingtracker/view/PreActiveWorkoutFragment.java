package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.viewmodel.ActiveWorkoutViewModel;

public class PreActiveWorkoutFragment extends Fragment {

    // Components
    private ListView preActiveWorkoutListView;
    private Button beginWorkoutButton;

    private ArrayAdapter<IWorkout> adapter;

    private View view;
    private ActiveWorkoutViewModel viewModel;
    private NavigationManager navigator;

    public static PreActiveWorkoutFragment newInstance(ActiveWorkoutViewModel viewModel, NavigationManager navigator) {
        PreActiveWorkoutFragment fragment = new PreActiveWorkoutFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ActiveWorkoutViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_preactiveworkout, container, false);
        navigator.setNavBarState(R.id.nav_active_workout);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        beginWorkoutButton = view.findViewById(R.id.pre_workout_start_button);
        preActiveWorkoutListView = view.findViewById(R.id.pre_workout_list);

        preActiveWorkoutListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter<IWorkout>(getContext(), R.layout.item_workout, R.id.workoutNameTextView, viewModel.getWorkouts()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView workoutNameTextView = (TextView) view.findViewById(R.id.workoutNameTextView);
                TextView workoutBlocksTextView = (TextView) view.findViewById(R.id.workoutBlocksTextView);

                workoutNameTextView.setText(viewModel.getWorkouts().get(position).getName());
                workoutBlocksTextView.setText(viewModel.getWorkouts().get(position).getNumberofBlocks());
                return view;
            }
        };
        preActiveWorkoutListView.setAdapter(adapter);
    }

    private void initListeners() {
        beginWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActiveWorkout();
            }
        });

        preActiveWorkoutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                preActiveWorkoutListView.setItemChecked(position, true);
            }
        });
    }

    private void startActiveWorkout(){
        if (preActiveWorkoutListView.getCheckedItemCount() > 0) {
            navigator.navigateActiveWorkout(viewModel.getWorkouts().get(preActiveWorkoutListView.getCheckedItemPosition()));
        } else {
            Toast.makeText(getContext(), "You need to select a workout first", Toast.LENGTH_SHORT).show();
        }
    }
}
