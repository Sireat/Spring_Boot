package com.example.version3;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class TeacherTableView {
    private final StringProperty id;
    private final StringProperty name;
    private final StringProperty email;

    private final StringProperty phone;
    private final StringProperty  dept;
    private final StringProperty  evlt;

    public TeacherTableView()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");
        email = new SimpleStringProperty(this, "email");
        phone = new SimpleStringProperty(this,"phone");
        dept = new SimpleStringProperty(this, "dept");
        evlt = new SimpleStringProperty(this, "evlt");

    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }

    public StringProperty emailProperty() { return email; }
    public String getEmail() { return email.get(); }
    public void setEmail(String newEmail) { email.set(newEmail); }

    public StringProperty phoneProperty() { return phone; }
    public String getPhone() { return phone.get(); }
    public void setPhone(String newPhone) { phone.set(newPhone); }
    public StringProperty deptProperty() { return dept; }
    public String getDept() { return dept.get(); }
    public void setDept(String newDept) { dept.set(newDept); }

    public StringProperty evltProperty() { return evlt; }
    public String getEvlt() { return evlt.get(); }
    public void setEvlt(String newEvlt) { evlt.set(newEvlt); }
}
