/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Kunle Bakare
 */
public class FileManager {

    //handles the json file including creating, reading, writing
    private static Path filePath = Path.of("tasks.json");

    public FileManager() {
        try {
            Files.createFile(filePath);
        } catch (FileAlreadyExistsException e) {
            e.getStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeToFile(String jsonLine) {
        //expects line to be wrapped in {}
        //expects the file to already exist
        try {
            if (Files.size(filePath) == 0 || Files.readString(filePath).isBlank()) { //if the file is empty
                Files.writeString(filePath, "{\"Tasks\":[" + jsonLine + "]}");
            } else {
                String jsonLines = Files.readString(filePath).trim();
                String openjsonLines = jsonLines.substring(0, jsonLines.length() - 2); //remove ]}
                String closedjsonLines = openjsonLines + "," + jsonLine + "]}";
                Files.writeString(filePath, closedjsonLines, TRUNCATE_EXISTING);
            }

        } catch (NoSuchFileException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void writeAllToFile(List<String> lines) {
        lines.stream()
                .forEach(line -> writeToFile(line));
    }

    public static List<String> readAllFromFile() {
        try {
            String lines = Files.readString(filePath);
            String newLines = lines.substring(11, lines.length() - 3);
            String[] tasks = newLines.split("\\},\\{");
            //Files.delete(filePath);
            return Arrays.asList(tasks);
        } catch (NoSuchFileException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public static boolean fileIsEmpty() {
        try {
            if (Files.readString(filePath).isBlank()) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static List<Task> readAllAsTask() {
        return JsonHandler.convertAllToTask(readAllFromFile());
    }

    public static void writeAllTasksToFile(List<Task> allTasks) {

        writeAllToFile(JsonHandler.convertAlltoJson(allTasks));
    }
}
