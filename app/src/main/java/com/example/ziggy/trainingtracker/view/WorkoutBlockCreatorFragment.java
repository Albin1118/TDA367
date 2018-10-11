package com.example.ziggy.trainingtracker.view;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockCreatorFragment extends Fragment {

    private List<Exercise> exercises;

    private ListView selectExerciseListView;
    private Button addWorkoutBlockButton;

    private View view;
    private MainActivity parentActivity;
    private ArrayAdapter<Exercise> adapter;

    private Workout editableWorkout;
    private Workout originalWorkout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_workout_block_creator, container, false);
        exercises = parentActivity.viewModel.getExercises();

        initViews();
        initListeners();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, exercises);
        selectExerciseListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        selectExerciseListView.setAdapter(adapter);


        return view;
    }

    private void initViews() {
        selectExerciseListView = view.findViewById(R.id.selectExerciseListView);
        addWorkoutBlockButton = view.findViewById(R.id.addWorkoutBlockButton);
    }

    private void initListeners() {
        /*selectExerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parentActivity.viewModel.getExercises().get(position).toString(), Toast.LENGTH_SHORT).show();
                if(parentActivity.viewModel.getCustomExercises() != null && parentActivity.viewModel.preMadeExercises.contains(exercises.get(position))) {

                    parentActivity.setExerciseDetailView(exercises.get(position));
                    parentActivity.setViewPager(8);
                } else if (parentActivity.viewModel.getCustomExercises() != null && parentActivity.viewModel.getCustomExercises().contains(exercises.get(position))){
                    parentActivity.setCustomExerciseDetailView(exercises.get(position), position);
                    parentActivity.setViewPager(7);
                }
            }
        });*/

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int [] checkedItemPositions = selectExerciseListView.getCheckedItemPositions();
                //List<Exercise> checkedExercises = new ArrayList<>();
                WorkoutBlock workoutBlock = new WorkoutBlock();

                SparseBooleanArray checkedItems = selectExerciseListView.getCheckedItemPositions();
                if (checkedItems != null) {
                    for (int i=0; i<checkedItems.size(); i++) {
                        if (checkedItems.valueAt(i)) {
                            //Exercise checkedExercise = (Exercise) selectExerciseListView.getAdapter().getItem(i);
                            workoutBlock.addExercise(exercises.get(i), 1);
                        }
                    }
                }

                WorkoutCreatorFragment fragment = new WorkoutCreatorFragment();
                if(editableWorkout != null) {
                    fragment.setEditableWorkout(editableWorkout);
                    fragment.setOriginalWorkout(originalWorkout);
                }
                fragment.addWorkoutBlock(workoutBlock);
                parentActivity.setFragmentContainerContent(fragment);
            }
        });
    }

    public void setEditableWorkout(Workout editableWorkout) {
        this.editableWorkout = editableWorkout;
    }

    public void setOriginalWorkout(Workout originalWorkout) {
        this.originalWorkout = originalWorkout;
    }
}
