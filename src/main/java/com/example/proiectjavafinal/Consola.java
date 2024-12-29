package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Consola {
    // Instanțe de obiecte necesare
    private static Scanner sc = new Scanner(System.in);
    private static ManagerCursuri mg = new ManagerCursuri();
    private static List<Student> studenti = new ArrayList<>();
    private static List<Profesor> profesori = new ArrayList<>();
    private static User utilizatorilogati = null;

    public static void main(String[] args) throws IOException {
        /// Creare cursuri și note de test
        List<Curs> cursuri = new ArrayList<>();
        List<Nota> note = new ArrayList<>();
        FileDisplay fd = new FileDisplay();
        FileDataManager cititorgen = new FileDataManager();

        /// Creare obiecte de test pentru profesori și studenți
        Profesor profesor = new Profesor(1, "Popescu", "Ion", "popescu.ion", "parola");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs de Matematică", "Matematică");
        cursuri.add(curs);
        curs.profesor = profesor;

        /// Creare studenti de test
        Student student = new Student(1, "Ionescu", "Maria", 2, "LF4731", "maria.ionescu@gmail.com", "parola");
        Student student2 = new Student(1, "Ionescu", "Maria", 3, "LF4731", "maria.ionescu@gmail.com", "parola");
        studenti.add(student);

        /// Adăugare studenți la curs și actualizare note de test
        curs.adaugareStudenti(student);
        curs.adaugareStudenti(student2);
        curs.actualizeazaNota(student, 10);
        curs.actualizeazaNota(student2, 10);
        note.add(new Nota(curs.getId(), student2.getId(), curs.getNota().get(student2)));
        note.add(new Nota(curs.getId(), student.getId(), curs.getNota().get(student)));

        // Interfața principală pentru utilizator
        ///SA FAC SA SE STEARGA ECRANUL DUPA FIECARE ITERARE INTELEGI TU.....
        while (true) {
            System.out.println("Selectati optiunea dorita:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Close");

            int optiune = sc.nextInt();
            sc.nextLine(); // Consumă newline-ul rămas

            switch (optiune) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    try {
                        fd.displayStudents(studenti);
                        fd.displayTeachers(profesori);
                    }
                    catch (IOException e) {
                        System.out.println("nu am scris in fisier ");
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Optiune invalida!");


            }
        }
    }

    // Metodă pentru generarea unui ID random/// vedem daca ramane
    public static int generateRandomId() {
        int BOUND = 1000000;
        Random random = new Random();
        return random.nextInt(BOUND);
    }

    // Metodă pentru login
    private static void login() {
        System.out.println("Username: ");
        String username = sc.nextLine();
        System.out.println("Password: ");
        String password = sc.nextLine();

        // Verificare utilizator existent
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

    // Metodă pentru înregistrare utilizator
    private static void register() {
        System.out.println("Tip utilizator: Student/Profesor");
        String userType = sc.nextLine();

        if (userType.equalsIgnoreCase("Student")) {
            // Înregistrare student
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
            sc.nextLine(); // Consumă newline-ul rămas
            System.out.println("În ce grupă ești?");
            String grupa = sc.nextLine();

            Student student = new Student(id, nume, prenume, an, grupa, username, password);
            studenti.add(student);

            System.out.println("Student inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);
        } else if (userType.equalsIgnoreCase("Profesor")) {
            // Înregistrare profesor
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:");///parola trebuie hashuita da vedem pe final
            String password = sc.nextLine();
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            int id = generateRandomId();///vedem ce mai facem cu id ul , nu stiu daca sa l tinem random
            System.out.println("În ce an predai?");
            int an = sc.nextInt();
            sc.nextLine(); // Consumă newline-ul rămas
            Profesor profesor = new Profesor(id, nume, prenume, username, password);
            profesori.add(profesor);

            System.out.println("Profesor inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);
        }
    }
}
