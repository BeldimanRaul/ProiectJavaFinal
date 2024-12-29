package com.example.proiectjavafinal;

import java.util.Scanner;

public class Student extends User {
    int id;
    String nume;
    String prenume;
    String grupa;
    int an;
    private static Scanner scanner=new Scanner(System.in);

    public Student(int id, String nume, String prenume, int an, String grupa, String username, String password) {
        super(username, password);
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.an = an;
        this.grupa = grupa;


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

   public void dashboardSTD() {
       while (true) {
           System.out.println("Dashboard Student:");
           System.out.println("1. Vizualizeaza Cursuri");
           System.out.println("2. Vizualizeaza Note");
           System.out.println("3. Vizualizeaza Media");
           System.out.println("4. Vizualizeaza Restante");
           System.out.println("5. Logout");

           int option = scanner.nextInt();
           scanner.nextLine();

           switch (option) {
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
                   return;
               default:
                   System.out.println("Optiune invalida!");
           }
       }
   }

}
//    @Override
//    public String toString() {
//        return "Student{" +
//                "grupa='" + grupa + '\'' +
//                ", an=" + an +
//                '}';
//    }
//}
