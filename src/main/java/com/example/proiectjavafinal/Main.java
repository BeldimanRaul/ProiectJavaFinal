package com.example.proiectjavafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main extends Application {

    private static Stage stg;
    static ManagerCursuri mg = new ManagerCursuri();

    public void schimba(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) throws IOException {

        test_cursuri();
        launch(args);


    }

    public static void test_cursuri() {
        Curs curs = new Curs(1, 133, "Curs introductiv în algebră și geometrie", "Matematică");
        Curs curs2 = new Curs(1, 21, "Noțiuni de bază despre programare în Java", "Programare Java");
        Curs curs3 = new Curs(2, 31, "Istoria artei în perioada Renașterii", "Istoria Artei");
        Curs curs4 = new Curs(1, 41, "Bazele chimiei organice", "Chimie");
        Curs curs5 = new Curs(1, 51, "Introducere în psihologia comportamentală", "Psihologie");
        Curs curs6 = new Curs(1, 61, "Fizică aplicată pentru inginerie", "Fizică Aplicată");
        mg.adaugareCursuri(curs);
        mg.adaugareCursuri(curs2);
        mg.adaugareCursuri(curs3);
        mg.adaugareCursuri(curs4);
        mg.adaugareCursuri(curs5);
        mg.adaugareCursuri(curs6);
    }


    @Override
    public void start(Stage fereastraprincipala) throws Exception {
        stg = fereastraprincipala;

        Parent root = FXMLLoader.load(getClass().getResource("prima-pagina.fxml"));
        fereastraprincipala.setScene(new Scene(root, 600, 400));
        fereastraprincipala.setTitle("APLICATIE");
        fereastraprincipala.show();


    }
}

