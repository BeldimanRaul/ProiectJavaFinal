package com.example.proiectjavafinal;

import java.util.Random;

public class IdGenerator {
    public static  synchronized int idCurs() {
        int marginesus = 1000000;
        int marginejos=500000;
        Random random = new Random();
        return random.nextInt(marginesus-marginejos)+marginesus;
    }




}
