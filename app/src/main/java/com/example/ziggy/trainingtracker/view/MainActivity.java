package com.example.ziggy.trainingtracker.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager mViewPager;

    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initListeners();
        initDataBinding();
        setupViewPager(mViewPager);
    }

    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        mViewPager = findViewById(R.id.viewPagerContainer);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initListeners() {
        mViewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_dashboard:
                        setViewPager(0);
                        break;
                    case R.id.nav_workouts:
                        setViewPager(1);
                        break;
                    case R.id.nav_exercises:
                        setViewPager(2);
                        break;
                    case R.id.nav_settings:
                        setViewPager(3);
                        break;


                }
                return true;
            }
        });
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void initDataBinding() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
    }

    public void setupViewPager(ViewPager viewpager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StartPageFragment());
        adapter.addFragment(new WorkoutTabFragment());
        adapter.addFragment(new ExerciseTabFragment());
        adapter.addFragment(new SettingsFragment());
        adapter.addFragment(new ExerciseCreatorFragment());
        adapter.addFragment(new WorkoutCreatorFragment());
        viewpager.setAdapter(adapter);
    }
}
