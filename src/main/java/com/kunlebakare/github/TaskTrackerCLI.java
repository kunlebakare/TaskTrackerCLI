/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.kunlebakare.github;

/**
 *
 * @author Kunle Bakare
 */
public class TaskTrackerCLI {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a command.");
            return;
        }
        FileManager fm = new FileManager(); //to create the new file
        String arg1 = args[0].strip();
        String arg2 = "";
        String arg3 = "";

        if (args.length == 2) {
            arg2 = args[1].strip();
        }

        if (args.length >= 3){
            arg3 = args[2].strip();
        }

        switch (arg1) {
            case "add" ->
                TaskManager.addNewTask(arg2);
            case "update" ->
                TaskManager.updateTask(arg2, arg3);
            case "delete" ->
                TaskManager.deleteTask(arg2);
            case "mark-in-progress" ->
                TaskManager.updateToInProgress(arg2);
            case "mark-done" ->
                TaskManager.updateToDone(arg2);
            case "list" -> {
                String filter = (args.length > 1) ? arg2 : "all";
                switch (filter) {
                    case "done" ->
                        TaskManager.listAllDoneTasks();
                    case "todo" ->
                        TaskManager.listAllTodoTasks();
                    case "in-progress" ->
                        TaskManager.listAllTasksInProgress();
                    case "all" ->
                        TaskManager.listAllTasks();
                    default ->
                        System.out.println("Unknown command: " + "\"" + arg1 + " " + arg2 + "\"");
                }
            }
            default ->
                System.out.println("Unknown command: " + arg1);
        }
    }
}
