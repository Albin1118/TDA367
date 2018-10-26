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
        assertTrue(tracker.getUser().getCustomExercises().get(0).isWeightBased());


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

    @Test
    public void loadBaseExercises_simpleExercises_added() {
        ITrainingTracker tracker = new TrainingTracker();
        List<ExerciseCategory> categories = new ArrayList<>();
        categories.add(ExerciseCategory.ARMS);
        IExercise e1 = new Exercise("N1", "U1", "D1", "I1", categories, true);
        IExercise e2 = new Exercise("N2", "U2", "D2", "I2", categories, false);
        List<IExercise> exercises = new ArrayList<>();
        exercises.add(e1);
        exercises.add(e2);
        assertEquals(tracker.getExercises().size(), 0);
        tracker.loadBaseExercises(exercises);
        assertEquals(tracker.getExercises().size(), 2);
        assertEquals(tracker.getExercises().get(0), e1);
        assertEquals(tracker.getExercises().get(1), e2);
    }

    @Test
    public void loadBaseWorkouts_simpleWorkouts_added() {
        ITrainingTracker tracker = new TrainingTracker();
        IWorkout w1 = new Workout("N1", "D1", new ArrayList<>());
        IWorkout w2 = new Workout("N2", "D2", new ArrayList<>());
        List<IWorkout> workouts = new ArrayList<>();
        workouts.add(w1);
        workouts.add(w2);
        assertEquals(tracker.getWorkouts().size(), 0);
        tracker.loadBaseWorkouts(workouts);
        assertEquals(tracker.getWorkouts().size(), 2);
        assertEquals(tracker.getWorkouts().get(0), w1);
        assertEquals(tracker.getWorkouts().get(1), w2);
    }

    @Test
    public void loadBaseChallenges_simpleChallenges_added() {
        ITrainingTracker tracker = new TrainingTracker();
        IChallenge c1 = new Challenge(new Exercise("N1", "U1", "D1", "I1", new ArrayList<>(), true));
        IChallenge c2 = new Challenge(new Exercise("N2", "U2", "D2", "I2", new ArrayList<>(), false));
        List<IChallenge> challenges = new ArrayList<>();
        challenges.add(c1);
        challenges.add(c2);
        assertEquals(tracker.getChallenges().size(), 0);
        tracker.loadBaseChallenges(challenges);
        assertEquals(tracker.getChallenges().size(), 2);
        assertEquals(tracker.getChallenges().get(0), c1);
        assertEquals(tracker.getChallenges().get(1), c2);
    }

    @Test
    public void checkIfCustom_customExercise_isTrue() {
        ITrainingTracker tracker = new TrainingTracker();
        tracker.addCustomExercise("N1", "U1", "D1", "I1", new ArrayList<>(), true);
        assertTrue(tracker.checkIfCustom(tracker.getExercises().get(0)));
    }

    @Test
    public void checkIfCustom_baseExercise_isFalse() {
        ITrainingTracker tracker = new TrainingTracker();
        IExercise e1 = new Exercise("N1", "U1", "D1", "I1", new ArrayList<>(), true);
        List<IExercise> baseExercises = new ArrayList<>();
        baseExercises.add(e1);
        tracker.loadBaseExercises(baseExercises);
        assertFalse(tracker.checkIfCustom(tracker.getExercises().get(0)));
    }

    @Test
    public void checkIfCustom_customWorkout_isTrue() {
        ITrainingTracker tracker = new TrainingTracker();
        tracker.addCustomWorkout(new Workout("N1", "D1", new ArrayList<>()));
        assertTrue(tracker.checkIfCustom(tracker.getWorkouts().get(0)));
    }

    @Test
    public void checkIfCustom_baseWorkout_isFalse() {
        ITrainingTracker tracker = new TrainingTracker();
        IWorkout w1 = new Workout("N1", "D1", new ArrayList<>());
        List<IWorkout> baseWorkouts = new ArrayList<>();
        baseWorkouts.add(w1);
        tracker.loadBaseWorkouts(baseWorkouts);
        assertFalse(tracker.checkIfCustom(tracker.getWorkouts().get(0)));
    }

}
