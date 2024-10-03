package com.entity;

public class Homework {
    private int id;
    private int studentId;
    private String fullName;
    private String group;
    private String homeworkDate;
    private String email;
    private String phone;
    private String homeworkFile;
    private int teacherId;
    private String status;

    public Homework() {
    }

    public Homework(int studentId, String fullName, String group, String homeworkDate, String email, String phone, String homeworkFile, int teacherId, String status) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.group = group;
        this.homeworkDate = homeworkDate;
        this.email = email;
        this.phone = phone;
        this.homeworkFile = homeworkFile;
        this.teacherId = teacherId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getHomeworkDate() {
        return homeworkDate;
    }

    public void setHomeworkDate(String homeworkDate) {
        this.homeworkDate = homeworkDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHomeworkFile() {
        return homeworkFile;
    }

    public void setHomeworkFile(String homeworkFile) {
        this.homeworkFile = homeworkFile;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
