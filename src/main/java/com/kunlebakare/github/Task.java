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

    public enum Status {
        TODO, INPROGRESS, DONE
    }

    public Task(String description) {
        this.description = description;
        this.taskStatus = Status.TODO;
        this.createdWhen = LocalDateTime.now();
    }

    public ChronoLocalDate getCreatedDate() {
        return createdWhen.toLocalDate();
    }

    public LocalTime getCreatedTime() {
        return createdWhen.toLocalTime();
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
    }

    public void updateStatus(String newStatus) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
