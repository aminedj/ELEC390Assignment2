package com.example.elec390assignment2.models;

public class Assignment {
    private int id;
    private String title;
    private int grade;
    private  int courseId;

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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Assignment(String title, int grade, int courseId) {
        this.title = title;
        this.grade = grade;
        this.courseId = courseId;
    }

    public Assignment() {
    }

    public Assignment(int id, String title, int grade, int courseId) {
        this.id = id;
        this.title = title;
        this.grade = grade;
        this.courseId = courseId;
    }
}
