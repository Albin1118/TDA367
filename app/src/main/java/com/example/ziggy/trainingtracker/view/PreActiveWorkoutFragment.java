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
import android.widget.ListView;



import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Workout;

import java.util.List;


public class PreActiveWorkoutFragment extends Fragment {


    private MainActivity parentActivity;
    private View view;
    private List<Workout> workouts;
    private Workout selectedWorkout;

    private ArrayAdapter<Workout>adapter;

    // Components
    private ListView preActiveWorkoutListView;
    private Button beginWorkoutButton;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_pre_active_workout, container, false);
        parentActivity = ((MainActivity)getActivity());
        parentActivity.setNavBarState(R.id.nav_active_workout);
        workouts = parentActivity.viewModel.getWorkouts();

        initViews();
        initListeners();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_activated_1, workouts);

        preActiveWorkoutListView.setAdapter(adapter);
        return view;
    }


    private void startActiveWorkout(){
        parentActivity.viewModel.setActiveWorkoutStatus(true);
        Fragment f = new ActiveWorkoutFragment();
        ((ActiveWorkoutFragment) f).setCurrentWorkout(selectedWorkout);
        parentActivity.setFragmentContainerContentFromTab(new ActiveWorkoutFragment());
    }

    private void initViews() {
        beginWorkoutButton = view.findViewById(R.id.pre_workout_start_button);
        preActiveWorkoutListView = view.findViewById(R.id.pre_workout_list);
        preActiveWorkoutListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

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
                view.setSelected(true); // Will fix color usage soon
                selectedWorkout = workouts.get(position);

            }
        });

    }



}
