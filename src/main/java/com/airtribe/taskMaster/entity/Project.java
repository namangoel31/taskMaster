package com.airtribe.taskMaster.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private ArrayList<Users> users;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> task;

    public Project(long id, String name, ArrayList<Users> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Project() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }

    public List<Task> getTask() {
        return task;
    }

    public void setTask(List<Task> task) {
        this.task = task;
    }
}
