package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginControler {


    private static User utilizatorilogati = null;



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
    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            Main main = new Main();
            main.schimba("prima-pagina.fxml"); // Navigate back to the initial page
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void logingui(ActionEvent actionEvent) throws IOException {
          verificalogin();
    }

    private void verificalogin() {
        String user=username.getText();
        String pass=password.getText();
        if(user.isEmpty() || pass.isEmpty()) {
            loginGresit.setText("Nu ai introdus date !");
            return;
        }
        String parolahaz=SecuritateParole.parolahashuita(pass);
        if(studentautentificat(user,parolahaz)) {
            loginGresit.setText("Autentificare reusita!");
        }
        else {
            loginGresit.setText("Autentificare nereusita,username sau parola gresita!");
        }


    }

    private static boolean studentautentificat(String username, String password) {
        for (User user : Consola.studentisinote.keySet()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
               return true;
            }
        }
        return false;
    }
}
