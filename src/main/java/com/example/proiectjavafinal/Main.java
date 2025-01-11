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


    public void schimba(String fxml) throws IOException {
        Parent pane =FXMLLoader.load(getClass().getResource(fxml)) ;
        stg.getScene().setRoot(pane);
    }
    public static void main (String[] args) throws IOException {
        launch(args);

    }

    @Override
    public void start(Stage fereastraprincipala) throws Exception {
        stg = fereastraprincipala;
        fereastraprincipala.setResizable(false);
       Parent root=FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        fereastraprincipala.setScene(new Scene(root,600,400));
        fereastraprincipala.setTitle("APLICATIE");
        fereastraprincipala.show();


    }
}