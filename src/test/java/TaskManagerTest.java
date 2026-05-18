/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kunlebakare.github.FileManager;
import com.kunlebakare.github.Task;
import com.kunlebakare.github.TaskManager;

/**
 *
 * @author Kunle Bakare
 */
public class TaskManagerTest {

    public Path filePath;

    public TaskManagerTest() {
        this.filePath = Path.of("tasks.json");
    }

    @BeforeEach
    public void initializeTestfile() {
        try {
            Files.deleteIfExists(filePath);
            Files.createFile(filePath);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void reorganizeTasksTest() {
        List<String> tasks = new ArrayList<>();
        LocalDateTime timehere = LocalDateTime.now();

        String task1 = "{\"id\":3,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task2 = "{\"id\":6,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task3 = "{\"id\":9,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        FileManager.writeAllToFile(tasks);
        TaskManager.reorganizeTasks();
        List<Task> rtasks = FileManager.readAllAsTask();

        assertEquals(1, rtasks.get(0).getId());
        assertEquals(2, rtasks.get(1).getId());
        assertEquals(3, rtasks.get(2).getId());

    }

    @Test
    public void uniqueIdGeneratorTest() {
        int id = TaskManager.uniqueIdGenerator();
        assertEquals(1, id);

        List<String> tasks = new ArrayList<>();
        LocalDateTime timehere = LocalDateTime.now();

        String task1 = "{\"id\":3,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task2 = "{\"id\":6,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task3 = "{\"id\":9,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        FileManager.writeAllToFile(tasks);

        id = TaskManager.uniqueIdGenerator();
        assertEquals(4, id);
    }

    @Test
    public void addNewTaskTest() {
        TaskManager.addNewTask("workout at the gym");
        try {
            String line = Files.readString(filePath);

            String regex = "\"([a-z]+)\":(d+|\"[^\"]+\")";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);

                if (value.startsWith("\"") && value.endsWith("\"")) {
                    value = value.substring(1, value.length() - 1);
                }
                switch (key) {
                    case "id" ->
                        assertEquals(1, value);
                    case "description" ->
                        assertEquals("workout at the gym", value);
                    case "status" ->
                        assertEquals("todo", value);

                }
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }

    @Test
    public void addingMultipleTasksWorks() {
        TaskManager.addNewTask("workout at the gym");
        TaskManager.addNewTask("eat ice-cream");
        TaskManager.addNewTask("take a shower");
        TaskManager.addNewTask("go shopping");

        try {
            String line = Files.readString(filePath).substring(11);
            String[] lines = line.split("},");

            String regex = "\"([^\"]+)\":(\"[^\"]+\"|\\d+)";
            Pattern pattern = Pattern.compile(regex);

            for (String l : lines) {
                int id = 0;
                Matcher matcher = pattern.matcher(l);
                while (matcher.find()) {
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }

                    switch (key) {
                        case "id" ->
                            id = Integer.parseInt(value);
                        case "description" -> {
                            switch (id) {
                                case 1 ->
                                    assertEquals("workout at the gym", value);
                                case 2 ->
                                    assertEquals("eat ice-cream", value);
                                case 3 ->
                                    assertEquals("take a shower", value);
                                case 4 ->
                                    assertEquals("go shopping", value);
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    @Test
    public void updateTaskTest() {
        TaskManager.addNewTask("going to the market");
        TaskManager.updateTask(1, "buy ice cream");

        try {
            String line = Files.readString(filePath);

            String regex = "\"(description)\":\"([^\"]+)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);

                if (key.equals("description")) {
                    assertNotEquals("going to the market", value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    @Test
    public void updateToInProgressTest() {
        TaskManager.addNewTask("going to the market");
        TaskManager.updateToInProgress(1);

        try {
            String line = Files.readString(filePath);

            String regex = "\"(status)\":\"([^\"]+)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);

                if (key.equals("status")) {
                    assertEquals("in-progress", value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void updateToDoneTest() {
        TaskManager.addNewTask("going to the market");
        TaskManager.updateToDone(1);

        try {
            String line = Files.readString(filePath);

            String regex = "\"(status)\":\"([^\"]+)\"";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2);

                if (key.equals("status")) {
                    assertEquals("done", value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void deleteTaskTest() {
        List<String> tasks = new ArrayList<>();
        LocalDateTime timehere = LocalDateTime.now();

        String task1 = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task2 = "{\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";
        String task3 = "{\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"" + timehere + "\",\"updatedAt\":\"" + timehere + "\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        FileManager.writeAllToFile(tasks);

        TaskManager.deleteTask(2);

        try {
            String line = Files.readString(filePath).substring(11);
            String[] lines = line.split("},");

            String regex = "\"([^\"]+)\":(\"[^\"]+\"|\\d+)";
            Pattern pattern = Pattern.compile(regex);

            for (String l : lines) {
                int id = 0;
                Matcher matcher = pattern.matcher(l);
                while (matcher.find()) {
                    String key = matcher.group(1);
                    String value = matcher.group(2);

                    if (value.startsWith("\"") && value.endsWith("\"")) {
                        value = value.substring(1, value.length() - 1);
                    }

                    switch (key) {
                        case "id" ->
                            id = Integer.parseInt(value);
                        case "description" -> {
                            switch (id) {
                                case 1 ->
                                    assertEquals("Website Redesign", value);
                                case 2 ->
                                    assertEquals("Go to the market", value);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
