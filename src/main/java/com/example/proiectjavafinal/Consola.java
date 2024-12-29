package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Consola {
    private static Scanner sc = new Scanner(System.in);
    private static ManagerCursuri mg = new ManagerCursuri();
    private static List<User> useri = new ArrayList<>();
    private static User utilizatorilogati = null;


    {
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("Selectati optiunea dorita:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Close");


            int optiune = sc.nextInt();
            sc.nextLine();

            switch (optiune) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Optiune invalida!");
            }
        }
    }

    private static void login() {
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        for (User user : useri) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                utilizatorilogati = user;
                System.out.println("Login cu succes!");
                return;
            }
        }
        System.out.println("Login nereusit!");
    }

    private static void register() {
        String userType=sc.nextLine();
        System.out.println("Tip utilizator: Student/Profesor");
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();
        System.out.println("Nume:");
        String nume = sc.nextLine();
        System.out.println("Prenume:");
        String prenume = sc.nextLine();



        }
    }

