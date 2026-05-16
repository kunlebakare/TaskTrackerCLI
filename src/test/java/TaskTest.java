/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.kunlebakare.github.Task;

/**
 *
 * @author Kunle Bakare
 */
public class TaskTest {

    public TaskTest() {
    }


    @Test
    public void toStringTest(){
        Task task = new Task("description");
        String regex = "\\d+\\s[a-z]+\\s[a-z]+\\s[\\s\\S]+\\s[\\s\\S]+\\s";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(task.toString());

        assertTrue(matcher.find());
    }

    @Test
    public void setIdTest(){
        Task task = new Task("sample");
        //id of new task is 1 by default
        task.setId(2);
        assertNotEquals(1, task.getId());
    }

    @Test
    public void updateDescriptionTest() {
        Task task = new Task("sample");
        task.updateDescription("newDescription");
        assertEquals("newDescription", task.getDescription());
    }

     @Test
    public void updateStatusTest() {
        Task task = new Task("sample");
        task.updateStatus("DONE");
        assertNotEquals("TODO", task.getStatus());
    }

    @Test
    public void setCreatedWhenTest(){
        Task task = new Task("description");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        String createdWhen = LocalDateTime.now().toString();
        task.setCreatedWhen(createdWhen);
        assertEquals(createdWhen, task.getCreatedWhen().toString());
    }

    @Test
    public void setUpdatedWhenTest(){
        Task task = new Task("desc");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        String updatedWhen = LocalDateTime.now().toString();
        task.setUpdatedWhen(updatedWhen);
        assertEquals(updatedWhen, task.getUpdatedWhen().toString());
    }

    @Test
    public void updatingDescriptionOfTaskOnlyWorksIfDescriptionIsValid(){
        Task task = new Task("go to the market");
        task.updateDescription(" ");
        assertTrue(task.getDescription().equals("go to the market"), "Make sure the description does not update with empty text!");
    }

    @Test
    public void changingDescriptionOfTaskChangesUpdatedAtTime() {
        Task task = new Task("go to the market");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        task.updateDescription("buy vegetables");

        if (task.getCreatedDate().equals(task.getUpdatedDate())){
            assertFalse(task.getCreatedTime().equals(task.getUpdatedTime()), "Updated time does not reflect time when task description was updated!");
        } else if (task.getCreatedTime().equals(task.getUpdatedTime())){
            assertFalse(task.getCreatedDate().equals(task.getUpdatedDate()), "Updated time does not reflect time when task description was updated!");
        }
    }

    @Test
    public void changingStatusOfTaskChangesUpdatedAtTime() {
        Task task = new Task("sample");
        LocalTime oldTime = task.getUpdatedTime();

        try{
            Thread.sleep(2000);
        } catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        task.updateStatus("INPROGRESS");
        LocalTime newTime = task.getUpdatedTime();

        assertNotEquals(oldTime, newTime, "The time did not update!");
    }
}
