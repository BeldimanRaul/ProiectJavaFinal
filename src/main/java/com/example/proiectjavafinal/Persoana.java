package com.example.proiectjavafinal;

public abstract class Persoana {
    private int id;
    private String nume;
    private String prenume;
    private String username;
    private String password;

    public Persoana(int id, String password, String username, String prenume, String nume) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.prenume = prenume;
        this.nume = nume;
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
        return "Persoana{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

