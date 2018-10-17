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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Fragment representing the exercise-tab, where the list of exercises is displayed
 */
public class ExerciseTabFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;
    private ArrayAdapter<Exercise> adapter;

    private MainActivity parentActivity;
    private View view;
    private Button searchCategoryButton;
    private Spinner exerciseCategorySpinner;
    private List<Exercise>exercises;
    private List<Exercise>customExercises;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);
        parentActivity = ((MainActivity)getActivity());
        parentActivity.setNavBarState(R.id.nav_exercises);
        exercises = new ArrayList<>(parentActivity.viewModel.getExercises());

        initViews();
        initListeners();


        adapter = new ArrayAdapter<Exercise>(getContext(), R.layout.exercise_list_item, R.id.exerciseNameTextView, exercises) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView exerciseNameTextView = (TextView) view.findViewById(R.id.exerciseNameTextView);
                TextView exerciseDescriptionTextView = (TextView) view.findViewById(R.id.exerciseDescriptionTextView);

                exerciseNameTextView.setText(exercises.get(position).getName());
                exerciseDescriptionTextView.setText(exercises.get(position).getDescription());
                return view;
            }
        };
        exerciseListView.setAdapter(adapter);

        return view;
    }

    private void initViews() {

        //Initiate visual components by id
        addExerciseButton = view.findViewById(R.id.addExerciseButton);
        exerciseListView = view.findViewById(R.id.exerciseList);
        exerciseCategorySpinner = view.findViewById(R.id.exerciseCategorySpinner);
        searchCategoryButton = view.findViewById(R.id.searchCategoryButton);

       //Style and populate spinner
        ArrayList<String> categories = parentActivity.viewModel.getCategoriesToString();
        ArrayAdapter<String> spinnerAdapter;
        spinnerAdapter = new ArrayAdapter<>(this.parentActivity, android.R.layout.simple_spinner_item, categories);

        //Dropdown layout style
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Attaching adapter to spinner
        exerciseCategorySpinner.setAdapter(spinnerAdapter);
    }

    private void initListeners() {
        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setFragmentContainerContent(new ExerciseCreatorFragment());
            }
        });

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), parentActivity.viewModel.getExercises().get(position).toString(), Toast.LENGTH_SHORT).show();
                ExerciseDetailViewFragment fragment = new ExerciseDetailViewFragment();
                fragment.setExercise(exercises.get(position));
                parentActivity.setFragmentContainerContent(fragment);
            }
        });
        searchCategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExerciseCategory category = ExerciseCategory.valueOf(exerciseCategorySpinner.getSelectedItem().toString());
                List<Exercise> exercisesInCategory = new ArrayList<>();
                for(int i=0; i<parentActivity.viewModel.getExercises().size(); i++) {
                    if (parentActivity.viewModel.getExercises().get(i).getCategories().contains(category)) {
                        exercisesInCategory.add(parentActivity.viewModel.getExercises().get(i));
                    }
                }
                setExercises(exercisesInCategory);
                adapter.notifyDataSetChanged();

            }
        });
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises.clear();
        this.exercises.addAll(exercises);
    }
}
