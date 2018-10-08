package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment representing a view where the user can create custom workouts
 */
public class WorkoutCreatorFragment extends Fragment {

    private static List<WorkoutBlock>workoutBlocks= new ArrayList<>();

    private EditText workoutNameEditText;
    private EditText workoutDescriptionEditText;
    private Button addWorkoutBlockButton;
    private ListView workoutBlocksListView;
    private Button createWorkoutButton;

    private ArrayAdapter<WorkoutBlock> adapter;
    private MainActivity parentActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = (MainActivity)getActivity();
        view  = inflater.inflate(R.layout.fragment_workout_creator, container, false);
        initViews();
        initListeners();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, workoutBlocks);
        workoutBlocksListView.setAdapter(adapter);

        return view;
    }

    private void initViews() {
        workoutNameEditText = view.findViewById(R.id.workoutNameEditText);
        workoutDescriptionEditText = view.findViewById(R.id.workoutDescriptionEditText);
        addWorkoutBlockButton = view.findViewById(R.id.addWorkoutBlockButton);
        workoutBlocksListView = view.findViewById(R.id.workoutBlocksListView);
        createWorkoutButton = view.findViewById(R.id.createWorkoutButton);
    }

    private void initListeners() {

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setFragmentContainerContent(new WorkoutBlockCreatorFragment());
            }
        });

        createWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEditText = view.findViewById(R.id.workoutNameEditText);
                EditText descriptionEditText = view.findViewById(R.id.workoutDescriptionEditText);

                String name = nameEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                List<WorkoutBlock>newWorkoutBlocks = new ArrayList<>();
                newWorkoutBlocks.addAll(workoutBlocks);

                parentActivity.viewModel.addCustomWorkout(name, description, newWorkoutBlocks);

                workoutBlocks.clear();
                parentActivity.setFragmentContainerContent(new WorkoutTabFragment());
                Toast.makeText(getContext(), "New workout created!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void addWorkoutBlock(WorkoutBlock workoutBlock){
        workoutBlocks.add(workoutBlock);
        //adapter.notifyDataSetChanged();
    }


}
