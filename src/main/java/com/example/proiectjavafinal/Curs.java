package com.example.proiectjavafinal;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Curs {
    int id;
    String nume;
    String descriere;
    Profesor profesor;
    Set<Student> studenti;
    HashMap<Student, Integer> nota;
    int an;
    int idProfesor;

    public Curs(int id, int idProfesor, String descriere, String nume) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.descriere = descriere;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public int getAn() {
        return an;
    }

    public HashMap<Student, Integer> getNota() {
        return nota;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public String getDescriere() {
        return descriere;
    }

    public String getNume() {
        return nume;
    }

    public void adaugareStudenti(Student student) {
        nota.put(student, null);

    }
    public void stergeStudenti(Student student) {
        nota.remove(student);
    }

    public void actualizeazaNota(Student student, int notaNoua) {
        if (nota.containsKey(student)) {
            System.out.println("Studentul nu este înscris la acest curs.");
        }

        nota.put(student, notaNoua);

    }

    public List<Student>getStudentiInscrisi(){
        return nota.keySet().stream().toList();
    }


    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", descriere='" + descriere + '\'' +
                ", profesor=" + profesor +
                ", studenti=" + studenti +
                ", nota=" + nota +
                ", an=" + an +
                ", idProfesor=" + idProfesor +
                '}';
    }
}
