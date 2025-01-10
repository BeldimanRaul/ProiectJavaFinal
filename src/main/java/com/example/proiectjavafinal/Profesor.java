package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Profesor extends User {
    int id;
    String nume;
    String prenume;
FileDisplay fd=new FileDisplay();

    public Profesor(int id, String password, String username, String prenume, String nume) {
        super(username, password);
        this.id = id;
        this.prenume = prenume;
        this.nume = nume;


    }

    public int getId() {
        return id;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public void dashboardProfesor() throws IOException {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Afișează cursurile predate");
            System.out.println("2. Afișează lista de studenți pentru un curs selectat");
            System.out.println("3. Notează un student la un curs");
            System.out.println("4. Logout");
            System.out.println("Selectați opțiunea dorită:");
            int optiune = sc.nextInt();
            sc.nextLine();
            switch (optiune) {
                case 1:
                    afiseazacursPredat();
                    break;
                case 2:
                    afiseazatotistudentii();
                    break;
                case 3:
                    noteazastudent();
                    break;
                case 4:
                    System.out.println("Te-ai delogat. La revedere!");
                    return;
            }
        }
    }


    private void afiseazacursPredat() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        System.out.println("Cursurile predate de dvs sunt: ");
        for (Curs curs : cursurii) {
            if (curs.getIdProfesor() == this.getId()) {
                System.out.println(curs.getNume());
            }
        }
    }

    private void afiseazatotistudentii() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        Scanner sc = new Scanner(System.in);
        int idCurs = sc.nextInt();
        sc.nextLine();
        for (Curs curs : cursurii) {
            if (curs.getId() == idCurs && curs.getIdProfesor() == this.getId()) {
                System.out.println("Lista studentilor de la cursul " + curs.getNume() + " este: ");
                for (Student student : curs.getStudenti()) {
                    System.out.println(student.getNume() + " " + student.getPrenume());
                }
                return;
            }
        }
        System.out.println("Cursul nu a fost gasit :(");
    }

    private void noteazastudent() throws IOException {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        Map<Student, List<Nota>> studentisinote = Consola.studentisinote;
        System.out.println("Introdu id ul cursului:");

        Scanner sc = new Scanner(System.in);
        int idCurs=sc.nextInt();
        sc.nextLine();
        for(Curs curs : cursurii) {
            if (curs.getId() == idCurs&&curs.getIdProfesor() == this.getId()) {
                System.out.println("Introdu id student: ");
                int idStudent=sc.nextInt();
                sc.nextLine();
                for (Student student : curs.getStudenti()) {
                    if (student.getId() == idStudent) {
                        System.out.println("Pune nota: ");
                        int nota=sc.nextInt();
                        sc.nextLine();
                        curs.actualizeazaNota(student,nota);
                        System.out.println("Nota adaugata cu succes (vezi note.txt)");
                        fd.displayNote(studentisinote.values());

                    }
                } System.out.println("Studentul nu a fost găsit la acest curs.");
                return;
            }System.out.println("Cursul nu a fost găsit sau nu este predat de dumneavoastră.");
        }
    }

    @Override
    public String toString() {
        return "Profesor{}";
    }
}
