package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<Curs> cursuri = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Student> studenti = new ArrayList<>();
        List<Nota> note = new ArrayList<>();
        FileDisplay fd = new FileDisplay();
        FileDataManager cititorgen= new FileDataManager();


        Profesor profesor = new Profesor(1, "Popescu", "Ion", "popescu.ion", "parola");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs de Matematică", "Matematică");
        cursuri.add(curs);
        curs.profesor = profesor;
        Student student = new Student(1, "Ionescu", "Maria", 2, "LF4731", "maria.ionescu@gmail.com", "parola");
        studenti.add(student);
        curs.adaugareStudenti(student);
        curs.actualizeazaNota(student, 10);
        note.add(new Nota(curs.getId(), student.getId(), curs.getNota().get(student)));

        try{
            fd.displayTeachers(profesori);
            fd.displayStudents(studenti);
            fd.displayCurs(cursuri);
            fd.displayNote(note);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}