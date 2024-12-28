module com.example.proiectjavafinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proiectjavafinal to javafx.fxml;
    exports com.example.proiectjavafinal;
}