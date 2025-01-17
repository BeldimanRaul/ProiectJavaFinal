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
        this.studenti = new HashSet<>();
        this.nota = new HashMap<>();
    }


    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
        System.out.println("Profesor setat pentru curs: " + nume + " - " + profesor.getNume());
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

    public  Set<Student> getStudenti() {
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

    public  List<Student> getStudentiInscrisi() {
        return new ArrayList<>(nota.keySet());
    }

    public void addProfesor(Profesor profesor) {
        this.profesor = profesor;
    }



    ///metoda temporara sa vad daca merg celelalte func de la student
    public void actualizeazaNota(Student student, Integer notaNoua) {

        if (notaNoua < 11||notaNoua==null) {
            if (nota.containsKey(student)) {
                List<Nota> listaNote = nota.get(student);
                Nota newNota = new Nota(this.id, student.getId(), notaNoua);
                listaNote.add(newNota);
                student.addNota(newNota);
                adaugaNotaInColectie(student, newNota);
            } else {
                System.out.println("Studentul nu este înscris la acest curs.");
            }
        }
        else{
            System.out.println("Pune o nota normala");
        }
    }
    private void adaugaNotaInColectie(Student student, Nota nota) {
        Consola.studentisinote.computeIfAbsent(student, k -> new ArrayList<>()).add(nota);
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