package com.example.proiectjavafinal;

public class Profesor extends Persoana {
    public Profesor(int id, String nume, String prenume, String username, String password) {
        super(id, nume, prenume, username, password);
    }

    @Override
    public String toString() {
        return "Profesor{}";
    }
}
