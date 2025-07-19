package com.airtribe.taskMaster.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class VerificationToken {

    private String token;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Users user;

    private Date expiryDate;

    public VerificationToken(String token, long id, Users user, Date expiryDate) {
        this.token = token;
        this.id = id;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public VerificationToken() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}
