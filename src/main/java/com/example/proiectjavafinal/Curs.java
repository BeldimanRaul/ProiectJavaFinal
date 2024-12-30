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

    public Curs(int an, int idProfesor, String descriere, String nume) {
        this.an = an;
        this.id = IdGenerator.idCurs();
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

    /// va fi folosita de profesor in interfata
    public void adaugareStudenti(Student student) {
        if(!studenti.contains(student)) {
            studenti.add(student);
            nota.put(student, new ArrayList<>());
        }


    }

    /// si asta la fel
    public void stergeStudenti(Student student) {
        nota.remove(student);
    }

    /// si asta doar de prof



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
