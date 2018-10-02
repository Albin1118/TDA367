package com.example.ziggy.trainingtracker.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
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

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;

import java.util.List;

public class ExerciseTabFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;
    private ArrayAdapter<Exercise> adapter;

    private MainActivity parentActivity;
    private View view;
    private List<Exercise>exercises;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);
        exercises = parentActivity.viewModel.getExercises();

        initViews();
        initListeners();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, exercises);
        exerciseListView.setAdapter(adapter);


        return view;
    }

    private void initViews() {
        addExerciseButton = view.findViewById(R.id.addExerciseButton);
        exerciseListView = view.findViewById(R.id.exerciseList);
    }

    private void initListeners() {
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setViewPager(4);
            }
        });

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parentActivity.viewModel.getExercises().get(position).toString(), Toast.LENGTH_SHORT).show();
                parentActivity.setExerciseDetailView(exercises.get(position));
                parentActivity.setViewPager(7);
            }
        });
    }

    private void customExerciseAdded() {
        parentActivity.viewModel.getNewCustomExercise().observe(this, new Observer<Exercise>() {
            @Override
            public void onChanged(@Nullable Exercise exercises) {
                adapter.clear();
                adapter.addAll(parentActivity.viewModel.getExercises());
            }
        });
    }
}
