package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void logingui(ActionEvent actionEvent) throws IOException {
        verificalogin();
    }
    private static User utilizatorilogati = null;
    private void verificalogin() {
        String user=username.getText();
        String pass=password.getText();
        if(user.isEmpty() || pass.isEmpty()) {
            labelGresit.setText("Nu ai introdus date !");
            return;
        }
        String parolahaz=SecuritateParole.parolahashuita(pass);
        if(profautentificat(user,parolahaz)) {
            labelGresit.setText("Autentificare reusita!");
        }
        else {
            labelGresit.setText("Autentificare nereusita,username sau parola gresita!");
        }


    }private static boolean profautentificat(String username,String password){
        for (Profesor profesor : Consola.profesori) {
            if (profesor.getUsername().equals(username) && profesor.getPassword().equals(password)) {
                utilizatorilogati = profesor;
                return true;
            }
        }
        return false;
    }
    }

