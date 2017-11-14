package com.example.alexandersmith.todolist;

import java.util.Date;

/**
 * Created by alexandersmith on 10/11/2017.
 */

public class Task {

    private String name;
    private String description;
    private Date dueDate;
    private boolean completed;
    private int id;


    public Task(String name, String description, Date dueDate, boolean completed) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
    }

    public Task(String name, String description, Date dueDate, boolean completed, int id) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.completed = completed;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getId() {
        return id;
    }
}
