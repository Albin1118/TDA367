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
import android.widget.TextView;
import android.widget.Toast;


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

        adapter = new ArrayAdapter(getContext(), R.layout.workout_list_item, R.id.workoutNameTextView, workouts) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView workoutNameTextView = (TextView) view.findViewById(R.id.workoutNameTextView);
                TextView workoutDescriptionTextView = (TextView) view.findViewById(R.id.workoutDescriptionTextView);
                TextView workoutBlocksTextView = (TextView) view.findViewById(R.id.workoutBlocksTextView);

                workoutNameTextView.setText(workouts.get(position).getName());
                workoutDescriptionTextView.setText(workouts.get(position).getDescription());
                workoutBlocksTextView.setText(workouts.get(position).getNumberofBlocks());
                return view;
            }
        };

        preActiveWorkoutListView.setAdapter(adapter);
        return view;
    }


    private void startActiveWorkout(){
        if (selectedWorkout != null) {
            parentActivity.navigateActiveWorkout(selectedWorkout);
        }
        else{
            Toast.makeText(getContext(), "You need to select a workout first", Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        beginWorkoutButton = view.findViewById(R.id.pre_workout_start_button);
        preActiveWorkoutListView = view.findViewById(R.id.pre_workout_list);
        preActiveWorkoutListView.setSelector(R.color.selectedListItem);

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
                view.setSelected(true);
                selectedWorkout = workouts.get(position);

            }
        });

    }



}
