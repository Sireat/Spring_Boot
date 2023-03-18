package com.example.version3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AdminQuestions {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnQuestions;

    @FXML
    private Button btnTeachers;


    @FXML
    private Button btnStudents;

    @FXML
    private Button btnSignOut;

    @FXML
    void toDashboard(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Dashboard.fxml",btnDashboard);
    }
    @FXML
    private Button btnEditQuestion;
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
//    @FXML
//    void editQuestion(ActionEvent event) {
//
//    }

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

    void editQuestion(){

    }
    void addQuestion(){

    }


}
