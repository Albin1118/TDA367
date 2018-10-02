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

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_tab, container, false);
        initViews();
        initListeners();



        //Used for testing
        List <Exercise> exercises = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            exercises.add(new Exercise("Pre-made exercise", "This is a pre-made exercise", "unit"));
        }

        final List <Workout> workouts = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Workout w = new Workout("Pre-made workout");
            w.setDescription("This is a pre-made workout");
            WorkoutBlock block = new WorkoutBlock();
            for (Exercise e : exercises) {
                block.addExercise(e, 10);
            }
            List<WorkoutBlock> blocks = new ArrayList<>();
            blocks.add(block);
            w.setBlocks(blocks);
            workouts.add(w);
        }

        ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(getContext(), android.R.layout.simple_list_item_1, workouts);

        workoutList.setAdapter(adapter);
        workoutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), workouts.get(position).toString(), Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setWorkoutDetailView(workouts.get(position));
                ((MainActivity)getActivity()).setViewPager(6);
            }
        });


        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).setViewPager(5);
            }
        });

        return view;
    }

    private void initViews() {
        addWorkoutButton = view.findViewById(R.id.addWorkoutButton);
        workoutList = view.findViewById(R.id.workoutList);
    }

    private void initListeners() {

    }

}
