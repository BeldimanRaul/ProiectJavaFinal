package com.example.proiectjavafinal;

public class Student  {
    int id;
    String nume;
    String prenume;
    String username;
    String password;
    String grupa;
    int an;

    public Student(int id, String nume, String prenume, int an, String grupa, String username, String password) {
        super();
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
        this.grupa = grupa;
        this.username = username;
        this.password = password;

    }

    public int getId() {
        return id;
    }

    public int getAn() {
        return an;
    }

    public String getGrupa() {
        return grupa;
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
        return "Student{" +
                "grupa='" + grupa + '\'' +
                ", an=" + an +
                '}';
    }
}
