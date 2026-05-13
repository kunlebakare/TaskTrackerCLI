/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kunlebakare.github.FileManager;

/**
 *
 * @author Kunle Bakare
 */
public class FileManagerTest {

    private Path filePath;

    public FileManagerTest() {
        this.filePath = Path.of("tasks.json");
    }

    @BeforeEach
    public void deleteFile() {
        if (Files.exists(filePath)) {
            try {
                Files.delete(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void writeToFileTest() {
        FileManager fm = new FileManager();
        String jsonLine = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        fm.writeToFile(jsonLine);
        String expectedString = "{\"Tasks\":[" + jsonLine + "]}";

        try {
            String lines = Files.readString(filePath);
            assertEquals(expectedString, lines);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeAllToFileTest() {
        FileManager fm = new FileManager();
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

        fm.writeAllToFile(tasks);

        String expected = "{\"Tasks\":[" + task1 + "," + task2 + "," + task3 + "," + task4 + "," + task5 + "," + task6 + "," + task7 + "," + task8 + "," + task9 + "," + task10 + "]}";

        try {
            String actual = Files.readString(filePath);
            assertEquals(expected, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readAllFromFileTest() {
        FileManager fm = new FileManager();
        List<String> tasks = new ArrayList<>();

        String task1 = "{\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task2 = "{\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";
        String task3 = "{\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"}";

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        fm.writeAllToFile(tasks);

        String[] expected = {"\"id\":1,\"description\":\"Website Redesign\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"",
            "\"id\":2,\"description\":\"Website Copy\",\"status\":\"done\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\"",
            "\"id\":3,\"description\":\"Go to the market\",\"status\":\"todo\",\"createdAt\":\"timehere\",\"updatedAt\":\"timehere\""};

        List<String> actual = fm.readAllFromFile();
        assertArrayEquals(expected, actual.toArray());
    }

}
