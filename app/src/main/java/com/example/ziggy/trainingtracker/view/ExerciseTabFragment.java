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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

/**
 * Fragment representing the exercise-tab, where the list of exercises is displayed
 */
public class ExerciseTabFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;
    private Button searchCategoryButton;
    private Spinner exerciseCategorySpinner;

    private ArrayAdapter<Exercise> adapter;

    private MainActivity parentActivity;
    private View view;
    private ExerciseTabViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentActivity = ((MainActivity)getActivity());
        parentActivity.setNavBarState(R.id.nav_exercises);
        viewModel = new ExerciseTabViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);
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

        adapter = new ArrayAdapter<Exercise>(getContext(), R.layout.exercise_list_item, R.id.exerciseNameTextView, viewModel.getAllExercises()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView exerciseNameTextView = (TextView) view.findViewById(R.id.exerciseNameTextView);
                TextView exerciseDescriptionTextView = (TextView) view.findViewById(R.id.exerciseDescriptionTextView);

                exerciseNameTextView.setText(viewModel.getExercises().get(position).getName());
                exerciseDescriptionTextView.setText(viewModel.getExercises().get(position).getDescription());
                return view;
            }
        };
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
                parentActivity.setFragmentContainerContent(new ExerciseCreatorFragment());
            }
        });

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExerciseDetailViewFragment fragment = new ExerciseDetailViewFragment();
                fragment.setExercise(viewModel.getExercises().get(position));
                parentActivity.setFragmentContainerContent(fragment);
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
