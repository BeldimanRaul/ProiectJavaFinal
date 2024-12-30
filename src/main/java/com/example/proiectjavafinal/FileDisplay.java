package com.example.proiectjavafinal;
///SCRIERE
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class FileDisplay {
    String studentPath = "student.txt";
    String cursPath = "curs.txt";
    String profPath = "prof.txt";
    String notePath = "note.txt";

    public void displayStudents(Set<Student> studenti) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(studentPath));

        for (Student student : studenti) {
            w.write(student.getId() + "," +
                    student.getNume() + "," +
                    student.getPrenume() + "," +
                    student.getAn() + "," +
                    student.getGrupa() + "," +
                    student.getUsername() + "," +
                    student.getPassword());
            w.newLine();

        }
        w.close();
    }

    public void displayCurs(List<Curs> cursuri) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(cursPath));
        for (Curs curs : cursuri) {
            w.write(curs.getId() + "," +
                    curs.getNume() + "," +
                    curs.getDescriere() + "," +
                    curs.getIdProfesor());
            w.newLine();

        }
        w.close();
    }

    public void displayTeachers(List<Profesor> profesori) throws IOException {
        BufferedWriter w = new BufferedWriter(new FileWriter(profPath));
        for (Profesor profesor : profesori) {
            w.write(profesor.getId() + "," +
                    profesor.getNume() + "," +
                    profesor.getPrenume() + "," +
                    profesor.getUsername() + "," +
                    profesor.getPassword());
            w.newLine();


        }w.close();
    }
public void displayNote(List<Nota> note) throws IOException {
        BufferedWriter w= new BufferedWriter(new FileWriter(notePath));
        for (Nota nota : note) {
            w.write(nota.getIdCurs()+","+
                    nota.getIdStudent()+","+
                    nota.getNota());
            w.newLine();

        }
        w.close();
}

}

