package com.example.proiectjavafinal;

public class Profesor  {
    int id;
    String nume;
    String prenume;
    String username;
    String password;

    public Profesor(int id, String password, String username, String prenume, String nume) {

        this.id = id;
        this.prenume = prenume;
        this.nume = nume;
        this.password = password;
        this.username = username;

    }

    public int getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
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
