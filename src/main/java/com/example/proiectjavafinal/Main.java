package com.example.proiectjavafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {
    public static void main (String[] args) throws IOException {
        launch(args);

    }

    @Override
    public void start(Stage fereastra) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root,300,200);
        fereastra.setTitle("test");
        fereastra.setScene(scene);
        fereastra.show();
    }
}