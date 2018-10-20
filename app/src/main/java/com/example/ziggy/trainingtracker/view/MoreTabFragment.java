package com.example.ziggy.trainingtracker.view;

import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.ziggy.trainingtracker.R;

/**
 * Fragment representing the "more" tab where the user can access app-wide settings and content
 */

public class MoreTabFragment extends Fragment {


    private EditText weightEditText;
    private Switch nightModeSwitch;
    private Button statisticsButton;

    private View view;
    private NavigationManager navigationManager;

    SharedPreferences themeSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeSettings = getActivity().getSharedPreferences("Themes", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_more_tab, container, false);
        navigationManager = (MainActivity)getActivity();
        navigationManager.setNavBarState(R.id.nav_more);

        initViews();
        initListeners();

        return view;
    }

    private void initViews() {
        weightEditText = view.findViewById(R.id.weight_edit_text);
        nightModeSwitch = view.findViewById(R.id.night_mode_switch);
        statisticsButton = view.findViewById(R.id.statistics_button);

        if (themeSettings.getInt("theme", 0) == R.style.DarkTheme) {
            nightModeSwitch.setChecked(true);
        }
    }


    private void initListeners() {
        statisticsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationManager.navigateStatistics();
            }
        });

        nightModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor themeSettingsEditor = themeSettings.edit();
                if (isChecked) {
                    themeSettingsEditor.putInt("theme", R.style.DarkTheme);
                } else {
                    themeSettingsEditor.putInt("theme", R.style.AppTheme);
                }
                themeSettingsEditor.apply();
                getActivity().recreate();
            }
        });
    }
}
