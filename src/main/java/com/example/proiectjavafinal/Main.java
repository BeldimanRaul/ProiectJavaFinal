package com.example.proiectjavafinal;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Main {

    /// SI MAINUL SE REFACE VEZI LAB 2
    public static void main(String[] args) {
        FileDataManager fileDataManager = new FileDataManager();
        FileDisplay fileDisplay = new FileDisplay();


        Student student1=new Student(1,"beldiman","raul",2,"lf4731",",ionescu.andrei@gmial.com","parola");
        Student student2=new Student(1,"beldiman","raul",2,"lf4731",",ionescu.andrei@gmial.com","parola");
        Curs curs1=new Curs(1,1,"descriere","matematica");
        curs1.adaugareStudenti(student1);








    }
}
