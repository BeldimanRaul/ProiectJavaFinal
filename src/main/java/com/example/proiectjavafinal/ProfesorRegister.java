package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorRegister {

    private FileDisplay fileDisplay = new FileDisplay();

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField numeField;
    @FXML
    private TextField prenumeField;
    @FXML
    private TextField anField;
    @FXML
    private Label messageLabel;

    @FXML
    public void handleProfesorRegister(ActionEvent event) {
        String username = usernameField.getText();
        if (!ManagerUtilizatori.verificaUnicitateUsername(username)) {
            messageLabel.setText("Username-ul este deja folosit. Incercați altul.");
            return;
        }

        String password = passwordField.getText();
        String parolahaz = SecuritateParole.parolahashuita(password);

        String nume = numeField.getText();
        String prenume = prenumeField.getText();
        int an;
        try {
            an = Integer.parseInt(anField.getText());
        } catch (NumberFormatException e) {
            messageLabel.setText("Introduceți un an valid.");
            return;
        }

        int id = IdGenerator.idProfesor();
        Profesor profesor = new Profesor(id, parolahaz, username, prenume, nume);
        Consola.profesori.add(profesor);
        ManagerUtilizatori.adaugaUtilizatori(profesor);
        int iddoizeze = id + 1;
        messageLabel.setText("Profesor înregistrat cu succes! ID-ul tau este: " + iddoizeze);

        // Debug statement


        try {
            fileDisplay.displayTeachers(Consola.profesori);
        } catch (IOException e) {
            e.printStackTrace();
            messageLabel.setText("Eroare la scrierea în fișier.");
        }
    }
    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            Main main = new Main();
            main.schimba("register-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
