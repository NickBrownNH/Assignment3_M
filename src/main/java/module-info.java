module com.example.assignment3_master {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assignment3_master to javafx.fxml;
    exports com.example.assignment3_master;
}