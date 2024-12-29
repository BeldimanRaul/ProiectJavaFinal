package com.example.proiectjavafinal;


import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Student extends User {

    private int id;
    private String nume;
    private String prenume;
    private String grupa;
    private int an;


    public Student(int id, String nume, String prenume, int an, String grupa, String username, String password) {
        super(username, password);
        this.id =id;
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
        this.grupa = grupa;
    }

    // Getteri
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

    /// Metoda dashboard pentru  utilizator
    public void dashboardSTD(List<Curs>cursuri) {
        Scanner scanner = new Scanner(System.in); // Instanțierea scannerului
        while (true) {
            System.out.println("\nDashboard Student:");
            System.out.println("1. Vizualizeaza Cursuri");
            System.out.println("2. Vizualizeaza Note");
            System.out.println("3. Vizualizeaza Media");
            System.out.println("4. Vizualizeaza Restante");
            System.out.println("5. Logout");

            System.out.print("Alege o opțiune: ");
            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 1:
                    vizualizeazaCursuri(cursuri);
                    break;
                case 2:
                     vizualizeazaNote();
                   break;
                case 3:
                    //vizualizeazaMedia();
                  break;
                case 4:
                    // vizualizeazaRestante();
                 break;
                case 5:
                    System.out.println("Te-ai delogat. La revedere!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opțiune invalidă! Te rog să alegi din nou.");
            }
        }



    }
    ///METODE PT PRIMA OPTIUNE
    public int alegean(){
        System.out.println("alege an pt curs");
        Scanner scanner = new Scanner(System.in);
        int anAles=scanner.nextInt();
        scanner.nextLine();
        return anAles;
    }
    public void vizualizeazaCursuri(List<Curs>cursuri){
        if(cursuri!=null){
        int anAles=alegean();
        if (anAles!=this.an){
            System.out.println("Nu sunteți în anul " + anAles + ". Sunteți în anul " + this.an + ".");
            return;

        }System.out.println("Cursuri înscrise pentru anul " + anAles + ":");
        for (Curs curs : cursuri) {
            if(curs.getAn()==anAles&&curs.getStudenti().contains(this)){
                System.out.println("ID: " + curs.getId() + ", Nume: " + curs.getNume() + ", Descriere: " + curs.getDescriere());
            }
        }
    }
        System.out.println("nu sunt cursuri inregistrate");
    }

    public void vizualizeazaNote(){

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
