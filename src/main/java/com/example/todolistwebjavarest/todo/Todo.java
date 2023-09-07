package com.example.todolistwebjavarest.todo;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "todo")
public class Todo {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "txt")
    private String text;

    @Column(name = "startdate")
    private Date startdate;

    @Column(name = "enddate")
    private Date enddate;

    @Column(name = "user_id")
    private int userId;

    public Todo(Todo originalTodo){
        text = originalTodo.getText();
        startdate = originalTodo.getStartdate();
        enddate= originalTodo.getEnddate();
        userId = originalTodo.getUserId();
    }

    public Todo() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
