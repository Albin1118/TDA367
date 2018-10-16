package com.example.ziggy.trainingtracker.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;

import java.util.ArrayList;

/**
 * Fragment representing the settings-tab where the user can change the app settings
 */

public class SettingsFragment extends Fragment {


    private View view;
    private EditText weightEditText;
    private Button statisticsButton;

    private MainActivity parentActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_settings, container, false);
        parentActivity = (MainActivity)getActivity();
        parentActivity.setNavBarState(R.id.nav_settings);

        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        weightEditText = view.findViewById(R.id.weight_edit_text);
        statisticsButton = view.findViewById(R.id.statistics_button);
    }


    private void initListeners() {
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentActivity.setFragmentContainerContent(new StatisticsFragment());
            }
        });
    }
}
