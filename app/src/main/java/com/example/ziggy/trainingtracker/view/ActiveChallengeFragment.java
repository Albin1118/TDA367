package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.ActiveChallengeViewModel;


public class ActiveChallengeFragment extends Fragment {

    TextView challengeNameTextView;
    TextView challengeDescriptionTextView;
    EditText challengeScoreEditText;
    Button finishChallengeButton;

    private View view;
    private ActiveChallengeViewModel viewModel;
    private NavigationManager navigator;

    public static ActiveChallengeFragment newInstance(ActiveChallengeViewModel viewModel, NavigationManager navigator) {
        ActiveChallengeFragment fragment = new ActiveChallengeFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(ActiveChallengeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_activechallenge, container, false);
        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        challengeNameTextView = view.findViewById(R.id.challengeNameTextView);
        challengeDescriptionTextView = view.findViewById(R.id.challengeDescriptionTextView);
        challengeScoreEditText = view.findViewById(R.id.challengeScoreEditText);
        finishChallengeButton = view.findViewById(R.id.finishChallengeButton);



        challengeNameTextView.setText(viewModel.getChallenge().getName());
        challengeDescriptionTextView.setText(viewModel.getChallenge().getDescription());
        challengeScoreEditText.setHint(viewModel.getChallenge().getUnit());

    }

    private void initListeners() {
        finishChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    viewModel.finishChallenge(Integer.parseInt(challengeScoreEditText.getText().toString()));
                    navigator.goBack();
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Give an integer input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
