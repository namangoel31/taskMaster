package com.airtribe.taskMaster.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private ArrayList<Users> users;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
