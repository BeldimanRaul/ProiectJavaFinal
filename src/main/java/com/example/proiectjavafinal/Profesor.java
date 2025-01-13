package com.example.proiectjavafinal;

import java.io.IOException;
import java.util.*;




public class Profesor extends User {
    private String nume;
    private String prenume;
    private int an;
    int id;
    static Set<Integer>idinscrierecursprof;
    FileDisplay fd=new FileDisplay();


    public Profesor(String username, String password, String nume, String prenume, int an) {
        super(username, password);
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
        this.id = IdGenerator.idProfesor();
        this.idinscrierecursprof = new HashSet<>();
    }


    public String getNume() {
        return nume;
    }
    public static Set<Integer> getIdinscrierecursprof() {
        return idinscrierecursprof;}

    public int getId() {
        return id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void dashboardProfesor() throws IOException {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Afișează cursurile predate");
            System.out.println("2. Afișează lista de studenți pentru un curs selectat");
            System.out.println("3. Notează un student la un curs");
            System.out.println("4.Preda la un curs");
            System.out.println("5. Logout");
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
                    inscrieLaCursProfesor();
                        break;
                case 5:
                    System.out.println("Te-ai delogat. La revedere!");
                    return;
            }
        }
    }
    public void inscrieLaCursProfesor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceți ID-ul cursului la care doriți să vă înscrieți: ");
        int cursID = sc.nextInt();
        sc.nextLine();

        List<Curs> cursuri = ManagerCursuri.getCursuri();

        for (Curs curs : cursuri) {
            if (curs.getId() == cursID) {
                if (curs.getProfesor() != null && curs.getProfesor().getId() != this.id) {
                    System.out.println("Acest curs are deja un alt profesor asignat.");
                    return;
                }

                if (curs.getAn() == this.getAn()) {
                    curs.setProfesor(this);
                    idinscrierecursprof.add(curs.getId());
                    System.out.println("V-ați înscris cu succes la cursul " + curs.getNume());
                } else {
                    System.out.println("Nu puteți preda acest curs deoarece este destinat altui an: " + curs.getAn());
                }
                return;
            }
        }
        System.out.println("Cursul cu ID-ul " + cursID + " nu a fost găsit.");
    }



    void afiseazacursPredat() {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        System.out.println("Cursurile predate de dumneavoastră sunt: ");
        boolean existaCursuri = false;

        for (Curs curs : cursuri) {
            if (curs.getProfesor() != null && curs.getProfesor().getId() == this.id) {
                System.out.println("- " + curs.getNume());
                existaCursuri = true;
            }
        }

        if (!existaCursuri) {
            System.out.println("Nu predați niciun curs în acest moment.");
        }
    }


    private void afiseazatotistudentii() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceți ID-ul cursului: ");
        int idCurs = sc.nextInt();
        sc.nextLine(); // Consumăm newline-ul rămas

        List<Curs> cursuri = ManagerCursuri.getCursuri();

        for (Curs curs : cursuri) {
            if (curs.getId() == idCurs && curs.getProfesor() != null && curs.getProfesor().getId() == this.id) {
                System.out.println("Lista studenților de la cursul " + curs.getNume() + ": ");
                for (Student student : curs.getStudenti()) {
                    System.out.println("- " + student.getNume() + " " + student.getPrenume());
                }
                return;
            }
        }
        System.out.println("Cursul nu a fost găsit sau nu este predat de dumneavoastră.");
    }


    public void noteazastudent() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduceți ID-ul cursului: ");
        int idCurs = sc.nextInt();
        sc.nextLine(); // Consumăm newline-ul rămas

        List<Curs> cursuri = ManagerCursuri.getCursuri();
        Map<Student, List<Nota>> studentiSiNote = Consola.studentisinote;

        for (Curs curs : cursuri) {
            if (curs.getId() == idCurs && curs.getProfesor() != null && curs.getProfesor().getId() == this.id) {
                System.out.print("Introduceți ID-ul studentului: ");
                int idStudent = sc.nextInt();
                sc.nextLine();

                for (Student student : curs.getStudenti()) {
                    if (student.getId() == idStudent) {
                        System.out.print("Introduceți nota: ");
                        int nota = sc.nextInt();
                        sc.nextLine();

                        curs.actualizeazaNota(student, nota);
                        System.out.println("Nota a fost adăugată cu succes.");
                        fd.displayNote(studentiSiNote.values());
                        return;
                    }
                }
                System.out.println("Studentul nu a fost găsit la acest curs.");
                return;
            }
        }
        System.out.println("Cursul nu a fost găsit sau nu este predat de dumneavoastră.");
    }

    @Override
    public String toString() {
        return "Profesor{}";
    }
}
