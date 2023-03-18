package com.example.version3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    public void initialize(URL location, ResourceBundle resources){
        try {
            display();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Label lblTotalTeachers;

    @FXML
    private Label lblTotalStudents;
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnQuestions;

    @FXML
    private Button btnSignOut;

    @FXML
    private Button btnTeachers;

    @FXML
    private Button btnStudents;

    @FXML
    void toDashboard(ActionEvent event) {
        btnDashboard.isDefaultButton();
        System.out.println("you are in the dashboard");
    }

    @FXML
    void toQuestions(ActionEvent event) throws Exception {
         Navigation.pagerouter("Admin_Questions.fxml",btnQuestions);
    }

    @FXML
    void toTeachers(ActionEvent event) throws Exception {
       Navigation.pagerouter("Admin_Teachers.fxml",btnTeachers);
    }

    @FXML
    void toStudents(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Students.fxml",btnTeachers);
    }
@FXML
void toSignOut(ActionEvent event) throws Exception {

    Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure");
    //a.showAndWait();
    Optional<ButtonType> option = a.showAndWait();
    if (option.get() == ButtonType.OK){
        a.close();
        Navigation.pagerouter("Login.fxml",btnSignOut);
    }
    else{
        a.close();
    }
}

    public void display() throws Exception {
        int count = 0;
        String sql = "Select * from teacher_info";
        PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);
        ResultSet teacher = st.executeQuery();
        while(teacher.next()){
            count = count + 1;
        }
        lblTotalTeachers.setText(String.valueOf(count));
        count = 0;
        String sqll = "Select * from student_info";
        PreparedStatement stt = ConnectionDatabase.getConnection().prepareStatement(sqll);
        ResultSet student = stt.executeQuery();
        while(student.next()){
            count = count + 1;
        }
        lblTotalStudents.setText(String.valueOf(count));
    }
}

