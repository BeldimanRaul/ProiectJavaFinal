package com.example.proiectjavafinal;


import java.util.*;
import java.util.Scanner;

import static com.example.proiectjavafinal.Consola.studentisinote;

public class Student extends User {

    private int id;
    private String nume;
    private String prenume;
    private String grupa;
    private int an;
    private Set<Integer> idinscrierecurs;
    private List<Nota> note;


    public Student(int id, String nume, String prenume, int an, String grupa, String username, String password) {
        super(username, password);
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
        this.grupa = grupa;
        this.idinscrierecurs = new HashSet<>();
        this.note = new ArrayList<>();

    }

    // Getteri


    public Set<Integer> getIdinscrierecurs() {
        return idinscrierecurs;
    }

    public int getId() {
        return id;
    }

    public int getAn() {
        return an;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public void addNota(Nota nota) {
        this.note.add(nota);
    }


    /// Metoda dashboard pentru  utilizator
    public void dashboardSTD(List<Curs> cursuri) {
        Scanner scanner = new Scanner(System.in); // Instanțierea scannerului
        while (true) {
            System.out.println("\nDashboard Student:");
            System.out.println("1. Vizualizeaza Cursuri");
            System.out.println("2. Vizualizeaza Note");
            System.out.println("3. Vizualizeaza Media");
            System.out.println("4. Vizualizeaza Restante");
            System.out.println("5. Inscrie-te la un curs");
            System.out.println("6. Vezi cursuri valabile");
            System.out.println("7. Logout");

            System.out.print("Alege o opțiune: ");
            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    vizualizeazaCursuri();
                    break;
                case 2:
                    vizualizeazaNote();
                    break;
                case 3:
                    vizualizeazaMedia();
                    break;
                case 4:
                    vizualizeazaRestante();
                    break;
                case 5:
                    inscrieLaCurs(scanner.nextInt());
                    break;
                case 6:

                    cursvalabil();
                    break;
                case 7:
                    System.out.println("Te-ai delogat. La revedere!");

                    return;
                default:
                    System.out.println("Opțiune invalidă! Te rog să alegi din nou.");
            }
        }


    }

    public double calculeazaMedia(List<Nota> note) {

        double suma = 0;
        for (Nota nota : note) {
            suma += nota.getNota();
        }
        return suma / note.size();
    }

    public void vizualizeazaRestante() {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        List<Nota> note=this.note;
        boolean cursurigasite = false;
        for (Curs curs : cursuri) {
            if (idinscrierecurs.contains(curs.getId())) {
                List<Nota> notecurs = new ArrayList<>();
                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        notecurs.add(nota);
                    }
                }
                if (!notecurs.isEmpty()) {
                    double media = calculeazaMedia(notecurs);
                    if (media < 5) {
                        System.out.println("Esti restant la cursul " + curs.getNume() + "media ta este " + media);
                        cursurigasite = true;
                    }
                }
            }
        }
        if (!cursurigasite) {
            System.out.println("Nu ai restante...inca");
        }
    }

    public void vizualizeazaMedia() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        List<Nota> note=this.note;
        Scanner scanner = new Scanner(System.in);
        String curscautat = scanner.nextLine();
        boolean cursgasit = false;
        List<Nota> noteCurs = new ArrayList<>();
        for (Curs curs : cursurii) {
            if (curscautat.equalsIgnoreCase(curs.getNume())) {
                cursgasit = true;
                double suma = 0;
                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        noteCurs.add(nota);
                    }

                }
                if (!noteCurs.isEmpty()) {
                    double medie = calculeazaMedia(noteCurs);
                    if (medie < 5) {
                        System.out.println("Ai restanta puiule, media ta este " + medie);
                    } else if (medie >= 5) {
                        System.out.println("Media ta la cursul" + curs.getNume() + "este" + medie);
                    }
                } else {
                    System.out.println("Nu ai nota la cursul" + curs.getNume());
                }

                break;
            }

        }
        if (!cursgasit) {
            System.out.println("nu ai note");
        }

    }

    public void vizualizeazaNote() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        List<Nota> note=this.note;
        Scanner scanner = new Scanner(System.in);
        System.out.println("La ce curs vrei să îți vezi nota?");
        String curscautat = scanner.nextLine();
        boolean cursgasit = false;

        for (Curs curs : cursurii) {
            if (curscautat.equalsIgnoreCase(curs.getNume())) {
                cursgasit = true;
                boolean notagasita = false;

                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        notagasita = true;
                        System.out.println("Nota ta este " + nota.getNota() + " la cursul " + curs.getNume());
                    }
                }

                if (!notagasita) {
                    System.out.println("Nu ai încă note la acest curs.");
                }
                break;
            }
        }

        if (!cursgasit) {
            System.out.println("Cursul căutat nu există!");
        }
    }


    public void vizualizeazaCursuri() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        if (cursurii == null || cursurii.isEmpty()) {
            System.out.println("Nu sunt cursuri înregistrate.");
            return;
        }

        System.out.println("Cursuri la care ești înscris:");
        boolean cursuriGasite = false;
        for (Curs curs : cursurii) {
            if (idinscrierecurs.contains(curs.getId())) {
                System.out.println("ID: " + curs.getId() + ", Nume: " + curs.getNume() + ", Descriere: " + curs.getDescriere());
                cursuriGasite = true;
            }
        }

        if (!cursuriGasite) {
            System.out.println("Nu ești înscris la niciun curs.");
        }
    }

    public void inscrieLaCurs(int cursID) {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        for (Curs curs : cursurii) {
            if (curs.getId() == cursID) {
                if (curs.getAn() == this.getAn()) {
                    curs.adaugareStudenti(this);

                    idinscrierecurs.add(curs.getId());
                }
                else{
                    System.out.println("nu te poti inscrie la cursuri din alti ani");
                }

                return;
            }
        }
        System.out.println("Cursul cu ID-ul " + cursID + " nu a fost găsit.");
    }

    private void cursvalabil() {
        List<Curs> cursurii = ManagerCursuri.getCursuri();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdu anul pentru care doresti să vezi cursurile valabile:");
        int an = sc.nextInt();
        sc.nextLine();

        boolean cursurigasite = false;
        for (Curs curs : cursurii) {
            if (curs.getAn() == an) {
                cursurigasite = true;
                System.out.println("ID: " + curs.getId() + ", Nume: " + curs.getNume() + ", Descriere: " + curs.getDescriere());

            }
        }
        if (!cursurigasite) {
            System.out.println("Nu există cursuri valabile pentru anul " + an + ".");
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa='" + grupa + '\'' +
                ", an=" + an +
                '}';
    }
}



