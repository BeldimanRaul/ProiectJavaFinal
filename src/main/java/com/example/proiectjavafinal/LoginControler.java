package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginControler {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;

    @FXML
    private Label loginGresit;




    public void logingui(ActionEvent actionEvent) throws IOException {
          verificalogin();
    }

    private void verificalogin() throws IOException {
        Main main = new Main();
        if (username.getText().toString().equals("") || password.getText().equals("")) {
            loginGresit.setText("Succes");
            main.schimba("");
        }
    }

    public void registergui(ActionEvent actionEvent) {
    }
}
