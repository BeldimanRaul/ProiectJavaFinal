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
    private static ManagerCursuri managerCursuri;

    public void schimba(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }


    public static void test_cursuri() {
        Curs curs = new Curs(1, 133, "Curs introductiv în algebră și geometrie", "Matematică");
        Curs curs2 = new Curs(1, 21, "Noțiuni de bază despre programare în Java", "Programare Java");
        Curs curs3 = new Curs(2, 31, "Istoria artei în perioada Renașterii", "Istoria Artei");
        Curs curs4 = new Curs(1, 41, "Bazele chimiei organice", "Chimie");
        Curs curs5 = new Curs(1, 51, "Introducere în psihologia comportamentală", "Psihologie");
        Curs curs6 = new Curs(1, 61, "Fizică aplicată pentru inginerie", "Fizică Aplicată");
        managerCursuri.adaugareCursuri(curs);
        managerCursuri.adaugareCursuri(curs2);
        managerCursuri.adaugareCursuri(curs3);
        managerCursuri.adaugareCursuri(curs4);
        managerCursuri.adaugareCursuri(curs5);
        managerCursuri.adaugareCursuri(curs6);

    }


    @Override
    public void start(Stage fereastraprincipala) throws Exception {
        // Salvează referința la Stage pentru utilizări viitoare
        stg = fereastraprincipala;

        // Încarcă fișierul FXML folosind FXMLLoader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("prima-pagina.fxml"));
        Parent root = loader.load();

        // Obține controller-ul asociat fișierului FXML
        PrimaPaginaController controller = loader.getController();

        // Dacă e nevoie, inițializează obiecte sau configurări pentru controller
        controller.setManagerCursuri(managerCursuri);

        // Configurează scena principală
        fereastraprincipala.setScene(new Scene(root, 600, 400));
        fereastraprincipala.setTitle("APLICATIE");
        fereastraprincipala.show();
    }

    public static void main(String[] args) throws IOException {

        test_cursuri();
        launch(args);


    }
}

