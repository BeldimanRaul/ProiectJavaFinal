package com.example.proiectjavafinal;


import java.util.*;

public class Student extends User {

    private int id;
    private String nume;
    private String prenume;
    private String grupa;
    private int an;
    private Set<Integer> idinscrierecurs;
    private List<Nota>note;


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
        this.note.add(nota); // Method to add a note to the list
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
                    vizualizeazaCursuri(cursuri);
                    break;
                case 2:
                    //vizualizeazaNote();
                    break;
                case 3:
                    //vizualizeazaMedia();
                    break;
                case 4:
                    // vizualizeazaRestante();
                    break;
                case 5:
                    inscrieLaCurs(cursuri, scanner.nextInt());
                    break;
                case 6:

                    cursvalabil(cursuri);
                    break;
                case 7:
                    System.out.println("Te-ai delogat. La revedere!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opțiune invalidă! Te rog să alegi din nou.");
            }
        }


    }


    public void vizualizeazaNote(List<Nota> note) {
        if (!note.isEmpty()) {
            for (Nota nota : note) {
                System.out.println("nota ta este" + nota.getNota() + "la cursul" + nota.getIdCurs());
            }
        }
        System.out.println("nu ai inca note");
    }

    public void inscrielacurs(int cursId) {
        idinscrierecurs.add(cursId);
    }

    public void vizualizeazaCursuri(List<Curs> cursuri) {
        if (cursuri == null || cursuri.isEmpty()) {
            System.out.println("Nu sunt cursuri înregistrate.");
            return;
        }

        System.out.println("Cursuri la care ești înscris:");
        boolean cursuriGasite = false;
        for (Curs curs : cursuri) {
            if (idinscrierecurs.contains(curs.getId())) {
                System.out.println("ID: " + curs.getId() + ", Nume: " + curs.getNume() + ", Descriere: " + curs.getDescriere());
                cursuriGasite = true;
            }
        }

        if (!cursuriGasite) {
            System.out.println("Nu ești înscris la niciun curs.");
        }
    }


    public void inscrieLaCurs(List<Curs> cursuri, int cursID) {
        for (Curs curs : cursuri) {
            if (curs.getId() == cursID) {
                curs.adaugareStudenti(this);
                System.out.println("Te-ai înscris cu succes la cursul: " + curs.getNume());
                idinscrierecurs.add(curs.getId());
                System.out.println("id bagat in lista");
                return;
            }
        }
        System.out.println("Cursul cu ID-ul " + cursID + " nu a fost găsit.");
    }

    private void cursvalabil(List<Curs> cursuri) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdu anul pentru care doresti să vezi cursurile valabile:");
        int an = sc.nextInt();
        sc.nextLine();

        boolean cursurigasite = false;
        for (Curs curs : cursuri) {
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



