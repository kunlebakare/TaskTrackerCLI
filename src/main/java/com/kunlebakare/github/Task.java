/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kunlebakare.github;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
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
    private int uniqueId;
    private JsonHandler jsonHandler;

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

    public Task() {
        this("description");
    }

    //setters
    public void setId(int id) {
        this.uniqueId = id;
    }

    public void updateDescription(String newDescription) {
        if (!newDescription.isBlank()) {
            this.description = newDescription;
        }
        this.updatedWhen = LocalDateTime.now();
    }

    public void updateStatus(String newStatus) {
        switch (newStatus.toLowerCase()) {
            case "todo" ->
                this.taskStatus = Status.TODO;
            case "in-progress" ->
                this.taskStatus = Status.INPROGRESS;
            case "done" ->
                this.taskStatus = Status.DONE;

        }
        this.updatedWhen = LocalDateTime.now();
    }

    public void setCreatedWhen(String createdWhen){
        this.createdWhen = LocalDateTime.parse(createdWhen);
    }

    public void setUpdatedWhen(String updatedWhen){
        this.updatedWhen = LocalDateTime.parse(updatedWhen);
    }

    //Getters

    public int getId() {
        return this.uniqueId;
    }

    public ChronoLocalDateTime getCreatedWhen() {
        return this.createdWhen;
    }

    public ChronoLocalDateTime getUpdatedWhen() {
        return this.updatedWhen;
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

}
