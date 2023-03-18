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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TeacherProfile implements Initializable {
    public static String name;
    public void initialize(URL location, ResourceBundle resources){
        try {
            data();
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
    private Button btnSignOut;

    @FXML
    private Button btnViewStatus;

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
    void toViewStatus(ActionEvent event) throws Exception {
      //  TeacherViewStatus.n(name);
      Navigation.pagerouter("Teacher_View_Status.fxml",btnViewStatus);
    }
    public List<String> data() throws Exception {
        String sql = "Select name,phone,dept,evlt from teacher_info where name= ?";
        PreparedStatement pst = ConnectionDatabase.getConnection().prepareStatement(sql);
        pst.setString(1,name);
        //pst = con.prepareStatement("select id,name,mobile,course from registation");
        ResultSet rs = pst.executeQuery();
        rs.next();
        try {
            lblName.setText("Mr. " + rs.getString("name"));
            lblPhone.setText(rs.getString("phone"));
            lblDept.setText(rs.getString("dept"));
        }
        catch (Exception e){
            System.out.println("");
        }


        List<String> list1 = new ArrayList<String>();
        list1.add(rs.getString("name"));
        list1.add(rs.getString("phone"));
        list1.add(rs.getString("dept"));
        list1.add(rs.getString("evlt"));
        return list1;

    }
    public static void n(String n){
        name = n;
    }

}
