/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        JsonHandler jh = new JsonHandler();
        Task task1 = new Task("go to the market");
        String convertedTask = jh.convertTaskToJson(task1);
        LocalDateTime createdNow = LocalDateTime.now();
        String expected = "{\"id\":1,\"description\":\"go to the market\",\"status\":\"TODO\",\"createdAt\":\""+createdNow+"\",\"updatedAt\":\""+createdNow+"\"}";

        assertEquals(expected, convertedTask);
    }

    @Test
    public void convertJsonToTaskTest(){
        JsonHandler jh = new JsonHandler();
        LocalDateTime createdNow = LocalDateTime.now();
        String jsonLine = "\"id\":1,\"description\":\"go to the market\",\"status\":\"TODO\",\"createdAt\":\""+createdNow+"\",\"updatedAt\":\""+createdNow+"\"";
       
        Task task = jh.convertJsonToTask(jsonLine);
        assertEquals(1, task.getId());
        assertEquals("go to the market", task.getDescription());
        assertEquals("TODO", task.getStatus().toString());
        assertEquals(createdNow.toString(), task.getCreatedWhen().toString());
        assertEquals(createdNow.toString(), task.getUpdatedWhen().toString());
    }

}
