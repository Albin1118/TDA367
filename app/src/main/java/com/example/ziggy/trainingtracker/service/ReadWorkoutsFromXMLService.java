package com.example.ziggy.trainingtracker.service;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;

public class ReadWorkoutsFromXMLService {

    private Document dom;
    private Map<String, Workout> workouts;
    private Map<String, Exercise> exercises;

    public ReadWorkoutsFromXMLService(List<Exercise> baseExercises) {
        this.workouts = new LinkedHashMap<>();
        this.exercises = new LinkedHashMap<>();
        for (Exercise e : baseExercises) {
            this.exercises.put(e.getName(), e);
        }
    }

    /**
     * Read res/raw/workouts.xml using the elements and their contents to create Workout objects
     * and add them to the map of Workouts that gets returned.
     * @return A list of Workouts loaded from the XML.
     */
    public List<Workout> readWorkouts() {
        parseXMLFile();
        parseDocument();
        return new ArrayList<>(workouts.values());
    }

    /**
     * Parse the content of the xml file and put it in the Document dom.
     */
    private void parseXMLFile() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(this.getClass().getClassLoader().getResourceAsStream("res/raw/workouts.xml"));
        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Workout objects out of the elements in the document and add them to the list of Workouts.
     */
    private void parseDocument() {
        //get the root element
        Element rootElement = dom.getDocumentElement();

        //get a NodeList of workout elements
        NodeList nl = rootElement.getElementsByTagName("workout");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength();i++) {
                //get the workout element
                Element element = (Element) nl.item(i);
                try {
                    //get the Workout object
                    Workout w = getWorkout(element);
                    //add it to list
                    workouts.put(w.getName(), w);
                } catch (IllegalArgumentException iae) {
                    iae.printStackTrace();
                    System.out.println("** workouts.xml error, workout: \"" + element.getAttribute("name") + "\"");
                    System.out.println(" " + iae.getMessage());
                }
            }
        }
    }

    /**
     * Take a workout element, read the values in, create a Workout and return it.
     * @param workoutElement A workout element.
     * @return A new Workout object.
     */
    private Workout getWorkout(Element workoutElement) throws IllegalArgumentException {
        String name = workoutElement.getAttribute("name");
        if (name.trim().equals("") || workouts.containsKey(name))
            throw new IllegalArgumentException("A workout cannot have an empty or duplicate name.");

        String description = getTextValue(workoutElement, "description");
        if (description == null)
            description = "-no description-";

        List<WorkoutBlock> blocks = new ArrayList<>();
        NodeList nl = workoutElement.getElementsByTagName("block");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength();i++) {
                //get the block element
                Element element = (Element)nl.item(i);
                //get the WorkoutBlock object
                WorkoutBlock wb = getBlock(element);
                //add it to list
                blocks.add(wb);
            }
        }

        Workout w = new Workout(name);
        w.setDescription(description);
        w.setBlocks(blocks);
        return w;
    }

    /**
     * Take a block element, read the values in, create a WorkoutBlock and return it.
     * @param blockElement A block element.
     * @return A new WorkoutBlock object.
     */
    private WorkoutBlock getBlock(Element blockElement) throws IllegalArgumentException {
        int multiplier;
        try {
            multiplier = Integer.parseInt(blockElement.getAttribute("x"));
            if (multiplier < 1)
                throw new IllegalArgumentException("Attribute n can not be less than 1.");
        } catch (NumberFormatException e) { //If the attribute x is missing use the default multiplier = 1
            multiplier = 1;
        }

        WorkoutBlock block = new WorkoutBlock();
        block.setMultiplier(multiplier);

        NodeList nl = blockElement.getElementsByTagName("exercise");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength();i++) {
                //get the exercise element
                Element element = (Element)nl.item(i);
                //get the Exercise object
                Exercise e = getExercise(element);
                int amount;
                try {
                    amount = Integer.parseInt(element.getAttribute("n"));
                } catch (NumberFormatException ex) { //If the attribute n is missing
                    throw new IllegalArgumentException("Missing attribute n in the Exercise: \"" + e.getName() + "\".");
                }
                //add it to block
                block.addExercise(e, amount);
            }
        }

        return block;
    }

    /**
     * Take an exercise element, read the values in, create an Exercise and return it.
     * @param exerciseElement An exercise element.
     * @return A new Exercise object.
     */
    private Exercise getExercise(Element exerciseElement) {
        String name = exerciseElement.getTextContent();
        Exercise e = this.exercises.get(name);
        if (e == null)
            throw new IllegalArgumentException("Cannot find Exercise: \"" + name + "\".");
        return e;
    }

    /**
     * Take an XML element and a tag name, look for the tag and get the text content.
     * I.e for <workout><description>Example description</description></workout>, if
     * the Element points to a workout node and tagName is 'description' it will return "Example description".
     * @param element An element that contains a tagName element.
     * @param tagName A String that represents the element to get the text value from.
     * @return The String content in the "tagName"-element.
     */
    private String getTextValue(Element element, String tagName) {
        String textVal = null;
        NodeList nl = element.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    /**
     * Call getTextValue and return it as an int.
     * @param element An element that contains a tagName element.
     * @param tagName A String that represents the element to get the int value from.
     * @return The int value of getTextValue.
     */
    private int getIntValue(Element element, String tagName) {
        int intValue;
        try {
            intValue = Integer.parseInt(getTextValue(element, tagName));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            intValue = 0;
        }
        return intValue;
    }
}
