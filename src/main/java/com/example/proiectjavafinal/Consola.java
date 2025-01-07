package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.*;

public class Consola {

    private static Scanner sc = new Scanner(System.in);
    public static ManagerCursuri mg = new ManagerCursuri();
    public static Map<Student, List<Nota>> studentisinote = new HashMap<>();
    public static List<Profesor> profesori = new ArrayList<>();
    private static User utilizatorilogati = null;

    public static void main(String[] args) throws IOException {


        FileDisplay fd = new FileDisplay();
        FileDataManager cititorgen = new FileDataManager();



        // Creare obiecte de test pentru profesori și studenți
        Profesor profesor = new Profesor(1, "Popescu", "Ion", "popescu.ion", "parola");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs de Matematică", "Matematica");
        Curs curs2 = new Curs(1, 1, "peul;ala", "mamsmsnjdsnbdbd");
        Curs curs3 = new Curs(1, 1, "Curs de Matematică22222", "Matematica22");

        curs3.profesor = profesor;
        curs.profesor = profesor;

        // Creare studenți de test
        Student student1 = new Student(1, "Ionescu", "Maria", 2, "LF4731", "pipi", "parola");
        Student student2 = new Student(2, "Popescu", "George", 3, "LF4731", "caca", "parola");

        mg.adaugareCursuri(curs);
        student1.inscrieLaCurs(curs.getId());
        mg.adaugareCursuri(curs2);
        mg.adaugareCursuri(curs3);
        student1.inscrieLaCurs(curs3.getId());
        student2.inscrieLaCurs(curs3.getId());

        // Adăugare studenți la cursuri
        curs.adaugareStudenti(student1);
        curs3.adaugareStudenti(student1);
        curs3.adaugareStudenti(student2);

        // Adăugare note pentru studenți
        curs.actualizeazaNota(student1, 10);
        curs.actualizeazaNota(student1, 10);
        curs3.actualizeazaNota(student1, 9);
        curs3.actualizeazaNota(student1, 7);




        try {
            fd.displayTeachers(profesori);
            fd.displayStudents(studentisinote.keySet());
            fd.displayCurs(mg.getCursuri());
            fd.displayNote(studentisinote.values());
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul de note: " + e.getMessage());
        }




        // Interfața principală pentru utilizator
        ///SA FAC SA SE STEARGA ECRANUL DUPA FIECARE ITERARE INTELEGI TU.....
        while (true) {

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Close");
            System.out.println("Selectati optiunea dorita:");
           int optiune = sc.nextInt();
           sc.nextLine();

            switch (optiune) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    try {
                        fd.displayStudents(studentisinote.keySet());
                        fd.displayTeachers(profesori);
                        fd.displayNote(studentisinote.values());
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


    private static void login() {
        if (studentisinote.keySet() == null || studentisinote.keySet().isEmpty()) {
            System.out.println("Nu există useri înregistrați.");
            return;
        }

        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();

        /// Verificare utilizator existent
        for (User user : studentisinote.keySet()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                utilizatorilogati = user;
                System.out.println("Login cu succes!");


                if (user instanceof Student) {
                    Student student = (Student) user;
                    student.dashboardSTD(mg.getCursuri()); /// Dashboard pentru studenți
                } else if (user instanceof Profesor) {
                    Profesor profesor = (Profesor) user;
                    profesor.dashboardProfesor(mg.getCursuri()); /// Dashboard pentru profesori (de implementat)
                }
                return;
            }
        }

        // Mesaj în caz de date greșite
        System.out.println("Login nereușit! Verificați username-ul și parola.");
    }

    private static void register() {
        System.out.println("Tip utilizator: Student/Profesor");
        String userType = sc.nextLine();

        if (userType.equalsIgnoreCase("Student")) {
            // Înregistrare student
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:");
            if(!ManagerUtilizatori.verificaUnicitateUsername(username)) {
                System.out.println("User existent , alege altul");
                return;
            }
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
            studentisinote.put(student, new ArrayList<>());

            System.out.println("Student inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);///fa ceva cu id ul
        } else if (userType.equalsIgnoreCase("Profesor")) {
            // Înregistrare profesor
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:"); /// parola trebuie hashuita ( . Y . )
            String password = sc.nextLine();
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            System.out.println("Introdu ID-ul: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("În ce an predai?");
            int an = sc.nextInt();
            sc.nextLine();
            Profesor profesor = new Profesor(id, nume, prenume, username, password);
            profesori.add(profesor);

            System.out.println("Profesor inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);
        }
    }
}


