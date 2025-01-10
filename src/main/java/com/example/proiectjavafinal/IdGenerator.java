package com.example.proiectjavafinal;

import java.util.Random;

public class IdGenerator {

    private static int currentIDelev = 1;
    private static int currentIdprof = 1;
    public static  synchronized int idCurs() {
        int marginesus = 999;
        int marginejos=50;
        Random random = new Random();
        return random.nextInt(marginesus-marginejos)+marginesus;
    }
public static  int idElev(){
   return currentIDelev++;

}
public static  int idProfesor(){

        return currentIdprof++;
}



}
