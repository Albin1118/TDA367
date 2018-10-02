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
import android.widget.Toast;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutTabFragment extends Fragment {

    private FloatingActionButton addWorkoutButton;
    private ListView workoutList;

    private MainActivity parentActivity;
    private View view;
    private List <Workout> workouts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = (MainActivity)getActivity();
        view  = inflater.inflate(R.layout.fragment_workout_tab, container, false);
        workouts = parentActivity.viewModel.getWorkouts();
        initViews();
        initListeners();
        ArrayAdapter<Workout> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, workouts);
        workoutList.setAdapter(adapter);

        return view;
    }

    private void initViews() {
        addWorkoutButton = view.findViewById(R.id.addWorkoutButton);
        workoutList = view.findViewById(R.id.workoutList);
    }

    private void initListeners() {
        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), workouts.get(position).toString(), Toast.LENGTH_SHORT).show();
                parentActivity.setWorkoutDetailView(workouts.get(position));
                parentActivity.setViewPager(6);
            }
        });

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setViewPager(5);
            }
        });
    }

}
