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

public class Admin_update_teacher implements Initializable {
    public static int i;
    public static String  n,e,p;
    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOk;

    @FXML
    private ComboBox<String> combDepartment;

    @FXML
    private  TextField txtEmail;

    @FXML
    private  TextField txtName;

    @FXML
    private  TextField txtPhone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        info();
        combDepartment.setItems(FXCollections.observableArrayList(
                "Software Engineering",
                "Computer Science",
                "It",
                "Is"));
    }

    @FXML
    void Cancel(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Teachers.fxml",btnCancel);

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
            String sql = "update teacher_info set name = ?,email = ? ,phone = ?, dept = ? where id = ? ";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);

            st.setString(1, name);
            st.setString(2, email);
            st.setString(3, phone);
            st.setString(4,department);
            st.setInt(5,i);
            st.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Update Teacher");
            alert.setHeaderText("Update Teacher");
            alert.setContentText("Record Updated Successfully!");

            alert.showAndWait();

            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");


        }
        catch (SQLException ex)
        {
            Logger.getLogger(Admin_add_teacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  void info(){
        System.out.println(i);
        txtName.setText(n);
        txtEmail.setText(e);
        txtPhone.setText(p);
    }

    public static void update_info(int id, String name, String email, String phone){
         i = id;
         n = name;
         e = email;
         p = phone;
    }

}
