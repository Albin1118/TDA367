package com.example.ziggy.trainingtracker.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziggy.trainingtracker.R;

/**
 * Fragment representing the startPage-tab
 */
public class StartPageFragment extends Fragment {

    private NavigationManager navigationManager;
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_startpage, container, false);
        navigationManager = (MainActivity)getActivity();
        navigationManager.setNavBarState(R.id.nav_dashboard);

        return view;
    }

}
