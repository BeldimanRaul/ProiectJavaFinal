package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.*;

public class StudentRegister {

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
    private void handleRegisterStudent(ActionEvent event) throws IOException {
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

        int id = IdGenerator.idElev();
        Student student = new Student(id, nume, prenume, an, "", username, parolahaz);
        ManagerUtilizatori.adaugaUtilizatori(student);
        Consola.studentisinote.put(student, new ArrayList<>());
        int iddoizeze = id + 1;
        messageLabel.setText("Student înregistrat cu succes! ID-ul tau este: " + iddoizeze);
    try {
        fileDisplay.displayStudents(Consola.studentisinote.keySet());
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
