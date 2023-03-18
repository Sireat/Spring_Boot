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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentProfile implements Initializable {
public int myIndex,id;
public static int i;
public String n,p;
public static String name,depat;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            data();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        table();
    }
    @FXML
    private Button btnEvaluateTeacher;

    @FXML
    private Button btnSignOut;

    @FXML
    private TableColumn<TeacherTableView, String> deptColumn;

    @FXML
    private TableColumn<TeacherTableView, String> idColumn;

    @FXML
    private TableColumn<TeacherTableView, String> nameColumn;

    @FXML
    private TableView<TeacherTableView> table;

    @FXML
    private Label lblDepartment;

    @FXML
    private Label lblId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblPhone;
    @FXML
    void toEvaluteTeacher(ActionEvent event) throws Exception {
        StudentTeacherEvaluationForm.teach_info(i);
        Navigation.pagerouter("Student_Teacher-evaluation_form.fxml",btnEvaluateTeacher);
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

    public void table()
    {
        ObservableList<TeacherTableView> teacherTableViews = FXCollections.observableArrayList();
        try
        {
            String sqll = "Select dept from student_info where Name=?";
            PreparedStatement pstt = ConnectionDatabase.getConnection().prepareStatement(sqll);
            pstt.setString(1,name);
            ResultSet rss = pstt.executeQuery();
            rss.next();
            depat = rss.getString("dept");

            String sql = "Select id,name,dept from teacher_info where dept=?";
            PreparedStatement pst = ConnectionDatabase.getConnection().prepareStatement(sql);
            pst.setString(1,depat);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    TeacherTableView st = new TeacherTableView();
                    st.setId(rs.getString("id"));
                    st.setName(rs.getString("name"));
                    st.setDept(rs.getString("dept"));
                    teacherTableViews.add(st);
                }
            }
            table.setItems(teacherTableViews);
            idColumn.setCellValueFactory(f -> f.getValue().idProperty());
            nameColumn.setCellValueFactory(f -> f.getValue().nameProperty());
            deptColumn.setCellValueFactory(f -> f.getValue().deptProperty());

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
                    p = table.getItems().get(myIndex).getPhone();
                }
            });
            return myRow;
        });



    }
    public List<String> data() throws Exception {
        String sql = "Select id,name,phone,dept from student_info where name= ?";
        PreparedStatement pst = ConnectionDatabase.getConnection().prepareStatement(sql);
        pst.setString(1,name);
        //pst = con.prepareStatement("select id,name,mobile,course from registation");
        ResultSet rs = pst.executeQuery();
        rs.next();
        try {
            lblId.setText(rs.getString("id"));
            lblName.setText(rs.getString("name"));
            lblPhone.setText(rs.getString("phone"));
            lblDepartment.setText(rs.getString("dept"));
        }
        catch (Exception e){
            System.out.println("");
        }
        List<String> list1 = new ArrayList<String>();
        list1.add(rs.getString("id"));
        list1.add(rs.getString("name"));
        list1.add(rs.getString("phone"));
        list1.add(rs.getString("dept"));
        return list1;
    }
    public static void n(String n){
        name = n;
    }


}
