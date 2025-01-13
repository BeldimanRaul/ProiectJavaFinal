package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.Scanner;

public class Threaduri {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selectati modul de utilizare:");
        System.out.println("1. Consola");
        System.out.println("2. GUI");
        System.out.print("Optiunea dvs: ");

        int optiune = scanner.nextInt();
        scanner.nextLine();

        switch (optiune) {
            case 1:
              ///Consola
                Thread consolaThread = new Thread(() -> {
                    try {
                        Consola.main(args);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                consolaThread.start();
                break;

            case 2:
                /// GUI
                Thread mainThread = new Thread(() -> Main.main(args));
                mainThread.start();
                break;

            default:
                System.out.println("Optiune invalida!");
                break;
        }


    }
}
