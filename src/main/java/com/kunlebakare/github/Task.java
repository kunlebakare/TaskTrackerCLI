/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;

/**
 *
 * @author Kunle Bakare
 */
public class Task {

    private String description;
    private Status taskStatus;
    private ChronoLocalDateTime createdWhen;
    private ChronoLocalDateTime updatedWhen;
    private String uniqueId;

    public enum Status {
        TODO, INPROGRESS, DONE
    }

    public Task(String description) {
        this.description = description;
        this.taskStatus = Status.TODO;
        this.createdWhen = LocalDateTime.now();
        this.updatedWhen = this.createdWhen;
    }

    public Task(String description, String id){
        super();
        this.uniqueId = id;
    }

    public String getId(){
        return this.uniqueId;
    }

    public ChronoLocalDate getCreatedDate() {
        return createdWhen.toLocalDate();
    }

    public LocalTime getCreatedTime() {
        return createdWhen.toLocalTime();
    }

    public ChronoLocalDate getUpdatedDate() {
        return updatedWhen.toLocalDate();
    }

    public LocalTime getUpdatedTime() {
        return updatedWhen.toLocalTime();
    }

    public String getDescription() {
        return this.description;
    }

    public Status getStatus() {
        return this.taskStatus;
    }

    public void updateDescription(String newDescription) {
        if (!newDescription.isBlank()) {
            this.description = newDescription;
        }
        this.updatedWhen = LocalDateTime.now();
    }

    public void updateStatus(String newStatus) {
        switch(newStatus){
            case "todo" -> this.taskStatus = Status.TODO;
            case "in-progress" -> this.taskStatus = Status.INPROGRESS;
            case "done" -> this.taskStatus = Status.DONE;

        }
        this.updatedWhen = LocalDateTime.now();
    }

}
