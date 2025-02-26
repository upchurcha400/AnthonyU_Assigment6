package com.coderscampus.Assignment6;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {

    // Creating a list of TeslaSalesData objects from the data read from the CSV file
    public List<TeslaSalesData> readTeslaSalesData(String filename) {
        try (Stream<String> lines = Files.lines(Paths.get(filename))) {
            return lines.skip(1)
                    .map(line -> parseTeslaSalesData(line, filename))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("There was an error reading the file: " + filename + " due to " + e.getMessage());
            return Collections.emptyList();
        }
    }

    //The will parse the single line from CSV and create the TeslaSalesData object.
    private TeslaSalesData parseTeslaSalesData(String line, String filename) {
        String data = Arrays.toString(line.split(","));
        String date = Arrays.toString(data.split("-"));
        int sales = Integer.parseInt(data);
        String model = determineModel(filename);
        return new TeslaSalesData(date, sales, model);
    }

    //Determining the model based on the filename
    private String determineModel(String filename) {
        if (filename.contains("model3")) {
            return "Model 3";
        } else if (filename.contains("modelS")) {
            return "Model S";
        } else if (filename.contains("modelX")) {
            return "Model X";
        } else {
            return "Unknown Model was found";
        }
    }
}

