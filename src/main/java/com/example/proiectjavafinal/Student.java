package com.example.proiectjavafinal;

public class Student extends Persoana{

     String grupa;
     int an;

    public Student(int id, String password, String username, String prenume, String nume, String grupa, int an) {
        super(id, password, username, prenume, nume);
        this.grupa = grupa;
        this.an = an;
    }
    public String getGrupa() {
            return grupa;
    }
    public int getAn() {
        return an;
    }

    @Override
    public String toString() {
        return "Student{" +
                "grupa='" + grupa + '\'' +
                ", an=" + an +
                '}';
    }
}
