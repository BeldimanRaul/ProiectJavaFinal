package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.*;

public class Consola {

    private static Scanner sc = new Scanner(System.in);
    public static ManagerCursuri mg = new ManagerCursuri();
    public static Map<Student, List<Nota>> studentisinote = new HashMap<>();
    public static List<Profesor> profesori = new ArrayList<>();
    private static User utilizatorilogati = null;
    int test;

    public static void main(String[] args) throws IOException {


        FileDisplay fd = new FileDisplay();
        FileDataManager cititorgen = new FileDataManager();


        // Creare obiecte de test pentru profesori și studenți
        Profesor profesor = new Profesor(1, "parola", "iongm", "Ion", "Popescu");
        profesori.add(profesor);
        Curs curs = new Curs(1, 1, "Curs introductiv în algebră și geometrie", "Matematică");
        Curs curs2 = new Curs(1, 2, "Noțiuni de bază despre programare în Java", "Programare Java");
        Curs curs3 = new Curs(1, 3, "Istoria artei în perioada Renașterii", "Istoria Artei");
        Curs curs4 = new Curs(1, 4, "Bazele chimiei organice", "Chimie");
        Curs curs5 = new Curs(1, 5, "Introducere în psihologia comportamentală", "Psihologie");
        Curs curs6 = new Curs(1, 6, "Fizică aplicată pentru inginerie", "Fizică Aplicată");


        curs3.profesor = profesor;
        curs.profesor = profesor;

        /// Creare studenți de test
        Student student1 = new Student(1111, "Ionescu", "Maria", 1, "LF4731", "stud1", "parola");
        Student student2 = new Student(2111, "Popescu", "George", 1, "LF4731", "stud2", "parola");
///CURSUL 1
        mg.adaugareCursuri(curs);
        student1.inscrieLaCurs(curs.getId());
        student2.inscrieLaCurs(curs.getId());
        ///CURSUL 2
        mg.adaugareCursuri(curs2);

        ///CURSUL 3
        mg.adaugareCursuri(curs3);
        student1.inscrieLaCurs(curs3.getId());
        student2.inscrieLaCurs(curs3.getId());

        /// Adăugare studenți la cursuri
        curs.adaugareStudenti(student1);
        curs3.adaugareStudenti(student1);
        curs3.adaugareStudenti(student2);

        /// Adăugare note pentru studenți TEST
        curs.actualizeazaNota(student1, 10);
        curs.actualizeazaNota(student1, 10);
        curs3.actualizeazaNota(student1, 4);
        curs3.actualizeazaNota(student1, 4);
        curs3.actualizeazaNota(student2, 4);
        curs3.actualizeazaNota(student2, 7);

///SCRIE IN FISIER STUDENTII DE TEST
        try {
            fd.displayTeachers(profesori);
            fd.displayStudents(studentisinote.keySet());
            fd.displayCurs();
            fd.displayNote(studentisinote.values());
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul de note: " + e.getMessage());
        }


        // Interfața principală pentru utilizator in consola


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


    private static void login() throws IOException {
        System.out.println("Selectati tipul de utilizator");
        System.out.println("1. Student");
        System.out.println("2. Profesor");
        System.out.println("3. Close");

        int optiune = sc.nextInt();
        sc.nextLine();

        if (optiune == 3) {
            return;
        }

        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();
        String parolahaz = SecuritateParole.parolahashuita(password);
        if (autentificareUser(optiune, username, parolahaz)) {
            System.out.println("Autentificare reușită!");
        } else {
            System.out.println("Login nereușit! Verificați username-ul și parola.");
        }
    }

    private static boolean autentificareUser(int optiune, String username, String password) throws IOException {
        if (optiune == 1) {
            return autentificareStudent(username, password);
        } else if (optiune == 2) {
            return autentificareProfesor(username, password);
        } else {
            System.out.println("Optiune invalida!");
            return false;
        }
    }

    private static boolean autentificareStudent(String username, String password) {
        for (User user : studentisinote.keySet()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                utilizatorilogati = user;
                System.out.println("Spor la invatat Studentule");
                if (user instanceof Student) {
                    Student student = (Student) user;
                    student.dashboardSTD();
                }
                return true;
            }
        }
        return false;
    }

    private static boolean autentificareProfesor(String username, String password) throws IOException {
        for (Profesor profesor : profesori) {
            if (profesor.getUsername().equals(username) && profesor.getPassword().equals(password)) {
                utilizatorilogati = profesor;
                System.out.println("Bun venit domn Profesor");
                profesor.dashboardProfesor();
                return true;
            }
        }
        return false;
    }
    private static void register() {
        System.out.println("Tip utilizator: Student/Profesor");
        String userType = sc.nextLine().trim();

        if (userType.equalsIgnoreCase("Student")) {
            registerStudent();
        } else if (userType.equalsIgnoreCase("Profesor")) {
            registerProfesor();
        } else {
            System.out.println("Tip de utilizator invalid. Încercați din nou.");
        }
    }

    private static void registerStudent() {
        System.out.println("=== Inregistrare Student ===");

        String username = promptForInput("Username: ");
        if (!ManagerUtilizatori.verificaUnicitateUsername(username)) {
            System.out.println("Username-ul este deja folosit. Incercați altul.");
            return;
        }

        String password = promptForInput("Password: ");
        String parolahaz = SecuritateParole.parolahashuita(password);
        String nume = promptForInput("Nume: ");
        String prenume = promptForInput("Prenume: ");
        int an = promptForIntInput("În ce an ești? ");

        int id = IdGenerator.idElev();
        Student student = new Student(id, nume, prenume, an, "", username, parolahaz);
        ManagerUtilizatori.adaugaUtilizatori(student);
        studentisinote.put(student, new ArrayList<>());
        int iddoizeze=id+1;
        System.out.println("Student înregistrat cu succes! ID-ul tau este: " + iddoizeze);
    }

    private static void registerProfesor() {
        System.out.println("=== Inregistrare Profesor ===");

        String username = promptForInput("Username: ");
        String password = promptForInput("Password: ");
        String parolahaz = SecuritateParole.parolahashuita(password);
        String nume = promptForInput("Nume: ");
        String prenume = promptForInput("Prenume: ");
        int an = promptForIntInput("În ce an predai? ");

        int id = IdGenerator.idProfesor();
        Profesor profesor = new Profesor(id, nume, prenume, username, parolahaz);
        profesori.add(profesor);
        ManagerUtilizatori.adaugaUtilizatori(profesor);
        System.out.println("Profesor înregistrat cu succes! ID-ul tau este: " + id);
    }

    private static String promptForInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int promptForIntInput(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.println("Introduceți un număr valid.");
            sc.next();
            System.out.print(prompt);
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}


