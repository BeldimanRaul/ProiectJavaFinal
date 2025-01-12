package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfesorLogin {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    private Label labelGresit;

    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            Main main = new Main();
            main.schimba("prima-pagina.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logingui1(ActionEvent actionEvent) {
        try {
            verificalogin1();
        } catch (Exception e) {
            e.printStackTrace();
            labelGresit.setText("Eroare în procesul de autentificare.");
        }
    }

    private void verificalogin1() throws Exception {
        Main main = new Main();

        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            labelGresit.setText("Nu ai introdus date!");
            return;
        }

        String parolahash = SecuritateParole.parolahashuita(pass);
        Profesor profesor = autentificaProfesor(user, parolahash);

        if (profesor != null) {
            ProfesorSession.setProfesorCurent(profesor);
            labelGresit.setText("Autentificare reușită!");
            main.schimba("profesor-dashboard.fxml");
        } else {
            labelGresit.setText("Autentificare eșuată! Username sau parola greșită.");
        }
    }

    private Profesor autentificaProfesor(String username, String password) {
        for (Profesor profesor : Consola.profesori) {
            if (profesor.getUsername().equals(username) && profesor.getPassword().equals(password)) {
                return profesor;
            }
        }
        return null;
    }
}
