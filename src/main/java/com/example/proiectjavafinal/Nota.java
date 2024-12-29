package com.example.proiectjavafinal;

import java.util.Collection;

public class Nota {
    private int idCurs;
    private int idStudent;
    private Integer nota;


    public Nota(int idCurs, int idStudent, Integer nota) {
        this.idCurs = idCurs;
        this.idStudent = idStudent;
        this.nota = nota;


    }



    public int getIdCurs() {
        return idCurs;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public Integer getNota() {
        return nota;
    }


}
