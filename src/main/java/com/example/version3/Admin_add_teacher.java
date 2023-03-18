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

public class Admin_add_teacher implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private ComboBox<String> combDepartment;

    @FXML
    private TextField txtEmail;

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
        email = txtEmail.getText();
        phone = txtPhone.getText();
        department = combDepartment.getValue();
        try
        {
            TeacherModel teacher = new TeacherModel(name,email,phone,department,0);

            String sql = "insert into teacher_info(name,email,phone,dept,evlt)values(?,?,?," +
                    "?,?)";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);

            st.setString(1, teacher.getName());
            st.setString(2, teacher.getEmail());
            st.setString(3, teacher.getPhone());
            st.setString(4,teacher.getDept());
            st.setDouble(5,teacher.getEvlt());

            st.executeUpdate();

            String sqll = "insert into teacher(username, password)values(?,?)";
            PreparedStatement stt = ConnectionDatabase.getConnection().prepareStatement(sqll);
            stt.setString(1, teacher.getName());
            stt.setString(2,"1234");
            stt.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teacher Registation");
            alert.setHeaderText("Teacher Registation");
            alert.setContentText("Record Added Successfully!");

            alert.showAndWait();
            txtName.setText("");
            txtEmail.setText("");
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
