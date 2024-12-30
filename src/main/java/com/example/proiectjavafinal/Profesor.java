package com.example.proiectjavafinal;

import java.util.List;

public class Profesor extends User {
    int id;
    String nume;
    String prenume;


    public Profesor(int id, String password, String username, String prenume, String nume) {
        super(username, password);
        this.id = id;
        this.prenume = prenume;
        this.nume = nume;


    }

    public int getId() {
        return id;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public void dashboardProfesor(List<Curs> cursuri){

    }



    @Override
    public String toString() {
        return "Profesor{}";
    }
}
