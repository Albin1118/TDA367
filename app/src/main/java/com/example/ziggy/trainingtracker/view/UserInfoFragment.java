package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.UserInfoViewModel;

/**
 * Fragment for displaying user info
 */

public class UserInfoFragment extends Fragment {

    private TextView usernameTextView;
    private TextView nameTextView;
    private TextView weightTextView;
    private TextView heightTextView;

    private View view;
    private UserInfoViewModel viewModel;

    public static UserInfoFragment newInstance(UserInfoViewModel viewModel) {
        UserInfoFragment fragment = new UserInfoFragment();
        fragment.setViewModel(viewModel);
        return fragment;
    }

    public void setViewModel(UserInfoViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_userinfo, container, false);
        initViews();
        initListeners();

        return view;

    }

    private void initViews() {

        usernameTextView = view.findViewById(R.id.usernameTextView);
        nameTextView = view.findViewById(R.id.nameTextView);
        weightTextView = view.findViewById(R.id.weightTextview);
        heightTextView = view.findViewById(R.id.heightTextView);

        usernameTextView.setText("Username: " + viewModel.getUsername());
        nameTextView.setText("Name: " + viewModel.getName());
        weightTextView.setText("Weight: " + viewModel.getWeight() + " kg");
        heightTextView.setText("Height: " + viewModel.getHeight() + " cm");

    }

    private void initListeners() {

    }
}



