package com.example.proiectjavafinal;

import java.util.*;

public class Curs {
    int id;
    String nume;
    String descriere;
    Profesor profesor;
    Set<Student> studenti;
    Map<Student, List<Nota>> nota;
    int an;
    int idProfesor;
    int twest;

    public Curs(int an, int idProfesor, String descriere, String nume) {
        this.an = an;
        this.id = IdGenerator.idCurs();
        this.idProfesor = idProfesor;
        this.descriere = descriere;
        this.nume = nume;
        this.studenti = new HashSet<>(); // Initialize the studenti set
        this.nota = new HashMap<>(); // Initialize the nota map
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

    public Map<Student, List<Nota>> getNota() {
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
        if(!studenti.contains(student)) {
            studenti.add(student);
            nota.put(student, new ArrayList<>());
        }
    }

    public void stergeStudenti(Student student) {
        studenti.remove(student);
        nota.remove(student);
    }

    public List<Student> getStudentiInscrisi() {
        return new ArrayList<>(nota.keySet());
    }

    public void addProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public void actualizeazaNota(Student student, Integer notaNoua) {
        if (nota.containsKey(student)) {
            List<Nota> listaNote = nota.get(student);
            Nota newNota = new Nota(this.id, student.getId(), notaNoua);
            listaNote.add(newNota); // Add the new grade to the student's list of grades
            student.addNota(newNota); // Also add the grade to the student's own list
        } else {
            System.out.println("Studentul nu este inscris la acest curs.");
        }
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