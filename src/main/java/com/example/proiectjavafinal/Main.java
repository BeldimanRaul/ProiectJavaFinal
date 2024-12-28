package com.example.proiectjavafinal;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        FileDataManager fileDataManager = new FileDataManager();
        FileDisplay fileDisplay = new FileDisplay();


        List<Student> studenti = new ArrayList<>();
        List<Curs> cursuri = new ArrayList<>();
        List<Profesor> profesori = new ArrayList<>();
        List<Nota> note = new ArrayList<>();

//        studenti.add(new Student(1, "Popescu", "Ion", 3, "gfgfg", "ipopescu", "password1"));
//        studenti.add(new Student(2, "Ionescu", "Maria", 2, "ckbdcgyu", "mionescu", "password2"));
//        studenti.add(new Student(2, "test", "test", 2, "ckbdcgyu", "mionescu", "password2"));
//        cursuri.add(new Curs(1, 1, "Matematica", "Matematica Avansata"));
//        cursuri.add(new Curs(2, 2, "Informatica", "Introducere in Programare"));
//
//
//
//
//        profesori.add(new Profesor(1, "Vasilescu", "Andrei", "avasilescu", "password3"));
//        profesori.add(new Profesor(2, "Georgescu", "Elena", "egeorgescu", "password4"));
//
//        note.add(new Nota(1, 1, 10));
//        note.add(new Nota(2, 2, 9));
//
//         try {
//             fileDisplay.displayStudents(studenti);
//             fileDisplay.displayCurs(cursuri);
//             fileDisplay.displayTeachers(profesori);
//             fileDisplay.displayNote(note);
//
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         }


        Profesor profesor = new Profesor(1, "tetetetete", "Ittton", "popescu.ion", "parola");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs de Matemtttttttatică", "Matematică");
        cursuri.add(curs);
        curs.profesor = profesor;
        curs.studenti=new HashSet<>();
        curs.nota=new HashMap<>();
        Student student = new Student(1, "Ionttttttescu", "Marttttttttttia", 2, "LF4731", "maria.ionescu@gmail.com", "parola");
        studenti.add(student);
        curs.adaugareStudenti(student);
        curs.actualizeazaNota(student,10);


        try {
            fileDisplay.displayStudents(studenti);
            System.out.println("Informațiile despre studenți au fost scrise în fișier.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
       }
}
