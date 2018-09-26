package com.example.ziggy.trainingtracker;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;
    private MainViewModel mainViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPagerContainer);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        setupViewPager(mViewPager);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
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
    };

    public void setupViewPager(ViewPager viewpager) {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new StartPageFragment());
        adapter.addFragment(new WorkoutTabFragment());
        adapter.addFragment(new ExercisesFragment());
        adapter.addFragment(new SettingsFragment());
        adapter.addFragment(new CreateExerciseFragment());
        adapter.addFragment(new WorkoutCreatorFragment());
        viewpager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }
}
