package com.example.version3;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Navigation {

//    static List<String> all_pages =List.of("Login.fxml","Admin_Dashboard.fxml",
//            "Admin_Questions.fxml", "Admin_Teachers.fxml","Admin_update_teacher.fxml",
//            "Admin_add_teacher");
//    static public void pagerouter(String name, Button btn)throws Exception{
//        int i = all_pages.indexOf(name);
//        String topage = all_pages.get(i);
//        System.out.println("to page :"+topage);
//        Parent root = FXMLLoader.load(Navigation.class.getResource(topage));
//        Stage window =(Stage)btn.getScene().getWindow();
//        window.setScene(new Scene(root, 939,625));
//
//    }

    static public void pagerouter(String name, Button btn)throws Exception{
        String topage = name;
        System.out.println("to page :"+ topage);
        Parent root = FXMLLoader.load(Navigation.class.getResource(topage));
        Stage window =(Stage)btn.getScene().getWindow();
        window.setScene(new Scene(root, 939,625));

    }
    static public void PopBox(String page) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Navigation.class.getResource(page));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
    }
}


