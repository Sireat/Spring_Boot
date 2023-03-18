package com.example.version3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;

public class TeacherViewStatus implements Initializable {

    public void initialize(URL location, ResourceBundle resources){
        try {
            TeacherProfile a = new TeacherProfile();
            lblName.setText("Mr. "+a.data().get(0));
            lblDept.setText(a.data().get(2));
            lblPhone.setText(a.data().get(1));
            lblRate.setText(a.data().get(3));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private Label lblDept;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;

    @FXML
    private Label lblRate;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignOut;

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
    @FXML
    void toTeacherProfile(ActionEvent event) throws Exception {
      Navigation.pagerouter("Teacher_Profile.fxml",btnBack);
    }

}