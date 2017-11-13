package com.example.alexandersmith.todolist;

import java.util.Date;

/**
 * Created by alexandersmith on 10/11/2017.
 */

public class Task {

    private String name;
    private String description;
    private Date dueDate;
    private int id;


    public Task(String name, String description, Date dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    public Task(String name, String description, Date dueDate, int id) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
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

    public int getId() {
        return id;
    }
}
