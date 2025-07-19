package com.airtribe.taskMaster.entity;

import jakarta.persistence.*;

@Entity
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String attachment;

    @ManyToOne
    private Users user;

    @ManyToOne
    private Task task;

    public Attachment(long id, String attachment, Users user, Task task) {
        this.id = id;
        this.attachment = attachment;
        this.user = user;
        this.task = task;
    }

    public Attachment(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
