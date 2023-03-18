package com.example.version3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javax.xml.transform.Result;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentTeacherEvaluationForm implements Initializable {
    int a,b,c,d,e;
    public static int i;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
        StudentProfile a = new StudentProfile();
        lblId.setText(a.data().get(0));
        lblName.setText(a.data().get(1));
        lblDept.setText(a.data().get(3));
        lblPhone.setText(a.data().get(2));
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
        group();
    }

    @FXML
    private Label lblDept;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;
    @FXML
    private Button btnBack;

    @FXML
    private Button btnSignOut;

    @FXML
    private RadioButton r1a;

    @FXML
    private RadioButton r1b;

    @FXML
    private RadioButton r1c;

    @FXML
    private RadioButton r1d;

    @FXML
    private RadioButton r2a;

    @FXML
    private RadioButton r2b;

    @FXML
    private RadioButton r2c;

    @FXML
    private RadioButton r2d;

    @FXML
    private RadioButton r3a;

    @FXML
    private RadioButton r3b;

    @FXML
    private RadioButton r3c;

    @FXML
    private RadioButton r3d;

    @FXML
    private RadioButton r4a;

    @FXML
    private RadioButton r4b;

    @FXML
    private RadioButton r4c;

    @FXML
    private RadioButton r4d;

    @FXML
    private RadioButton r5a;

    @FXML
    private RadioButton r5b;

    @FXML
    private RadioButton r5c;

    @FXML
    private RadioButton r5d;

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
    void toStudentProfile(ActionEvent event) throws Exception {
       Navigation.pagerouter("Student_Profile.fxml",btnBack);
    }
    ToggleGroup q1 = new ToggleGroup();
    ToggleGroup q2 = new ToggleGroup();
    ToggleGroup q3 = new ToggleGroup();
    ToggleGroup q4 = new ToggleGroup();
    ToggleGroup q5 = new ToggleGroup();

    @FXML
    public void Submit(ActionEvent event) {
        int sum = a+b+c+d+e;
        double total,result;
        total = (sum*100)/20;

        try
        {
            String sqll = "select evlt from teacher_info where id = ? ";
            PreparedStatement stt = ConnectionDatabase.getConnection().prepareStatement(sqll);
            stt.setInt(1,i);
            ResultSet rs = stt.executeQuery();
            rs.next();
            if (rs.getString("evlt")==null){
                String sql = "update teacher_info set evlt = ?  where id = ? ";
                PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);
                st.setDouble(1, total);
                st.setInt(2,i);
                st.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Evaluation Form");
                alert.setHeaderText("Evaluation Form");
                alert.setContentText("Recorded Submitted Successfully!");

                alert.showAndWait();
            }
            else{
            result = Double.parseDouble(rs.getString("evlt"));
            double x = (result + total)/2;
            String sql = "update teacher_info set evlt = ?  where id = ? ";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);
            st.setDouble(1, x);
            st.setInt(2,i);
            st.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Evaluation Form");
            alert.setHeaderText("Evaluation Form");
            alert.setContentText("Recorded Submitted Successfully!");

            alert.showAndWait();
            }

        }
        catch (SQLException ex)
        {
            Logger.getLogger(Admin_add_teacher.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public static void teach_info(int id){
        i = id;
    }
    void group(){

         List<RadioButton> list1 = List.of(
                 r1a,r1b,r1c,r1d,
                 r2a,r2b,r2c,r2d,
                 r3a,r3b,r3c,r3d,
                 r4a,r4b,r4c,r4d,
                 r5a,r5b,r5c,r5d);
         List<ToggleGroup> listtoggle = List.of(q1,q2,q3,q4,q5);
        int j = 0;
        for (int i=0; i<=4;i++){
            int counter = 0;
            while (true){
                list1.get(j).setToggleGroup(listtoggle.get(i));
                j++;
                counter++;
                if(counter==4) {
                    break;
                }
            }
        }

        q1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed (ObservableValue < ? extends Toggle > ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) q1.getSelectedToggle();
                a= Integer.parseInt(rb.getText());
            }
        });
        q2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed (ObservableValue < ? extends Toggle > ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) q2.getSelectedToggle();
                b= Integer.parseInt(rb.getText());
            }
        });
        q3.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed (ObservableValue < ? extends Toggle > ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) q3.getSelectedToggle();
                c= Integer.parseInt(rb.getText());
            }
        });
        q4.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed (ObservableValue < ? extends Toggle > ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) q4.getSelectedToggle();
                d= Integer.parseInt(rb.getText());
            }
        });
        q5.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed (ObservableValue < ? extends Toggle > ob, Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton) q5.getSelectedToggle();
                e= Integer.parseInt(rb.getText());
            }
        });
    }



}