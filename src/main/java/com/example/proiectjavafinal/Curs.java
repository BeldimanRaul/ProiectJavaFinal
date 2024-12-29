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
        this.nota = new HashMap<>();
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

    /// va fi folosita de profesor in interfata
    public void adaugareStudenti(Student student) {
        nota.put(student, null);


    }

    /// si asta la fel
    public void stergeStudenti(Student student) {
        nota.remove(student);
    }

    /// si asta doar de prof
    public void actualizeazaNota(Student student, int notaNoua) {
        if (notaNoua >= 1 && notaNoua <= 10) {
            if (!nota.containsKey(student)) {

            }

            nota.put(student, notaNoua);


        } else {
            System.out.println("introdu o nota intre 1 si 10 ");
        }
    }

    /// tot de prof va fi folosita
    public List<Student> getStudentiInscrisi() {
        return nota.keySet().stream().toList();
    }

    public void addProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
    ///TREBUIE SA FAC O FUNCTIE SA VAD NUMA NOTELE GEN PT STUDENTI GEN SA BAG UN STUDENT KEY SI SA VAD NOTA VALUE...
    ///
    ///


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
