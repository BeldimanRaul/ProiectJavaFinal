package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.List;

public class ManagerCursuri {
    private static List<Curs> cursurii = new ArrayList<>();

    private static ManagerCursuri instance;

    ManagerCursuri() {}
///sa se foloseasca aceiasi lista si pt gui si pt consola
    public static synchronized ManagerCursuri getInstance() {
        if (instance == null) {
            instance = new ManagerCursuri();
        }
        return instance;
    }

    public static List<Curs> getCursuri() {
        return cursurii;
    }

    public static void adaugareCursuri(Curs curs) {
        cursurii.add(curs);
    }
}
