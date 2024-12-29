package com.example.proiectjavafinal;

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

    @Override
    public String toString() {
        return "Profesor{}";
    }
}
