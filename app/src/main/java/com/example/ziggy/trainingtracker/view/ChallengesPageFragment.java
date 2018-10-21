package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.Challenge;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

public class ChallengesPageFragment extends Fragment {

    private ListView challengeListView;

    private ArrayAdapter<Challenge> adapter;

    private View view;
    private ExerciseTabViewModel viewModel;
    private NavigationManager navigator;

    public static ChallengesPageFragment newInstance(ExerciseTabViewModel viewModel, NavigationManager navigator) {
        ChallengesPageFragment fragment = new ChallengesPageFragment();
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
        view  = inflater.inflate(R.layout.fragment_challenges_page, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        challengeListView = view.findViewById(R.id.challengeListView);
        adapter = new ArrayAdapter<Challenge>(getContext(), R.layout.challenge_list_item, R.id.challengeNameTextView, viewModel.getChallenges()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView challengeNameTextView = view.findViewById(R.id.challengeNameTextView);

                challengeNameTextView.setText(viewModel.getChallenges().get(position).getName());
                return view;
            }
        };
        challengeListView.setAdapter(adapter);
    }

    private void initListeners() {

    }
}
