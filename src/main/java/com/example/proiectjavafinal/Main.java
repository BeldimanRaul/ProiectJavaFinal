package com.example.proiectjavafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends Application {

    private static Stage stg;
    private static ManagerCursuri managerCursuri;


    public static ManagerCursuri getManagerCursuri() {
        if (managerCursuri == null) {
            managerCursuri = ManagerCursuri.getInstance();
        }
        return managerCursuri;
    }

    public void schimba(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent pane = loader.load();
        stg.getScene().setRoot(pane);
    }

    public static void test_cursuri() {

        managerCursuri = ManagerCursuri.getInstance();


        Curs curs1 = new Curs(1, 133, "Curs introductiv în algebră și geometrie", "Matematică");
        Curs curs2 = new Curs(1, 21, "Noțiuni de bază despre programare în Java", "Programare Java");
        Curs curs3 = new Curs(2, 31, "Istoria artei în perioada Renașterii", "Istoria Artei");
        Curs curs4 = new Curs(1, 41, "Bazele chimiei organice", "Chimie");
        Curs curs5 = new Curs(1, 51, "Introducere în psihologia comportamentală", "Psihologie");
        Curs curs6 = new Curs(1, 61, "Fizică aplicată pentru inginerie", "Fizică Aplicată");

        managerCursuri.adaugareCursuri(curs1);
        managerCursuri.adaugareCursuri(curs2);
        managerCursuri.adaugareCursuri(curs3);
        managerCursuri.adaugareCursuri(curs4);
        managerCursuri.adaugareCursuri(curs5);
        managerCursuri.adaugareCursuri(curs6);

        Profesor profesor = new Profesor("p1", "hashedPassword", "usernameProf", "Nume", 1);

        // Asociază profesorul la curs
        curs1.setProfesor(profesor);
        curs2.setProfesor(profesor);

        System.out.println("Cursuri de test adăugate: " + managerCursuri.getCursuri());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        /// Salvează referința la stage pentru utilizări ulterioare
        stg = primaryStage;


        FXMLLoader loader = new FXMLLoader(getClass().getResource("prima-pagina.fxml"));
        Parent root = loader.load();


        primaryStage.setTitle("Aplicație Educațională");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();


        PrimaPaginaController controller = loader.getController();
        controller.setManagerCursuri(managerCursuri);
    }

    public static void main(String[] args) {
        /// Inițializează datele de test
        test_cursuri();


        launch(args);
    }
}
