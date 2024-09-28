package com.entity;

public class Speciality {
    private int id;
    private String specialityName;

    public Speciality() {}

    public Speciality(int id, String specialityName) {
        this.id = id;
        this.specialityName = specialityName;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getSpecialityName() {
        return specialityName;
    }
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
