package com.example.version3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentTableView {
    private final StringProperty id;
    private final StringProperty name;

    private final StringProperty phone;
    private final StringProperty  dept;


    public StudentTableView()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        phone = new SimpleStringProperty(this,"phone");
        dept = new SimpleStringProperty(this, "dept");


    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }


    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }
    public StringProperty deptProperty() { return dept; }
    public String getDept() { return dept.get(); }
    public void setDept(String newDept) { dept.set(newDept); }

}
