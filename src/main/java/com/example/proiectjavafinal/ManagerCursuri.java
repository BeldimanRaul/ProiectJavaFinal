package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ManagerCursuri {
    List<Curs> cursuri;
    HashMap<Integer, ArrayList<Student>>grupareaStudDupaAn;

    public ManagerCursuri(HashMap<Integer, ArrayList<Student>> grupareaStudDupaAn, List<Curs> cursuri) {
        this.grupareaStudDupaAn = grupareaStudDupaAn;
        this.cursuri = cursuri;
    }

    public void adaugareCursuri(Curs curs) {
        cursuri.add(curs);
    }
    public void stergereCursuri(Curs curs) {
        cursuri.remove(curs);
    }
}
