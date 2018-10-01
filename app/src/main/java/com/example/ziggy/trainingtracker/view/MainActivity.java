package com.example.ziggy.trainingtracker.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.ziggy.trainingtracker.viewmodel.MainViewModel;
import com.example.ziggy.trainingtracker.R;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager mViewPager;
    private MenuItem prevMenuItem;

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

    private void initListeners() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment currentFragment = null;

                switch (menuItem.getItemId()){
                    case R.id.nav_dashboard:
                        //currentFragment = new StartPageFragment();
                        setViewPager(0);
                        break;
                    case R.id.nav_workouts:
                        //currentFragment = new WorkoutTabFragment();
                        setViewPager(1);
                        break;
                    case R.id.nav_exercises:
                        //currentFragment = new ExercisesFragment();
                        setViewPager(2);
                        break;

                    case R.id.nav_settings:
                        //currentFragment = new StartPageFragment();
                        setViewPager(3);
                        break;


                }

                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                //       currentFragment).commit();
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
