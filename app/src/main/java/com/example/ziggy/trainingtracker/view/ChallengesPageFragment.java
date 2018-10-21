package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IChallenge;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

public class ChallengesPageFragment extends Fragment {

    private ListView challengeListView;
    private ArrayAdapter<IChallenge> adapter;

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
        view  = inflater.inflate(R.layout.fragment_exercisetabchallenges, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        challengeListView = view.findViewById(R.id.challengeListView);

        adapter = new ArrayAdapter<IChallenge>(getContext(), R.layout.item_challenge, R.id.challengeNameTextView, viewModel.getChallenges()) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView challengeNameTextView = (TextView) view.findViewById(R.id.challengeNameTextView);
                TextView challengeScoreTextView = (TextView) view.findViewById(R.id.challengeScoreTextView);
                Button startChallengeButton = (Button) view.findViewById(R.id.startChallengeButton);

                challengeNameTextView.setText(viewModel.getChallenges().get(position).getName());
                challengeScoreTextView.setText(String.valueOf(viewModel.getChallenges().get(position).getScore()));

                startChallengeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        navigator.navigateActiveChallenge(viewModel.getChallenges().get(position));
                    }
                });


                return view;
            }
        };
        challengeListView.setAdapter(adapter);
    }

    private void initListeners() {

    }
}
