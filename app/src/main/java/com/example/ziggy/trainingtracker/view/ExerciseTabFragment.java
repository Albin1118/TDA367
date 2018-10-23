package com.example.ziggy.trainingtracker.view;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.viewmodel.ExerciseTabViewModel;

/**
 * Fragment representing the exercise-tab, where the list of exercises is displayed
 */
public class ExerciseTabFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private PopupMenu sortMenu;

    private View view;
    private ExerciseTabViewModel viewModel;
    private NavigationManager navigator;

    public static ExerciseTabFragment newInstance(ExerciseTabViewModel viewModel, NavigationManager navigator) {
        ExerciseTabFragment fragment = new ExerciseTabFragment();
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return ExercisesPageFragment.newInstance(viewModel, navigator);
                    case 1:
                        return ChallengesPageFragment.newInstance(viewModel, navigator);
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_exercisetab, container, false);
        initViews();
        initListeners();
        navigator.setNavBarState(R.id.nav_exercises);
        setHasOptionsMenu(true);

        return view;
    }

    private void initViews() {
        toolbar = view.findViewById(R.id.toolbar);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        viewPager.setAdapter(pagerAdapter);
        toolbar.inflateMenu(R.menu.menu_exercisetabexercises);
        initSortMenu();
    }

    private void initListeners() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setBackgroundColor((int) argbEvaluator.evaluate((position + positionOffset), getColor(R.color.colorPrimary), getColor(R.color.colorSecondary)));
                toolbar.setBackgroundColor((int) argbEvaluator.evaluate((position + positionOffset), getColor(R.color.colorPrimary), getColor(R.color.colorSecondary)));
                getActivity().getWindow().setStatusBarColor((int) argbEvaluator.evaluate((position + positionOffset), getColor(R.color.colorPrimaryDark), getColor(R.color.colorSecondaryDark)));
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    default:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                sortMenu.show();
                return true;
            }
        });
    }

    private void initSortMenu() {
        sortMenu = new PopupMenu(getContext(), toolbar);
        sortMenu.setGravity(Gravity.END);
        sortMenu.getMenu().add(Menu.NONE, 0, 0, "ALL");
        for (int i = 1; i <= viewModel.getCategories().size(); i++) {
            sortMenu.getMenu().add(Menu.NONE, i, i, viewModel.getCategories().get(i-1));
        }

        sortMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == 0) {
                    viewModel.clearSorting();
                } else {
                    viewModel.sortByExerciseCategory(viewModel.getCategories().get(item.getItemId()-1));
                }
                return true;
            }
        });
    }

    private int getColor(int id) {
        return getResources().getColor(id, getContext().getTheme());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Reset status bar color
        getActivity().getWindow().setStatusBarColor(getColor(R.color.colorPrimaryDark));
    }
}
