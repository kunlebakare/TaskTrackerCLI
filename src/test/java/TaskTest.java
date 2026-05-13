/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
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
    public void creatingATaskObjectWorks() {
        Task task = new Task("sample");
        assertInstanceOf(Task.class, task);
    }

    @Test
    public void newTaskObjectHasDescription() {
        Task task = new Task("sample");
        assertFalse(task.getDescription().isBlank(), "Task description must not be blank");

    }

    @Test
    public void newTaskObjectHasStatusAstodo() {
        Task task = new Task("sample");
        assertEquals(Task.Status.TODO, task.getStatus(), "New task status must begin as TODO");
    }

    @Test
    public void newTaskObjectHasCorrectCreatedDate() {
        Task task = new Task("sample");
        LocalDate now = LocalDate.now();
        assertTrue(now.isEqual(task.getCreatedDate()), "The date of task creation is not correct");

    }

     @Test
    public void newTaskObjectHasCorrectCreatedTime() {
        Task task = new Task("sample");
        LocalTime now = LocalTime.now();
        assertTrue(now.getHour() == task.getCreatedTime().getHour());
        assertTrue(now.getMinute() == task.getCreatedTime().getMinute());
        
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
        task.updateStatus("in-progress");
        LocalTime newTime = task.getUpdatedTime();

        assertNotEquals(oldTime, newTime, "The time did not update!");
    }

    @Test
    public void newTaskHasId(){
        Task task = new Task("sample");
        assertEquals(1, task.getId());
    }

}
