/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kunlebakare.github.FileManager;
import com.kunlebakare.github.Task;

/**
 *
 * @author Kunle Bakare
 */
public class FileManagerTest {

    private Path filePath = Path.of("tasks.json");

    public FileManagerTest() {

    }

    @BeforeEach
    public void initializeFile() {
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void writeToFileTest() {
        String jsonLine = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        FileManager.writeToFile(jsonLine);
        String expectedString = "{\"Tasks\":[" + jsonLine + "]}";

        try {
            String lines = Files.readString(filePath);
            assertEquals(expectedString, lines);
        } catch (NoSuchFileException e) {
            System.out.println(e.toString());
            throw new Error("The File does not exist! ");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void writeMoreThanOneToFile() {
        String jsonLine = "{\"id\":1,\"description\":\"eat\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String jsonLine2 = "{\"id\":2,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String jsonLine3 = "{\"id\":3,\"description\":\"code\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";

        FileManager.writeToFile(jsonLine);
        FileManager.writeToFile(jsonLine2);
        FileManager.writeToFile(jsonLine3);

        String expectedString = "{\"Tasks\":[" + jsonLine + "," + jsonLine2 + "," + jsonLine3 + "]}";

        try {
            String lines = Files.readString(filePath);
            assertEquals(expectedString, lines);
        } catch (NoSuchFileException e) {
            System.out.println(e.toString());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void writeAllToFileTest() {
        List<String> tasks = new ArrayList<>();

        String task1 = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task2 = "{\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task3 = "{\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task4 = "{\"id\":4,\"description\":\"Feed the cat\",\"status\":\"in-progress\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task5 = "{\"id\":5,\"description\":\"Take bingo for a walk\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task6 = "{\"id\":6,\"description\":\"Watch Friends\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task7 = "{\"id\":7,\"description\":\"Go to the gym\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task8 = "{\"id\":8,\"description\":\"Go to the movies\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task9 = "{\"id\":9,\"description\":\"complete home assignment\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task10 = "{\"id\":10,\"description\":\"sleep\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        tasks.add(task5);
        tasks.add(task6);
        tasks.add(task7);
        tasks.add(task8);
        tasks.add(task9);
        tasks.add(task10);

        FileManager.writeAllToFile(tasks);

        String expected = "{\"Tasks\":[" + task1 + "," + task2 + "," + task3 + "," + task4 + "," + task5 + "," + task6 + "," + task7 + "," + task8 + "," + task9 + "," + task10 + "]}";

        try {
            String actual = Files.readString(filePath);
            assertEquals(expected, actual);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void readAllFromFileTest() {
        List<String> tasks = new ArrayList<>();

        String task1 = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task2 = "{\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task3 = "{\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        FileManager.writeAllToFile(tasks);

        String[] expected = {"\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"",
            "\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"",
            "\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\""};

        List<String> actual = FileManager.readAllFromFile();
        assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void readAllAsTaskTest() {
        List<String> tasks = new ArrayList<>();
        LocalDateTime timehere = LocalDateTime.now();

        String task1 = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\""+timehere+"\",\"updatedAt\":\""+timehere+"\"}";
        String task2 = "{\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\""+timehere+"\",\"updatedAt\":\""+timehere+"\"}";
        String task3 = "{\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\""+timehere+"\",\"updatedAt\":\""+timehere+"\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        FileManager.writeAllToFile(tasks);

        List<Task> readTasks = FileManager.readAllAsTask();
        String regex = "\\d+\\s[^\"]+\\s[a-z]+\\s[\\s\\S]+\\s[\\s\\S]+";

        Pattern pattern = Pattern.compile(regex);
        for (Task t : readTasks) {
            Matcher matcher = pattern.matcher(t.toString());
            assertTrue(matcher.find());
        }
    }

    @Test
    public void writeAllTasksToFileTest() {
        Task t1 = new Task("desc");
        Task t2 = new Task("new", 2);
        Task t3 = new Task("fun", 3);

        List<Task> listOfTasks = new ArrayList<>();
        listOfTasks.add(t1);
        listOfTasks.add(t2);
        listOfTasks.add(t3);

        FileManager.writeAllTasksToFile(listOfTasks);

        try {
            String l = Files.readString(filePath);
            String[] lines = l.split(":\\[");

            System.out.println(lines[0]);
            System.out.println(lines[1]);

            String regex = "\"Tasks\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(lines[0]);
            assertTrue(matcher.find());

            String regex2 = "\\{\"id\":\\d+,\"description\":\"[^\"]+\",\"status\":\"[a-z]+\",\"createdAt\":\"[^\"]+\",\"updatedAt\":\"[^\"]+\"\\}";
            Pattern pattern2 = Pattern.compile(regex2);
            Matcher matcher2 = pattern2.matcher(lines[1]);

            for (int i = 0; i < 3; i++) {
                assertTrue(matcher2.find());
            }

        } catch (IOException e) {
            System.out.println(e.toString());

        }

    }
}
