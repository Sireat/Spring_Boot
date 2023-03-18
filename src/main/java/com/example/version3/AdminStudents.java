package com.example.version3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.scene.control.Button;

public class AdminStudents implements Initializable {
    public int myIndex,id;
    public static int i;
    public String n,e,p;
    public void initialize(URL location, ResourceBundle resources){
        try {
            table();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
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
    void toDashboard(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Dashboard.fxml",btnDashboard);
    }

    @FXML
    void toQuestions(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Questions.fxml",btnQuestions);
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

    @FXML
    void toTeachers(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Teachers.fxml",btnTeachers);
    }


    @FXML
    void toStudents(ActionEvent event) throws Exception {
        Navigation.pagerouter("Admin_Students.fxml",btnTeachers);
    }
    @FXML
    private Button btnAdd;


    @FXML
    private Button btnDelete;



    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<StudentTableView, String> deptColumn;

    @FXML
    private TableColumn<StudentTableView, String> phoneColumn;

    @FXML
    private TableColumn<StudentTableView, String> idColumn;

    @FXML
    private TableColumn<StudentTableView, String> nameColumn;

    @FXML
    private TableView<StudentTableView> table;
    @FXML
    void Add(ActionEvent event) throws Exception {
        Navigation.PopBox("Admin_add_student.fxml");

    }

    @FXML
    void Delete(ActionEvent event) throws Exception {
        try{
            String sql = "delete from student_info where id = ?";
            PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);
            st.setInt(1,AdminStudents.i);
            st.executeUpdate();
            //System.out.println(AdminTeachers.i);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Information");

            alert.setHeaderText("Student Data");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();

        }
        catch (SQLException ex)
        {
            Logger.getLogger(AdminStudents.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    void Update(ActionEvent event) throws Exception {
        Admin_update_student.update_info(i,n,p);
        Navigation.PopBox("Admin_update_student.fxml");
    }
    public void table()
    {
        ObservableList<StudentTableView> studentTableViews = FXCollections.observableArrayList();
        try
        {
            String sql = "Select id,name,phone,dept from student_info";
            PreparedStatement pst = ConnectionDatabase.getConnection().prepareStatement(sql);
            //pst = con.prepareStatement("select id,name,mobile,course from registation");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    StudentTableView st = new StudentTableView();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));

                    st.setPhone(rs.getString("phone"));
                    st.setDept(rs.getString("dept"));

                    studentTableViews.add(st);
                }
            }
            table.setItems(studentTableViews);
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
            nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
            phoneColumn.setCellValueFactory(f -> f.getValue().phoneProperty());
            deptColumn.setCellValueFactory(f -> f.getValue().deptProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(AdminTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        table.setRowFactory( tv -> {
            TableRow<StudentTableView> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    i = Integer.parseInt(table.getItems().get(myIndex).getId());
                    n = table.getItems().get(myIndex).getName();
                    //e = table.getItems().get(myIndex).getEmail();
                    p = table.getItems().get(myIndex).getPhone();
                    //d = table.getItems().get(myIndex).getDept();

                    //System.out.println(i + n + e+ p+d);
                    //Admin_update_teacher.update_info(n,e,p);

                }
            });
            return myRow;
        });

    }

}


