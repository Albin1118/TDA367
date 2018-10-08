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
import android.widget.Toast;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment representing the workout-tab, where the list of workouts is displayed
 */
public class WorkoutTabFragment extends Fragment {

    private FloatingActionButton addWorkoutButton;
    private ListView workoutList;
    private ArrayAdapter<Workout>adapter;

    private MainActivity parentActivity;
    private View view;
    private List <Workout> workouts;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_tab, container, false);
        parentActivity = (MainActivity)getActivity();
        parentActivity.setNavBarState(R.id.nav_workouts);
        workouts = parentActivity.viewModel.getWorkouts();
        initViews();
        initListeners();

        //adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, workouts);
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

                Workout w = workouts.get(position);

                WorkoutDetailViewFragment fragment = new WorkoutDetailViewFragment();
                fragment.setWorkoutDetailViewComponents(w.getName(),w.getDescription(), w.getBlocks());
                fragment.setWorkoutNameTextView(w.getName());
                fragment.setWorkoutDescriptionTextView(w.getDescription());
                fragment.setWorkout(w);

                parentActivity.setFragmentContainerContent(fragment);
            }
        });

        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setFragmentContainerContent(new WorkoutCreatorFragment());
            }
        });
    }

}
