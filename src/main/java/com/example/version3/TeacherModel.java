package com.example.version3;

import javafx.beans.property.StringProperty;

public class TeacherModel {
    private String name;
    private String email;
    private String phone;
    private String dept;
    private double evlt;

    public TeacherModel(String name, String email, String phone, String dept, double evlt) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dept = dept;
        this.evlt = evlt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getEvlt() {
        return evlt;
    }

    public void setEvlt(double evlt) {
        this.evlt = evlt;
    }
}

