package com.example.todolistwebjavarest.session;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @Column(name="id")
    private UUID id;

    @Column(name= "user_id")
    private int userId;

    @Column(name = "connecttime")
    private Timestamp connectTime;

    protected Session(int userId){
        this.userId = userId;
        this.id = UUID.randomUUID();
        this.connectTime = new Timestamp(System.currentTimeMillis());
    }

    public Session() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
