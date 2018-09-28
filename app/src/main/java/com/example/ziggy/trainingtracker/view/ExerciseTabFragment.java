package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Exercise;

public class ExerciseTabFragment extends Fragment {

    private FloatingActionButton addExerciseButton;
    private ListView exerciseListView;

    private MainActivity parentActivity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        parentActivity = ((MainActivity)getActivity());
        view  = inflater.inflate(R.layout.fragment_exercise_tab, container, false);

        addExerciseButton = (FloatingActionButton) view.findViewById(R.id.addExerciseButton);
        exerciseListView = (ListView) view.findViewById(R.id.exerciseList);

        ArrayAdapter<Exercise> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, parentActivity.viewModel.getExercises());
        exerciseListView.setAdapter(adapter);

        addExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setViewPager(4);
            }
        });


        return view;
    }
}
