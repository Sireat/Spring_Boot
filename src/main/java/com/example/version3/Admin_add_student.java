package com.example.version3;

import javafx.collections.FXCollections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Admin_add_student implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private ComboBox<String> combDepartment;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        combDepartment.setItems(FXCollections.observableArrayList(
                "Software Engineering",
                "Computer Science",
                "It",
                "IS"));
    }
    @FXML
    void Ok(ActionEvent event) {

        String name,email,phone,department;
        name = txtName.getText();
        phone = txtPhone.getText();
        department = combDepartment.getValue();
        try
        {
            StudentModel student = new StudentModel(name,phone,department);
            String sql = "insert into student_info(name,phone,dept)values(?,?,?)";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);

            st.setString(1, student.getName());
            st.setString(2, student.getPhone());
            st.setString(3,student.getDept());
            st.executeUpdate();

            String sqll = "insert into student(username, password)values(?,?)";
            PreparedStatement stt = ConnectionDatabase.getConnection().prepareStatement(sqll);
            stt.setString(1,name);
            stt.setString(2,"1234");
            stt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registation");
            alert.setHeaderText("Student Registation");
            alert.setContentText("Student Added Successfully!");

            alert.showAndWait();
            txtName.setText("");

            txtPhone.setText("");

            //  Navigation.pagerouter("Admin_Teachers.fxml");



        }
        catch (SQLException ex)
        {
            Logger.getLogger(Admin_add_teacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
