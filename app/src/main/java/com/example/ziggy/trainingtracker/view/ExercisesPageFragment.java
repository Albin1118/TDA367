package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

public class ExercisesPageFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;
    private Button searchCategoryButton;
    private Spinner exerciseCategorySpinner;

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
        view  = inflater.inflate(R.layout.fragment_exercises_page, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        //Initiate visual components by id
        addExerciseButton = view.findViewById(R.id.addExerciseButton);
        exerciseListView = view.findViewById(R.id.exerciseList);
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);
        searchCategoryButton = view.findViewById(R.id.searchCategoryButton);

        ExerciseListAdapter adapter = new ExerciseListAdapter(getContext(), viewModel.getExercises());

        exerciseListView.setAdapter(adapter);

        //Style and populate spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, viewModel.getCategories());
        //Dropdown layout style
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Attaching adapter to spinner
        exerciseCategorySpinner.setAdapter(spinnerAdapter);
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
                navigator.navigateExerciseDetailView(viewModel.getExercises().get(position));
            }
        });

        searchCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.sortExercisesByCategory(exerciseCategorySpinner.getSelectedItem().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
}
