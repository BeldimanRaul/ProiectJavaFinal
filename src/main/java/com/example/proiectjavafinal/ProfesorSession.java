package com.example.proiectjavafinal;

public class ProfesorSession {
    private static Profesor profesorCurent;


    public static void setProfesorCurent(Profesor profesor) {
        profesorCurent = profesor;
        System.out.println("Profesor curent setat: " + profesor.getId() + " - " + profesor.getNume());
    }

    public static Profesor getProfesorCurent() {
        return profesorCurent;
    }
}