package com.example.proiectjavafinal;
///CITIRE
import java.io.*;
import java.util.*;

public class FileDataManager {

    String studentPath = "student.txt";
    String cursPath = "curs.txt";
    String profPath = "prof.txt";
    String notePath = "note.txt";
    int test;


    public List<Curs> createCourseData(String cursPath) throws IOException {
        List<Curs> courseData = new ArrayList<>();
        BufferedReader brCurs = new BufferedReader(new FileReader(cursPath));
        String linie;

        while ((linie = brCurs.readLine()) != null) {
            String[] parti = linie.split(",");
            int id = Integer.parseInt(parti[0]);
            String nume = parti[1];
            String descriere = parti[2];
            int idProfesor = Integer.parseInt(parti[3]);
            courseData.add(new Curs(id, idProfesor, descriere, nume));


        }
        brCurs.close();
        return courseData;
    }

    public List<Profesor> createProfesorData(String profPath) throws IOException {
        List<Profesor> profesori = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(profPath));
        String linie;
        while ((linie = br.readLine()) != null) {
            String[] splituri = linie.split(",");
            if (splituri.length == 5) {
                int id = Integer.parseInt(splituri[0]);
                String nume = splituri[1];
                String prenume = splituri[2];
                String username = splituri[3];
                String password = splituri[4];
                int an=Integer.parseInt(splituri[5]);
                profesori.add(new Profesor(username, password, nume, prenume, an));
            }
        }
        br.close();

        return profesori;
    }

    public List<Nota> createNoteData(String notePath) throws IOException {
        List<Nota> noteData = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(notePath));
        String linie;
        while ((linie = br.readLine()) != null) {
            String[] splituri = linie.split(",");
            if (splituri.length == 3) {
                int idCurs = Integer.parseInt(splituri[0]);
                int idStudent = Integer.parseInt(splituri[1]);
                int nota = Integer.parseInt(splituri[2]);
                noteData.add(new Nota(idCurs, idStudent, nota));
            }

        }
        br.close();
        return noteData;
    }

}
