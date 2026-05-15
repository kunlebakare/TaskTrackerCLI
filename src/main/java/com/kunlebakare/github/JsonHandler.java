/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonHandler {

    public JsonHandler() {

    }

    public String convertTaskToJson(Task task) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(task.getId()).append(",");
        sb.append("\"description\":").append("\"").append(task.getDescription()).append("\"").append(",");
        sb.append("\"status\":").append("\"").append(task.getStatus()).append("\"").append(",");
        sb.append("\"createdAt\":").append("\"").append(task.getCreatedWhen()).append("\"").append(",");
        sb.append("\"updatedAt\":").append("\"").append(task.getUpdatedWhen()).append("\"");
        sb.append("}");
        return sb.toString();
    }

    public Task convertJsonToTask(String json) {
        //"id":1,"description":"Website Redesign","status":"todo","createdAt":"timehere","updatedAt":"timehere"
        String regex = "\"([^\"]+)\":(\"[^\"]+\"|\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        Task task = new Task();

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            // Clean up the quotes around string values
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            switch (key) {
                case "id" ->
                    task.setId(Integer.parseInt(value));
                case "description" ->
                    task.updateDescription(value);
                case "status" ->
                    task.updateStatus(value);
                case "createdAt" ->
                    task.setCreatedWhen(value);
                case "updatedAt" ->
                    task.setUpdatedWhen(value);
            }

        }
        return task;
    }

}
