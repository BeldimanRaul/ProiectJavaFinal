package com.example.proiectjavafinal;

import java.util.Random;

public class IdGenerator {
    public static  synchronized int idCurs() {
        int marginesus = 999;
        int marginejos=50;
        Random random = new Random();
        return random.nextInt(marginesus-marginejos)+marginesus;
    }




}
