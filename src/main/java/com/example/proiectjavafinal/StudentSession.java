package com.example.proiectjavafinal;

public class StudentSession {
    private static Student studentCurent;

    public static void setStudentCurent(Student student) {
        studentCurent = student;
    }

    public static Student getStudentCurent() {
        return studentCurent;
    }
}