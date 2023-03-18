package com.example.version3;

import javafx.beans.property.StringProperty;

public class StudentModel {

    private String name;
    private String phone;
    private String dept;

    public StudentModel(String name, String phone, String dept) {
        this.name = name;
        this.phone = phone;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
