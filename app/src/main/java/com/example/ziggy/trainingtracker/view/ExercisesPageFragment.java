package com.example.ziggy.trainingtracker.view;

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

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

import java.util.List;

public class ExercisesPageFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;

    private ArrayAdapter<IExercise> adapter;

    private View view;
    private ExerciseTabViewModel viewModel;
    private NavigationManager navigator;

    public static ExercisesPageFragment newInstance(ExerciseTabViewModel viewModel, NavigationManager navigator) {
        ExercisesPageFragment fragment = new ExercisesPageFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ExerciseTabViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercisetabexercises, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        //Initiate visual components by id
        addExerciseButton = view.findViewById(R.id.addExerciseButton);
        exerciseListView = view.findViewById(R.id.exerciseList);

        adapter = new ExerciseListAdapter(getContext(), viewModel.getAllExercises());
        exerciseListView.setAdapter(adapter);
    }

    private void initListeners() {
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigator.navigateExerciseCreator();
            }
        });

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                navigator.navigateExerciseDetailView(viewModel.getExercise(position));
            }
        });

        viewModel.getExercisesLiveData().observe(this, new Observer<List<IExercise>>() {
            @Override
            public void onChanged(@Nullable List<IExercise> exercises) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
