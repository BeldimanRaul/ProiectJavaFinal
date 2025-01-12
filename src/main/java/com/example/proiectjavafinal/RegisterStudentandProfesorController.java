package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class RegisterStudentandProfesorController {
    public void handleStudentRegister(ActionEvent actionEvent) throws IOException {
        try {
            Main main = new Main();
            main.schimba("student-register.fxml");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    public void handleProfesorRegister(ActionEvent actionEvent) throws  IOException {
        try {
            Main main = new Main();
            main.schimba("profesor-register.fxml");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            Main main = new Main();
            main.schimba("prima-pagina.fxml"); // Navigate back to the initial page
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
