module com.example.proiectjavafinal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.proiectjavafinal to javafx.fxml;
    exports com.example.proiectjavafinal;
}