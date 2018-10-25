package com.example.ziggy.trainingtracker.model;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
public class TrainingTrackerTest {

    @Test
    public void addCustomExercise_isCorrect() {
        ITrainingTracker tracker = new TrainingTracker();
        String name = "Pushups", description = "Very hard", instructions = "Don´t hit your nose", unit = "reps";
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ARMS);
        tracker.addCustomExercise(name, unit, description, instructions, categories, true);
        assertEquals(tracker.getUser().getCustomExercises().size(), 1);
        assertEquals(tracker.getUser().getCustomExercises().get(0).getName(), name);
        assertEquals(tracker.getUser().getCustomExercises().get(0).getUnit(), unit);
        assertEquals(tracker.getUser().getCustomExercises().get(0).getDescription(), description);
        assertEquals(tracker.getUser().getCustomExercises().get(0).getInstructions(), instructions);
        assertEquals(tracker.getUser().getCustomExercises().get(0).getCategories(), categories);
        assertEquals(tracker.getUser().getCustomExercises().get(0).isWeightBased(), true);


    }

    @Test
    public void removeCustomExercise_isCorrect() {
        ITrainingTracker tracker = new TrainingTracker();
        String name = "Pushups", description = "Very hard", instructions = "Don´t hit your nose", unit = "reps";
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ARMS);
        tracker.addCustomExercise(name, unit, description, instructions, categories, true);
        assertEquals(tracker.getUser().getCustomExercises().size(), 1);
        tracker.removeCustomExercise(tracker.getUser().getCustomExercises().get(0));
        assertEquals(tracker.getUser().getCustomExercises().size(), 0);
    }

    @Test
    public void addCustomWorkout_isCorrect() {
        ITrainingTracker tracker = new TrainingTracker();
        String name = "Hard workout", description = "Very hard";
        List<IWorkoutBlock> blocks = new ArrayList<>();
        blocks.add(new WorkoutBlock());
        IWorkout w = new Workout(name, description, blocks);
        tracker.addCustomWorkout(w);
        assertEquals(tracker.getUser().getCustomWorkouts().size(), 1);
        assertEquals(tracker.getUser().getCustomWorkouts().get(0).getName(), name);
        assertEquals(tracker.getUser().getCustomWorkouts().get(0).getDescription(), description);
        assertEquals(tracker.getUser().getCustomWorkouts().get(0).getBlocks(), blocks);
    }

    @Test
    public void removeCustomWorkout_isCorrect() {
        ITrainingTracker tracker = new TrainingTracker();
        String name = "Hard workout", description = "Very hard";
        List<IWorkoutBlock> blocks = new ArrayList<>();
        blocks.add(new WorkoutBlock());
        IWorkout w = new Workout(name, description, blocks);
        tracker.addCustomWorkout(w);
        assertEquals(tracker.getUser().getCustomWorkouts().size(), 1);
        tracker.removeCustomWorkout(w);
        assertEquals(tracker.getUser().getCustomWorkouts().size(), 0);
    }

}
