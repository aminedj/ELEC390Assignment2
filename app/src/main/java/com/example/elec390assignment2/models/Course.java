package com.example.elec390assignment2.models;

public class Course {
    private int id;
    private String title;

    public Course(int id, String title, String code) {
        this.id = id;
        this.title = title;
        this.code = code;
    }

    public Course() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Course(String title, String code) {
        this.title = title;
        this.code = code;
    }

    private String code;
}
