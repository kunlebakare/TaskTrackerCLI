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
import java.util.stream.Collectors;

public class TaskManager {
    //connects everything together

    public TaskManager() {
    }

    public static boolean idisInvalid(String id, int tasksSize) {
        return id.isBlank() || Integer.parseInt(id) > tasksSize;
    }

    public static boolean descIsInvalid(String description) {
        return description.isBlank();
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
        if (descIsInvalid(description)) {
            System.out.println("You did not give a description for your task");
            return;
        }
        Task task = new Task(description, uniqueIdGenerator());
        String taskJson = JsonHandler.convertTaskToJson(task);
        FileManager.writeToFile(taskJson);
        System.out.println("Task added successfully (ID: " + task.getId() + ")");
    }

    public static void updateTask(String id, String description) {
        List<Task> tasks = FileManager.readAllAsTask();
        if (idisInvalid(id, tasks.size())) {
            System.out.println("Invalid id");
            return;
        }
        int nid = Integer.parseInt(id);
        if (descIsInvalid(id)) {
            System.out.println("You did not give any description");
            return;
        }

        tasks.get(nid - 1).updateDescription(description);
        System.out.println("Task updated successfully");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void updateToInProgress(String id) {
        List<Task> tasks = FileManager.readAllAsTask();
        if (idisInvalid(id, tasks.size())) {
            System.out.println("Invalid id");
            return;
        }
        int nid = Integer.parseInt(id);
        tasks.get(nid - 1).updateStatus("in-progress");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void updateToDone(String id) {
        List<Task> tasks = FileManager.readAllAsTask();
        if (idisInvalid(id, tasks.size())) {
            System.out.println("Invalid id");
            return;
        }
        int nid = Integer.parseInt(id);
        tasks.get(nid - 1).updateStatus("done");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
    }

    public static void deleteTask(String id) {
        List<Task> tasks = FileManager.readAllAsTask();
        if (idisInvalid(id, tasks.size())) {
            System.out.println("Invalid id");
            return;
        }
        int nid = Integer.parseInt(id);
        tasks.remove(nid - 1);
        System.out.println("Task deleted successfully");
        FileManager.reinitializeFile();
        FileManager.writeAllTasksToFile(tasks);
        if (!FileManager.fileIsEmpty()) {
            reorganizeTasks();
        }

    }

    public static void listAllTasks() {
        if (!FileManager.fileIsEmpty()) {
            String format = "%-5s %-25s %-15s %-22s %-22s%n";
            System.out.printf(format, "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
            FileManager.readAllAsTask().stream()
                    .forEach(System.out::println);
        } else {
            System.out.println("There are no tasks");
        }

    }

    public static void listAllDoneTasks() {
        List<Task> doneTasks = FileManager.readAllAsTask().stream()
                .filter(task -> task.getStatus().equals("done"))
                .collect(Collectors.toList());
        if (doneTasks.isEmpty()) {
            System.out.println("There are no done tasks");
        } else {
            String format = "%-5s %-25s %-15s %-22s %-22s%n";
            System.out.printf(format, "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
            doneTasks.forEach(System.out::println);
        }
    }

    public static void listAllTasksNotDone() {
        String format = "%-5s %-25s %-15s %-22s %-22s%n";
        System.out.printf(format, "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
        FileManager.readAllAsTask().stream()
                .forEach(task -> {
                    if (!task.getStatus().equals("done")) {
                        System.out.println(task);
                    }
                });
    }

    public static void listAllTasksInProgress() {
        List<Task> inTasks = FileManager.readAllAsTask().stream()
                .filter(task -> task.getStatus().equals("in-progress"))
                .collect(Collectors.toList());
        if (inTasks.isEmpty()) {
            System.out.println("There are no in-progress tasks");
        } else {
            String format = "%-5s %-25s %-15s %-22s %-22s%n";
            System.out.printf(format, "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
            inTasks.forEach(System.out::println);
        }

    }

    public static void listAllTodoTasks() {
        List<Task> todoTasks = FileManager.readAllAsTask().stream()
                .filter(task -> task.getStatus().equals("todo"))
                .collect(Collectors.toList());
        if (todoTasks.isEmpty()) {
            System.out.println("There are no todo tasks");
        } else {
            String format = "%-5s %-25s %-15s %-22s %-22s%n";
            System.out.printf(format, "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
            todoTasks.forEach(System.out::println);
        }
    }

}
