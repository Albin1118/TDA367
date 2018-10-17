package com.example.ziggy.trainingtracker.service;

import com.example.ziggy.trainingtracker.model.Exercise;
import com.example.ziggy.trainingtracker.model.ExerciseCategory;

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

public class ReadExercisesFromXMLService {

    private Document dom;
    private Map<String, Exercise> exercises;

    public ReadExercisesFromXMLService() {
        this.exercises = new LinkedHashMap<>();
    }

    /**
     * Read res/raw/exercises.xml using the elements and their contents to create Exercise objects
     * and add them to the map of Exercises that gets returned.
     * @return A list of Exercises loaded from the XML.
     */
    public List<Exercise> readExercises() {
        parseXMLFile();
        parseDocument();
        return new ArrayList<>(exercises.values());
    }

    /**
     * Parse the content of the xml file and put it in the Document dom.
     */
    private void parseXMLFile() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.parse(this.getClass().getClassLoader().getResourceAsStream("res/raw/exercises.xml"));
        } catch(ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Exercise objects out of the elements in the document and add them to the list of Workouts.
     */
    private void parseDocument() {
        //get the root element
        Element rootElement = dom.getDocumentElement();

        //get a NodeList of exercise elements
        NodeList nl = rootElement.getElementsByTagName("exercise");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength();i++) {
                //get the workout element
                Element element = (Element) nl.item(i);
                try {
                    //get the Exercise object
                    Exercise e = getExercise(element);
                    //add it to list
                    exercises.put(e.getName(), e);
                } catch (IllegalArgumentException iae) {
                    iae.printStackTrace();
                    System.out.println("** exercises.xml error, exercise: \"" + element.getAttribute("name") + "\"");
                    System.out.println(" " + iae.getMessage());
                }
            }
        }
    }

    /**
     * Take an exercise element, read the values in, create an Exercise and return it.
     * @param exerciseElement An exercise element.
     * @return A new Exercise object.
     */
    private Exercise getExercise(Element exerciseElement) throws IllegalArgumentException {
        String name = exerciseElement.getAttribute("name");
        if (name.trim().isEmpty() || exercises.containsKey(name))
            throw new IllegalArgumentException("An exercise cannot have an empty or duplicate name.");

        String description = getTextValue(exerciseElement, "description");
        if (description == null || description.trim().isEmpty())
            description = "-no description-";

        String instructions = getTextValue(exerciseElement, "instructions");
        if (instructions == null || instructions.trim().isEmpty())
            instructions = "-no instructions-";

        String unit = getTextValue(exerciseElement, "unit");
            if (unit == null || unit.trim().isEmpty())
                throw new IllegalArgumentException("An exercise needs a unit.");

        List<ExerciseCategory> categories = new ArrayList<>();

        NodeList nl = exerciseElement.getElementsByTagName("category");
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0 ; i < nl.getLength();i++) {
                Element element = (Element)nl.item(i);
                ExerciseCategory category = getCategory(element);
                categories.add(category);
            }
        }
        return new Exercise(name, description, instructions, unit, categories);
    }

    /**
     * Take an XML element and a tag name, look for the tag and get the text content.
     * @param element An element that contains a tagName element.
     * @param tagName A String that represents the element to get the text value from.
     * @return The String content in the "tagName"-element.
     */
    private String getTextValue(Element element, String tagName) {
        String textVal = null;
        NodeList nl = element.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element el = (Element)nl.item(0);
            if (el.getFirstChild() != null) {
                textVal = el.getFirstChild().getNodeValue();
            } else {
                textVal = "";
            }
        }
        return textVal;
    }

    private ExerciseCategory getCategory(Element categoryElement) {
        String s = categoryElement.getTextContent();
        ExerciseCategory category = ExerciseCategory.valueOf(s);
        return category;
    }
}
