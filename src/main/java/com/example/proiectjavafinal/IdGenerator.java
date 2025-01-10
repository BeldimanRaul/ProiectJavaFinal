package com.example.proiectjavafinal;

import java.util.Random;

public class IdGenerator {

    private static int currentID = 0;
    public static  synchronized int idCurs() {
        int marginesus = 999;
        int marginejos=50;
        Random random = new Random();
        return random.nextInt(marginesus-marginejos)+marginesus;
    }
public static synchronized int idElev(){
    currentID++; // Increment the ID
    return currentID;

}



}
