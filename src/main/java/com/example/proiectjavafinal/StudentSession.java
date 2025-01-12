package com.example.proiectjavafinal;

public class StudentSession {
    private static Student studentCurent;

    public static void setStudentCurent(Student student) {
        studentCurent = student;
        System.out.println("Student curent setat: " + student.getId() + " - " + student.getNume());
    }

    public static Student getStudentCurent() {
        return studentCurent;
    }
}
