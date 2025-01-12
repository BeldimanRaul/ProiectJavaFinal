package com.example.proiectjavafinal;

public class ProfesorSession {
    private static Profesor profesorCurent;

    public static void setProfesorCurent(Profesor profesor) {
        profesorCurent = profesor;
    }

    public static Profesor getProfesorCurent() {
        return profesorCurent;
    }
}