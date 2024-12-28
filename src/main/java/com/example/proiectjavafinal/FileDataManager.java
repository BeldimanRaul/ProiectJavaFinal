package com.example.proiectjavafinal;

import java.io.*;
import java.util.*;

public class FileDataManager {

String studentPath="student.txt";
String cursPath="curs.txt";
String profPath="prof.txt";
String notePath="note.txt";



 public List<Curs>createCourseData(String cursPath,String notePath,List<Student>studenti ) throws IOException {
     List<Curs> courseData=new ArrayList<>();
     BufferedReader brCurs=new BufferedReader(new FileReader(cursPath));
     String linie;

     while((linie=brCurs.readLine())!=null){
         String[]parti=linie.split(",");
         int id = Integer.parseInt(parti[0]);
         String nume = parti[1];
         String descriere = parti[2];
         int idProfesor = Integer.parseInt(parti[3]);
         courseData.add(new Curs(id, nume, descriere, idProfesor));



     }
     brCurs.close();
 }

 public List<Profesor>createProfesorData(String profPath) throws IOException {
     List<Profesor> profesori=new ArrayList<>();
     BufferedReader br=new BufferedReader(new FileReader(profPath));
     String linie;
     while((linie=br.readLine())!=null){
         String[] splituri=linie.split(",");
         if (splituri.length == 5) {
             int id = Integer.parseInt(splituri[0]);
             String nume = splituri[1];
             String prenume = splituri[2];
             String username = splituri[3];
             String password = splituri[4];
             profesori.add(new Profesor(id,nume,prenume,username,password));
         }
     }br.close();
     return profesori;
 }


}