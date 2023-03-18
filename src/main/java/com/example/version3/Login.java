package com.example.version3;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
public class Login implements Initializable {

    @FXML
    private Button btnLogIn;

    @FXML
    private ComboBox<String> comb;

    @FXML
    private Label lbldisplay;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comb.setItems(FXCollections.observableArrayList("admin", "teacher",
                "student"));
    }

    @FXML
    void loginValidator(ActionEvent event) throws Exception {
       // try {
            String name = txtUserName.getText();
            String pswd = txtPassword.getText();
            String tablename = comb.getValue();

            String sql = "Select * from " + tablename + " where username = ? and  password = ?  ";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);

            st.setString(1,name);
            st.setString(2,pswd);

            ResultSet rs = st.executeQuery();


            if(!rs.next()){
                System.out.println("not correct");
                lbldisplay.setText("Login Failed");
            }
            else {
                if(tablename == "admin"){
                    Navigation.pagerouter("Admin_Dashboard.fxml",btnLogIn);
                }
                else if(tablename == "teacher"){
                    TeacherProfile.n(name);
                    Navigation.pagerouter("Teacher_Profile.fxml",btnLogIn);

                }
                else if(tablename == "student"){
                    StudentProfile.n(name);
                    Navigation.pagerouter("Student_Profile.fxml",btnLogIn);
                }


            }
//        }
//        catch (Exception e){
//            System.out.println("Failed");
//        }
    }
}

