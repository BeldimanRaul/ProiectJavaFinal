package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PrimaPaginaController {

    @FXML
    private Button studentButton;

    @FXML
    private Button profesorButton;
    private ManagerCursuri managerCursuri;

    @FXML
   
    public void handleStudentButton(ActionEvent event) throws Exception {

        Main main = new Main();
        main.schimba("hello-view.fxml");
    }
    @FXML
    private void handleRegisterButton(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    ///pt login
    public void handleProfesorButton(ActionEvent event) throws Exception {

        Main main = new Main();
        main.schimba("profesor-login.fxml");
    }
    public void setManagerCursuri(ManagerCursuri managerCursuri) {
        this.managerCursuri = managerCursuri;
    }
}