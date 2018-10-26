package com.example.ziggy.trainingtracker.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.example.ziggy.trainingtracker.R;
import com.example.ziggy.trainingtracker.model.IExercise;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.viewmodel.WorkoutCreatorViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a view where the user can create a WorkoutBlock, by selecting a block-multiplier and
 * one/several exercises, and then add the block to the workout currently being built.
 */
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
        view  = inflater.inflate(R.layout.fragment_workoutblockcreator, container, false);
        initViews();
        initListeners();

        viewModel.resetWorkoutBlock();

        //adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice, viewModel.getExercises());
        adapter = new ExerciseCheckedListAdapter(getContext(), viewModel.getExercises());
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
                if (sets > 1) {
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

                if (selectExerciseListView.isItemChecked(position)) {
                    selectNumberOfReps(e, position);
/*
                    if (e.isWeightBased()) { //TODO change this back to get the input weight prompt on weighted exercises
                        selectNumberOfRepsAndWeight(e, position);
                    }
                    else{
                        selectNumberOfReps(e, position);
                    }*/
                }

                else {
                    viewModel.removeExercise(e);
                }

            }
        });

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(viewModel.workoutBlockIsEmpty()){
                    Toast.makeText(getContext(), "No block to display", Toast.LENGTH_SHORT).show();

                } else {
                    //Get the multiplier of the WorkoutBlock and add the block to the buildWorkout
                    int sets = Integer.parseInt(setsDisplay.getText().toString());
                    viewModel.addWorkoutBlock(sets);

                    //Create a list to be displayed and add the latest block
                    List<IWorkoutBlock> workoutBlockList = new ArrayList<>();
                    workoutBlockList.add(viewModel.getLatestBlock());

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
                            //Remove the WorkoutBlock after the preview
                            viewModel.removeLatestBlock();
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }


            }
        });

        addWorkoutBlockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewModel.workoutBlockIsEmpty()) {
                    Toast.makeText(getContext(), "You need to select at least one exercise!", Toast.LENGTH_SHORT).show();
                } else {
                    int sets = Integer.parseInt(setsDisplay.getText().toString());
                    viewModel.addWorkoutBlock(sets);
                    navigator.goBack();
                }

            }
        });

    }
    private void selectNumberOfReps(IExercise e, int position){
        //Create a dialog and set the title
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(e.getName());

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_inputreps, (ViewGroup) getView(), false);

        //Set the components of dialog_inputreps.xml
        final TextView dialogTitle = viewInflated.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Enter amount of " + e.getUnit());

        final EditText numberofUnitEditText = viewInflated.findViewById(R.id.numberofUnitEditText);

        final TextView unitTextView = viewInflated.findViewById(R.id.unitTextView);
        unitTextView.setText(e.getUnit());

        //Set the content of the main dialog view
        builder.setView(viewInflated);

        // Set up the OK-button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int numberofUnits = Integer.parseInt(numberofUnitEditText.getText().toString());
                viewModel.addExercise(e, numberofUnits);
                dialog.cancel();
            }
        });

        //Set up the Cancel-button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                selectExerciseListView.setItemChecked(position, false);
            }
        });

        builder.show();
        numberofUnitEditText.requestFocus();

    }

    private void selectNumberOfRepsAndWeight(IExercise e, int position){
        //Create a dialog and set the title
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(e.getName());

        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.dialog_inputrepsandweight, (ViewGroup) getView(), false);

        //Set the components of dialog_inputreps.xml
        final TextView dialogTitle = viewInflated.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Enter amount of " + e.getUnit());

        final EditText numberofUnitEditText = viewInflated.findViewById(R.id.numberofUnitEditText);
        final EditText weightEditText = viewInflated.findViewById(R.id.weightEditText);

        final TextView unitTextView = viewInflated.findViewById(R.id.unitTextView);
        unitTextView.setText(e.getUnit());

        //Set the content of the main dialog view
        builder.setView(viewInflated);

        // Set up the OK-button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int numberofUnits = Integer.parseInt(numberofUnitEditText.getText().toString());
                double weight = Integer.parseInt(weightEditText.getText().toString());
                viewModel.addExercise(e, numberofUnits); //TODO when we have fixed how to connect the three lists, pass weight here
                dialog.cancel();
            }
        });

        //Set up the Cancel-button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                selectExerciseListView.setItemChecked(position, false);
            }
        });

        builder.show();
        numberofUnitEditText.requestFocus();

    }
    
}
