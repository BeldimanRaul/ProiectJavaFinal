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
        Student student1 = new Student(1, "Ionescu", "Maria", 1, "LF4731", "stud1", "parola");
        Student student2 = new Student(2, "Popescu", "George", 3, "LF4731", "stud2", "parola");
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

        /// Adăugare note pentru studenți
        curs.actualizeazaNota(student1, 10);
        curs.actualizeazaNota(student1, 10);
        curs3.actualizeazaNota(student1, 4);
        curs3.actualizeazaNota(student1, 4);
        curs3.actualizeazaNota(student2, 4);
        curs3.actualizeazaNota(student2, 7);


        try {
            fd.displayTeachers(profesori);
            fd.displayStudents(studentisinote.keySet());
            fd.displayCurs(mg.getCursuri());
            fd.displayNote(studentisinote.values());
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișierul de note: " + e.getMessage());
        }


        // Interfața principală pentru utilizator

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
        System.out.println("1.Student");
        System.out.println("2.Profesor");
        System.out.println("3.Close");
        int optiune = sc.nextInt();
        sc.nextLine();
        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();
        String parolahash = SecuritateParole.parolahashuita(password);
        if(optiune==3){
            return ;
        }
        if (optiune == 1) {
            for (User user : studentisinote.keySet()) {
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    utilizatorilogati = user;
                    System.out.println("Spor la invatat Studentule");
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        student.dashboardSTD();
                    }
                    return;
                }
            }
        } else if (optiune == 2) {
            for (Profesor profesor : profesori) {
                if (profesor.getUsername().equals(username) && profesor.getPassword().equals(password)) {
                    utilizatorilogati = profesor;
                    System.out.println("Bun venit domn Profesor");
                    profesor.dashboardProfesor();
                    return;
                }
            }


        } else {
            System.out.println("Optiune invalida!");
            return;
        }
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
            if (!ManagerUtilizatori.verificaUnicitateUsername(username)) {
                System.out.println("User existent , alege altul");
                return;
            }

            String password = sc.nextLine();
            String parolahaz = SecuritateParole.parolahashuita(password);
            System.out.println("Nume:");
            String nume = sc.nextLine();
            System.out.println("Prenume:");
            String prenume = sc.nextLine();
            //System.out.println("Introdu ID-ul: ");
            //int id = sc.nextInt();
            //sc.nextLine();
            System.out.println("În ce an ești?");
            int an = sc.nextInt();
            sc.nextLine();

            Student student = new Student(IdGenerator.idElev(), nume, prenume, an, "", username, password);
            ManagerUtilizatori.adaugaUtilizatori(student);//verific si eu unicitatea
            studentisinote.put(student, new ArrayList<>());
            System.out.println("Student inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + IdGenerator.idElev());
        } else if (userType.equalsIgnoreCase("Profesor")) {
            // Înregistrare profesor
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password:");

            String password = sc.nextLine();
            String parolahaz = SecuritateParole.parolahashuita(password);
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
            ManagerUtilizatori.adaugaUtilizatori(profesor);
            System.out.println("Profesor inregistrat cu succes!");
            System.out.println("ID-ul tau este: " + id);
        } else {
            System.out.println("Tip de utilizator invalid. Încercați din nou.");
        }
    }
}


