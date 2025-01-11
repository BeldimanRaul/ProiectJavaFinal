package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaPaginaController {

    @FXML
    private Button studentButton;

    @FXML
    private Button profesorButton;

    @FXML
    public void handleStudentButton(ActionEvent event) throws IOException {

        Main main = new Main();
        main.schimba("hello-view.fxml");
    }

    @FXML
    public void handleProfesorButton(ActionEvent event) throws IOException {
        // Handle profesor button click and navigate to the professor login page
        Main main = new Main();
        main.schimba("login-view.fxml"); // Assuming "login-view.fxml" is the login page for professors
    }
}