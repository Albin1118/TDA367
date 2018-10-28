package com.example.ziggy.trainingtracker.service;

import com.example.ziggy.trainingtracker.model.IWorkout;
import com.example.ziggy.trainingtracker.model.IWorkoutBlock;
import com.example.ziggy.trainingtracker.model.Workout;
import com.example.ziggy.trainingtracker.model.WorkoutBlock;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IWorkoutDeserializer extends JsonDeserializer<IWorkout> {

    @Override
    public IWorkout deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        System.out.println("--------------------------------Inside Deserialize----------------------------------------");
        IWorkout iWorkout = new Workout();

        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);

        JsonNode blockNode = node.get("blocks");
        JsonNode amountNode = blockNode.get("amounts");
        JsonNode exerciseNode = blockNode.get("exercises");

        String workoutName = node.get("name").toString();
        System.out.println(workoutName);
        String workoutDescription = node.get("description").toString();
        System.out.println(workoutDescription);

        for (JsonNode s : blockNode.get("amounts")){
            System.out.println(s.toString());
        }


        iWorkout.setName(workoutName);
        iWorkout.setDescription(workoutDescription);

        //Iterator<JsonNode> workoutIterator = node.elements();

        /*
        for (JsonNode blockContent : node) {

            System.out.println(node.get("name"));
            System.out.println(node.get("description"));
            System.out.println(node.get("numberofblocks"));
        }
        */

        /*
        while (workoutIterator.hasNext()) {
            JsonNode slaidNode = workoutIterator.next();
            System.out.println(slaidNode.get("amounts"));
            System.out.println(slaidNode.get("exercises"));
            System.out.println(slaidNode.get("name"));
        }

        */

        return iWorkout;

        }
    }

