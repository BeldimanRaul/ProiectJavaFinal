package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ManagerCursuri {
    List<Curs> cursuri;
    HashMap<Integer, ArrayList<Student>>grupareaStudDupaAn;



    public void adaugareCursuri(Curs curs) {
        cursuri.add(curs);
    }
    public void stergereCursuri(Curs curs) {
        cursuri.remove(curs);
    }
}
