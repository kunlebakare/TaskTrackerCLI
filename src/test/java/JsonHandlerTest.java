/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.kunlebakare.github.JsonHandler;
import com.kunlebakare.github.Task;

/**
 *
 * @author Kunle Bakare
 */
public class JsonHandlerTest {

    public JsonHandlerTest() {
    }

    @Test
    public void convertTaskToJsonTest() {
        Task task1 = new Task("go to the market");
        String convertedTask = JsonHandler.convertTaskToJson(task1);

        System.out.println(convertedTask);
        String regex = "\\{\"id\":\\d+,\"description\":\"[^\"]+\",\"status\":\"[a-z]+\",\"createdAt\":\"[\\s\\S]+\",\"updatedAt\":\"[\\s\\S]+\"\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(convertedTask);

        assertTrue(matcher.find());
    }

    @Test
    public void convertJsonToTaskTest() {
        LocalDateTime createdNow = LocalDateTime.now();
        String jsonLine = "\"id\":1,\"description\":\"go to the market\",\"status\":\"TODO\",\"createdAt\":\"" + createdNow + "\",\"updatedAt\":\"" + createdNow + "\"";

        Task task = JsonHandler.convertJsonToTask(jsonLine);
        assertEquals(1, task.getId());
        assertEquals("go to the market", task.getDescription());
        assertEquals("todo", task.getStatus());
        assertEquals(createdNow.toString(), task.getCreatedWhen().toString());
        assertEquals(createdNow.toString(), task.getUpdatedWhen().toString());
    }

    @Test
    public void convertAllToTaskTest() {
        LocalDateTime createdNow = LocalDateTime.now();
        String jsonLine1 = "\"id\":1,\"description\":\"shop\",\"status\":\"TODO\",\"createdAt\":\"" + createdNow + "\",\"updatedAt\":\"" + createdNow + "\"";
        String jsonLine2 = "\"id\":2,\"description\":\"dance\",\"status\":\"INPROGRESS\",\"createdAt\":\"" + createdNow + "\",\"updatedAt\":\"" + createdNow + "\"";
        String jsonLine3 = "\"id\":3,\"description\":\"code\",\"status\":\"DONE\",\"createdAt\":\"" + createdNow + "\",\"updatedAt\":\"" + createdNow + "\"";

        List<String> lines = new ArrayList<>();
        lines.add(jsonLine1);
        lines.add(jsonLine2);
        lines.add(jsonLine3);

        List<Task> tasks = JsonHandler.convertAllToTask(lines);

        String regex1 = "1\\sshop\\stodo\\s[\\s\\S]+\\s[\\s\\S]+";
        String regex2 = "2\\sdance\\sin-progress\\s[\\s\\S]+\\s[\\s\\S]+";
        String regex3 = "3\\scode\\sdone\\s[\\s\\S]+\\s[\\s\\S]+";

        Pattern pattern1 = Pattern.compile(regex1);
        Matcher matcher1 = pattern1.matcher(tasks.get(0).toString());

        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(tasks.get(1).toString());

        Pattern pattern3 = Pattern.compile(regex3);
        Matcher matcher3 = pattern3.matcher(tasks.get(2).toString());

        assertTrue(matcher1.find());
        assertTrue(matcher2.find());
        assertTrue(matcher3.find());
    }

    @Test
    public void convertAlltoJsonTest(){
        Task task1 = new Task("eat");
        Task task2 = new Task("sleep");
        Task task3 = new Task("code");

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);

        List<String> lines = JsonHandler.convertAlltoJson(tasks);

        String regex = "\\{\"id\":\\d+,\"description\":\"[a-z]+\",\"status\":\"[a-z]+\",\"createdAt\":\"[\\s\\S]+\",\"updatedAt\":\"[\\s\\S]+\"\\}";

        Pattern pattern = Pattern.compile(regex);
        
        lines.stream()
        .forEach(line ->{
            Matcher matcher = pattern.matcher(line);
            assertTrue(matcher.find());
        } );
        
    }

}
