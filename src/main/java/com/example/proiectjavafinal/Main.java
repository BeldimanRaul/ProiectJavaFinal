package com.example.proiectjavafinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {

    /// SI MAINUL SE REFACE VEZI LAB 2
    public static void main(String[] args) {
        FileDataManager fileDataManager = new FileDataManager();
        FileDisplay fileDisplay = new FileDisplay();
        String studentPath = "student.txt";
        String cursPath = "curs.txt";
        String profPath = "prof.txt";

        ///Curs curs=new Curs("","","","","", "","","");

        Student student1=new Student(1,"parola","username","raul","beldiman","lf4731",2);
        Student student2=new Student(2,"parola","username","ionut","cox","lf4731",2);






    }
}
