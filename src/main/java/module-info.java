module com.example.version3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.version3 to javafx.fxml;
    exports com.example.version3;
}