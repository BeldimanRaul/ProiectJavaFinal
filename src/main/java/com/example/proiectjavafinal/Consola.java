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
        List<Curs> cursuri = new ArrayList<>();
        List<Nota> note = new ArrayList<>();
        FileDisplay fd = new FileDisplay();
        FileDataManager cititorgen = new FileDataManager();

        /// Creare obiecte de test pentru profesori și studenți
        Profesor profesor = new Profesor(1, "Popescu", "Ion", "popescu.ion", "parola");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs de Matematică", "Matematică");
        mg.adaugareCursuri(curs);
        curs.profesor = profesor;

        /// Creare studenti de test
        Student student = new Student(1, "Ionescu", "Maria", 2, "LF4731", "maria", "parola");
        Student student2 = new Student(1, "Ionescu", "Maria", 3, "LF4731", "maria.ionescu@gmail.com", "parola");
        studenti.add(student);

        /// Adăugare studenți la curs și actualizare note de test
        curs.adaugareStudenti(student);
        curs.adaugareStudenti(student2);
        curs.actualizeazaNota(student, 10);
        curs.actualizeazaNota(student2, 10);
        //note.add(new Nota(curs.getId(), student2.getId(), curs.getNota().get(student2)));
        //note.add(new Nota(curs.getId(), student.getId(), curs.getNota().get(student)));


        try {
            fd.displayTeachers(profesori);
            fd.displayStudents(studenti);
            fd.displayCurs(cursuri);
            fd.displayNote(note);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


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
                    register(cursuri);
                    try {
                        fd.displayStudents(studenti);
                        fd.displayTeachers(profesori);
                    } catch (IOException e) {
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


    // Metodă pentru login
    private static void login() {
        if (studenti == null || studenti.isEmpty()) {
            System.out.println("Nu există utilizatori înregistrați.");
            return;
        }

        System.out.print("Username: ");
        String username = sc.nextLine().trim(); // Elimină spațiile albe
        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        // Verificare utilizator existent
        for (User user : studenti) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                utilizatorilogati = user;
                System.out.println("Login cu succes!");

                // Verificare tip utilizator și apelare dashboard
                if (user instanceof Student) {
                    Student student = (Student) user;
                    student.dashboardSTD(mg.getCursuri()); // Dashboard pentru studenți
                } else if (user instanceof Profesor) {
                    Profesor profesor = (Profesor) user;
                    //profesor.dashboardProfesor(mg.getCursuri()); // Dashboard pentru profesori (de implementat)
                }
                return;
            }
        }

        // Mesaj în caz de date greșite
        System.out.println("Login nereușit! Verificați username-ul și parola.");
    }


    private static void register(List<Curs> cursuri) {
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
            System.out.println("Introdu ID-ul: ");
            int id = sc.nextInt(); // Solicită introducerea ID-ului
            sc.nextLine(); // Consumă newline-ul rămas
            System.out.println("În ce an ești?");
            int an = sc.nextInt();
            sc.nextLine(); // Consumă newline-ul rămas

            Student student = new Student(id, nume, prenume, an, "", username, password);
            studenti.add(student);

            System.out.println("Student inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);
        } else if (userType.equalsIgnoreCase("Profesor")) {
            // Înregistrare profesor
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:"); // parola trebuie hashuita
            String password = sc.nextLine();
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            System.out.println("Introdu ID-ul: ");
            int id = sc.nextInt(); // Solicită introducerea ID-ului
            sc.nextLine(); // Consumă newline-ul rămas
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


