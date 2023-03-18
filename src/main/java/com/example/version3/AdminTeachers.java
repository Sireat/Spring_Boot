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

public class AdminTeachers implements Initializable {
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
    private TableColumn<TeacherTableView, String> deptColumn;

    @FXML
    private TableColumn<TeacherTableView, String> emailColumn;

    @FXML
    private TableColumn<TeacherTableView, String> phoneColumn;
    @FXML
    private TableColumn<TeacherTableView, String> evaluateColumn;

    @FXML
    private TableColumn<TeacherTableView, String> idColumn;

    @FXML
    private TableColumn<TeacherTableView, String> nameColumn;

    @FXML
    private TableView<TeacherTableView> table;
    @FXML
    void Add(ActionEvent event) throws Exception {
        Navigation.PopBox("Admin_add_teacher.fxml");
    }

    @FXML
    void Delete(ActionEvent event) throws Exception {
        try{
        String sql = "delete from teacher_info where id = ?";
        PreparedStatement st = ConnectionDatabase.getConnection().prepareStatement(sql);
        st.setInt(1,AdminTeachers.i);
        st.executeUpdate();
            //System.out.println(AdminTeachers.i);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teacher Information");

            alert.setHeaderText("Teacher Data");
            alert.setContentText("Deletedd!");

            alert.showAndWait();
            table();

        }
        catch (SQLException ex)
    {
        Logger.getLogger(AdminTeachers.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    @FXML
    void Update(ActionEvent event) throws Exception {
        Admin_update_teacher.update_info(i,n,e,p);
        Navigation.PopBox("Admin_update_teacher.fxml");
    }



    public void table()
    {
        ObservableList<TeacherTableView> teacherTableViews = FXCollections.observableArrayList();
        try
        {
            String sql = "Select id,name,email,phone,dept,evlt from teacher_info";
            PreparedStatement pst = ConnectionDatabase.getConnection().prepareStatement(sql);
            //pst = con.prepareStatement("select id,name,mobile,course from registation");
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    TeacherTableView st = new TeacherTableView();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));
                    st.setEmail(rs.getString("email"));
                    st.setPhone(rs.getString("phone"));
                    st.setDept(rs.getString("dept"));
                    st.setEvlt(rs.getString("evlt"));
                    teacherTableViews.add(st);
                }
            }
            table.setItems(teacherTableViews);
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
            nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
            emailColumn.setCellValueFactory(f -> f.getValue().emailProperty());
            phoneColumn.setCellValueFactory(f -> f.getValue().phoneProperty());
            deptColumn.setCellValueFactory(f -> f.getValue().deptProperty());
            evaluateColumn.setCellValueFactory(f -> f.getValue().evltProperty());

        }

        catch (SQLException ex)
        {
            Logger.getLogger(AdminTeachers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        table.setRowFactory( tv -> {
            TableRow<TeacherTableView> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex = table.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getId()));
                    i = Integer.parseInt(table.getItems().get(myIndex).getId());
                    n = table.getItems().get(myIndex).getName();
                    e = table.getItems().get(myIndex).getEmail();
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


