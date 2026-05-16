/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Kunle Bakare
 */
public class Task {

    private String description;
    private Status taskStatus;
    private LocalDateTime createdWhen;
    private LocalDateTime updatedWhen;
    private int uniqueId;

    public enum Status {
        TODO, INPROGRESS, DONE
    }

    public Task(String description) {
        this.uniqueId = 1;
        this.description = description;
        this.taskStatus = Status.TODO;
        this.createdWhen = LocalDateTime.now();
        this.updatedWhen = this.createdWhen;
    }

    public Task(String description, int id) {
        this(description);
        this.uniqueId = id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fCreatedWhen = this.createdWhen.format(formatter);
        String fUpdatedWhen = this.updatedWhen.format(formatter);

        return this.uniqueId+" "+this.description+" "+this.taskStatus.toString().toLowerCase()+" "+fCreatedWhen+" "+fUpdatedWhen;
    }

    //***********************************************setters*************
    public void setId(int id) {
        this.uniqueId = id;
    }

    public void updateDescription(String newDescription) {
        if (!newDescription.isBlank()) {
            this.description = newDescription;
            this.updatedWhen = LocalDateTime.now();
        }
    }

    public void updateStatus(String newStatus) {
        if (this.getStatus().equals(newStatus)) {
            return;
        }
        switch (newStatus) {
            case "TODO" ->
                this.taskStatus = Status.TODO;
            case "INPROGRESS" ->
                this.taskStatus = Status.INPROGRESS;
            case "DONE" ->
                this.taskStatus = Status.DONE;

        }
        this.updatedWhen = LocalDateTime.now();
    }

    public void setCreatedWhen(String newcreatedWhen) {
        this.createdWhen = LocalDateTime.parse(newcreatedWhen);
    }

    public void setUpdatedWhen(String newupdatedWhen) {
        this.updatedWhen = LocalDateTime.parse(newupdatedWhen);
    }

    //*********************************GETTERS******************** */
    public int getId() {
        return this.uniqueId;
    }

    public LocalDateTime getCreatedWhen() {
        return this.createdWhen;
    }

    public LocalDateTime getUpdatedWhen() {
        return this.updatedWhen;
    }

    public LocalDate getCreatedDate() {
        return createdWhen.toLocalDate();
    }

    public LocalTime getCreatedTime() {
        return createdWhen.toLocalTime();
    }

    public LocalDate getUpdatedDate() {
        return updatedWhen.toLocalDate();
    }

    public LocalTime getUpdatedTime() {
        return updatedWhen.toLocalTime();
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatus() {
        if (this.taskStatus == Status.INPROGRESS){
            return "in-progress";
        }
        return this.taskStatus.toString().toLowerCase();
    }

}
