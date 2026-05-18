/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

/**
 *
 * @author Kunle Bakare
 */
import java.util.List;

public class TaskManager {
    //connects everything together

    public TaskManager() {
    }

    public static void reorganizeTasks() {
        List<Task> tasks = FileManager.readAllAsTask();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() != i + 1) {
                tasks.get(i).setId(i + 1);
            }
        }
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static int uniqueIdGenerator() {
        if (FileManager.fileIsEmpty()) {
            return 1;
        }
        reorganizeTasks();
        return FileManager.readAllAsTask().size() + 1;
    }

    public static void addNewTask(String description) {
        Task task = new Task(description, uniqueIdGenerator());
        String taskJson = JsonHandler.convertTaskToJson(task);
        FileManager.writeToFile(taskJson);
    }

    public static void updateTask(int id, String description) {
        List<Task> tasks = FileManager.readAllAsTask();
        tasks.get(id-1).updateDescription(description);
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void updateToInProgress(int id) {
        List<Task> tasks = FileManager.readAllAsTask();
        tasks.get(id-1).updateStatus("INPROGRESS");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void updateToDone(int id) {
        List<Task> tasks = FileManager.readAllAsTask();
        tasks.get(id-1).updateStatus("DONE");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void deleteTask(int id) {
        List<Task> tasks = FileManager.readAllAsTask();
        tasks.remove(id-1);
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
        reorganizeTasks();
    }

    public static void listAllTasks() {
        FileManager.readAllAsTask().stream()
                .forEach(System.out::println);
    }

    public static void listAllDoneTasks() {
        FileManager.readAllAsTask().stream()
                .forEach(task -> {
                    if (task.getStatus().equals("done")) {
                        System.out.println(task);
                    }
                });
    }

    public static void listAllTasksNotDone() {
        FileManager.readAllAsTask().stream()
                .forEach(task -> {
                    if (!task.getStatus().equals("done")) {
                        System.out.println(task);
                    }
                });
    }

    public static void listAllTasksInProgress() {
        FileManager.readAllAsTask().stream()
                .forEach(task -> {
                    if (task.getStatus().equals("in-progress")) {
                        System.out.println(task);
                    }
                });
    }

    public static void listAllTodoTasks() {
        FileManager.readAllAsTask().stream()
                .forEach(task -> {
                    if (task.getStatus().equals("todo")) {
                        System.out.println(task);
                    }
                });
    }

}
