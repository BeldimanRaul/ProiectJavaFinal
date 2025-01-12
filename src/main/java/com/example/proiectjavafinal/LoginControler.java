package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
            main.schimba("prima-pagina.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void logingui(ActionEvent actionEvent) {
        try {
            verificalogin();
        } catch (Exception e) {
            e.printStackTrace();
            loginGresit.setText("Eroare în procesul de autentificare.");
        }
    }

    private void verificalogin() throws Exception {
        String user = username.getText().trim();
        String pass = password.getText().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            loginGresit.setText("Nu ai introdus date!");
            return;
        }

        String parolahash = SecuritateParole.parolahashuita(pass);

        Student student = autentificaStudent(user, parolahash);
        if (student != null) {
            StudentSession.setStudentCurent(student); // Setează studentul curent în sesiune
            loginGresit.setText("Autentificare reușită!");
            Main main = new Main();
            main.schimba("student-dashboard.fxml");
        } else {
            loginGresit.setText("Autentificare nereușită! Username sau parola greșită.");
        }
    }

    private Student autentificaStudent(String username, String password) {
        for (Student student : Consola.studentisinote.keySet()) {
            if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
                return student;
            }
        }
        return null;
    }
}
