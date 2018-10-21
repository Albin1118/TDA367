package com.example.ziggy.trainingtracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadLinesFromFileService {
    InputStream inputStream;
    String charsetName;

    /**
     * Creates an input stream from the specified file path.
     * @param path The file path
     * @param charsetName Charset name (e.g. "UTF-8")
     */
    public ReadLinesFromFileService(String path, String charsetName) {
        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        this.charsetName = charsetName;
    }

    /**
     * Reads the file and returns a list of all the lines.
     * @return A list containing a String of each line
     */
    public List<String> readLines() {
        List<String> lines = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName))
        ){
            String line;
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * Reads the file and returns a list for each line containing all the Strings separated by ";".
     * @return A list of lists containing a String for each token in the line
     */
    public List<List<String>> readTokenLines() {
        List<List<String>> tokenLines = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charsetName))
        ){
            String line;
            while((line = reader.readLine()) != null) {
                tokenLines.add(Arrays.asList(line.split("\\s*;\\s*")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenLines;
    }
}
