package com.example.ziggy.trainingtracker.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutCreatorViewModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBlockCreatorFragment extends Fragment {

    private Button decrementSetButton;
    private TextView setsDisplay;
    private Button incrementSetButton;
    private ListView selectExerciseListView;
    private Button previewButton;
    private Button addWorkoutBlockButton;

    private ArrayAdapter<IExercise> adapter;

    private View view;
    private WorkoutCreatorViewModel viewModel;
    private NavigationManager navigator;

    private IWorkoutBlock block;

    public static WorkoutBlockCreatorFragment newInstance(WorkoutCreatorViewModel viewModel, NavigationManager navigator) {
        WorkoutBlockCreatorFragment fragment = new WorkoutBlockCreatorFragment();
        fragment.setViewModel(viewModel);
        fragment.setNavigator(navigator);
        return fragment;
    }

    public void setViewModel(WorkoutCreatorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setNavigator(NavigationManager navigator) {
        this.navigator = navigator;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_workout_block_creator, container, false);
        initViews();
        initListeners();

        block = new WorkoutBlock();

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, viewModel.getExercises());
        selectExerciseListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        selectExerciseListView.setAdapter(adapter);

        return view;
    }

    private void initViews() {
        decrementSetButton = view.findViewById(R.id.decrementSetButton);
        setsDisplay = view.findViewById(R.id.setsdisplay);
        incrementSetButton = view.findViewById(R.id.incrementSetButton);
        selectExerciseListView = view.findViewById(R.id.selectExerciseListView);
        previewButton = view.findViewById(R.id.previewButton);
        addWorkoutBlockButton = view.findViewById(R.id.addWorkoutBlockButton);
    }

    private void initListeners() {

        decrementSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                if(sets > 1){
                    sets--;
                }
                setsDisplay.setText(String.valueOf(sets));
            }
        });

        incrementSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                sets++;
                setsDisplay.setText(String.valueOf(sets));
            }
        });

        selectExerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                IExercise e = viewModel.getExercises().get(position);

                if(selectExerciseListView.isItemChecked(position)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle(e.getName());

                    View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.input_reps_dialog, (ViewGroup) getView(), false);

                    final TextView dialogTitle = viewInflated.findViewById(R.id.dialogTitle);
                    dialogTitle.setText("Enter amount of " + e.getUnit());

                    final EditText numberofUnitEditText = viewInflated.findViewById(R.id.numberofUnitEditText);
                    numberofUnitEditText.setTextColor(Color.WHITE);

                    final TextView unitTextView = viewInflated.findViewById(R.id.unitTextView);
                    unitTextView.setText(e.getUnit());


                    //Set the content of the main dialog view
                    builder.setView(viewInflated);

                    // Set up the button
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int numberofUnits = Integer.parseInt(numberofUnitEditText.getText().toString());
                            //e.setNumberofUnit(numberofUnits);
                            block.addExercise(e, numberofUnits);
                            dialog.cancel();
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            selectExerciseListView.setItemChecked(position, false);
                        }
                    });

                    builder.show();
                    numberofUnitEditText.requestFocus();

                }else {
                   block.removeExercise(e);
                }

            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the current WorkoutBlock
                //WorkoutBlock w = buildWorkoutBlock();
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                block.setMultiplier(sets);

                List<IWorkoutBlock>workoutBlockList = new ArrayList<>();
                workoutBlockList.add(block);

                //Create the dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Preview");

                //Create a ListView to be displayed in the dialog
                final ListView workoutBlockListView = new ListView(getContext());
                WorkoutBlockListAdapter adapter = new WorkoutBlockListAdapter(getContext(), workoutBlockList);
                workoutBlockListView.setAdapter(adapter);

                //Set the main dialog view to display the list of blocks
                builder.setView(workoutBlockListView);

                // Set up the button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //WorkoutBlock workoutBlock = buildWorkoutBlock();
                int sets = Integer.parseInt(setsDisplay.getText().toString());
                block.setMultiplier(sets);

                viewModel.getBuildWorkout().addBlock(block);
                navigator.goBack();
            }
        });


    }

    private IWorkoutBlock buildWorkoutBlock(){

        IWorkoutBlock workoutBlock = new WorkoutBlock();

        int sets = Integer.parseInt(setsDisplay.getText().toString());
        workoutBlock.setMultiplier(sets);

        SparseBooleanArray checkedItems = selectExerciseListView.getCheckedItemPositions();

        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    //Exercise checkedExercise = (Exercise) selectExerciseListView.getAdapter().getItem(i);
                    int position = checkedItems.keyAt(i);
                    workoutBlock.addExercise(viewModel.getExercises().get(position), 1);
                }
            }
        }

        return workoutBlock;
    }
}
