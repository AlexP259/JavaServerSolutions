package com.entity;

public class Teacher {
    private  int id;
    private String fullName;
    private String dob;
    private String qualification;
    private String  speciality;
    private String email;
    private String mobNo;
    private String password;


    public Teacher(){}

    public Teacher(String fullName, String dob, String qualification, String speciality, String email, String mobNo, String password) {
        this.fullName = fullName;
        this.dob = dob;
        this.qualification = qualification;
        this.speciality = speciality;
        this.email = email;
        this.mobNo = mobNo;
        this.password = password;
    }

    public Teacher(int id, String fullName, String dob, String qualification, String speciality, String email, String mobNo, String password) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.qualification = qualification;
        this.speciality = speciality;
        this.email = email;
        this.mobNo = mobNo;
        this.password = password;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getDob() {
        return dob;
    }
    public void setDob(String dob) {
        this.dob = dob;
    }


    public String getQualification() {
        return qualification;
    }
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }


    public String getSpeciality() {
        return speciality;
    }
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


    public String getMobNo() {
        return mobNo;
    }
    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }


    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
