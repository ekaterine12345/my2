module com.example.preperation_final_2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;


    opens com.example.preperation_final_2 to javafx.fxml;
    exports com.example.preperation_final_2;
}