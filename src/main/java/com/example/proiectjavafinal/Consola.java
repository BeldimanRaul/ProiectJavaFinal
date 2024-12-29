package com.example.proiectjavafinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.function.ToDoubleBiFunction;


public class Consola {
    private static Scanner sc = new Scanner(System.in);
    private static ManagerCursuri mg = new ManagerCursuri();
    private static List<User> studenti = new ArrayList<>();
    private static List<User> profesori = new ArrayList<>();
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
    public static int generateRandomId() {
        int BOUND = 1000000;
        Random random = new Random();
        return random.nextInt(BOUND);
    }

    private static void login() {
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();
        for (User user : studenti) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                utilizatorilogati = user;
                System.out.println("Login cu succes!");
                if (user instanceof Student) {
                    ((Student) user).dashboardSTD(mg.getCursuri());
                } else if (user instanceof Profesor) {
                    /// Metodă pentru dashboard-ul profesorului (de implementat)
                }
                return;
            }
        }
        System.out.println("Login nereusit!");
    }


    private static void register(){
        System.out.println("Tip utilizator: Student/Profesor");
        String userType=sc.nextLine();
        if(userType.equalsIgnoreCase("Student")){
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            int id = generateRandomId();
            System.out.println("În ce an ești?");
            int an = sc.nextInt();
            sc.nextLine();
            System.out.println("În ce grupă ești?");
            String grupa = sc.nextLine();
            Student student = new Student(id,nume, prenume, an, grupa, username, password);
            studenti.add(student);
            System.out.println("Student inregistrat cu succes!");
            System.out.println("id ul tau este , tine l minte:"+id);

        }else if (userType.equalsIgnoreCase("Profesor")){
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:");
            String password = sc.nextLine();
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            int id=generateRandomId();
            System.out.println("In ce An PREDAI:");
            int an=sc.nextInt();

        }

    }
    }

