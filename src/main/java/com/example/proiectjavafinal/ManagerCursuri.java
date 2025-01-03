package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ManagerCursuri {
   private static List<Curs> cursurii=new ArrayList<>();
    HashMap<Integer, ArrayList<Student>>grupareaStudDupaAn;

    public ManagerCursuri() {
        this.cursurii = new ArrayList<>();
    }

    public void adaugareCursuri(Curs curs) {
        this.cursurii.add(curs);
    }
    public void stergereCursuri(Curs curs) {
        cursurii.remove(curs);
    }

    public HashMap<Integer, ArrayList<Student>> getGrupareaStudDupaAn() {
        return grupareaStudDupaAn;
    }

    public List<Curs> getCursuri() {
        return cursurii;
    }

}
