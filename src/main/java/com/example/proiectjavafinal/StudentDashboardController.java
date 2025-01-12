package com.example.proiectjavafinal;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class StudentDashboardController {

    private Student student;
    private ManagerCursuri managerCursuri;

    @FXML
    private TextField cursIdField;

    @FXML
    private TextArea outputArea;

    @FXML
    private Button vizualizeazaCursuriButton;

    @FXML
    private Button vizualizeazaNoteButton;

    @FXML
    private Button vizualizeazaMediaButton;

    @FXML
    private Button vizualizeazaRestanteButton;

    @FXML
    private Button inscrieLaCursButton;

    @FXML
    private Button cursuriValabileButton;

    @FXML
    public void initialize() {
        student = StudentSession.getStudentCurent();
        managerCursuri = ManagerCursuri.getInstance();

        System.out.println("Student curent: " + (student != null ? student.getId() : "null"));
        System.out.println("Cursuri disponibile: " + managerCursuri.getCursuri());
    }

    @FXML
    private void vizualizeazaCursuri() {
        outputArea.clear();
        List<Curs> cursuri = managerCursuri.getCursuri();
        if (cursuri == null || cursuri.isEmpty()) {
            outputArea.setText("Nu sunt cursuri înregistrate.");
            return;
        }

        StringBuilder output = new StringBuilder("Cursuri la care ești înscris:\n");
        boolean cursuriGasite = false;

        for (Curs curs : cursuri) {
            if (student.getIdinscrierecurs().contains(curs.getId())) {
                output.append("ID: ").append(curs.getId())
                        .append(", Nume: ").append(curs.getNume())
                        .append(", Descriere: ").append(curs.getDescriere()).append("\n");
                cursuriGasite = true;
            }
        }

        if (!cursuriGasite) {
            output.append("Nu ești înscris la niciun curs.");
        }

        outputArea.setText(output.toString());
    }

    @FXML
    private void vizualizeazaNote() {
        outputArea.clear();
        String curscautat = cursIdField.getText();
        boolean cursgasit = false;

        List<Curs> cursuri = managerCursuri.getCursuri();
        List<Nota> note = student.getNote();

        for (Curs curs : cursuri) {
            if (curscautat.equalsIgnoreCase(curs.getNume())) {
                cursgasit = true;
                boolean notagasita = false;

                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        notagasita = true;
                        outputArea.appendText("Nota ta este " + nota.getNota() + " la cursul " + curs.getNume() + "\n");
                    }
                }

                if (!notagasita) {
                    outputArea.setText("Nu ai încă note la acest curs.");
                }
                break;
            }
        }

        if (!cursgasit) {
            outputArea.setText("Cursul căutat nu există!");
        }
    }

    @FXML
    private void vizualizeazaMedia() {
        outputArea.clear();
        String curscautat = cursIdField.getText();
        boolean cursgasit = false;
        List<Nota> noteCurs = new ArrayList<>();
        List<Nota> note = student.getNote();

        List<Curs> cursuri = managerCursuri.getCursuri();
        for (Curs curs : cursuri) {
            if (curscautat.equalsIgnoreCase(curs.getNume())) {
                cursgasit = true;
                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        noteCurs.add(nota);
                    }
                }

                if (!noteCurs.isEmpty()) {
                    double medie = student.calculeazaMedia(noteCurs);
                    if (medie < 5) {
                        outputArea.setText("Ai restanță, media ta este " + medie + " la cursul " + curs.getNume());
                    } else {
                        outputArea.setText("Media ta la cursul " + curs.getNume() + " este " + medie);
                    }
                } else {
                    outputArea.setText("Nu ai note la cursul " + curs.getNume());
                }
                break;
            }
        }

        if (!cursgasit) {
            outputArea.setText("Cursul căutat nu există!");
        }
    }

    @FXML
    private void vizualizeazaRestante() {
        outputArea.clear();
        List<Curs> cursuri = managerCursuri.getCursuri();
        List<Nota> note = student.getNote();
        boolean cursurigasite = false;
        StringBuilder output = new StringBuilder();

        for (Curs curs : cursuri) {
            if (student.getIdinscrierecurs().contains(curs.getId())) {
                List<Nota> notecurs = new ArrayList<>();
                for (Nota nota : note) {
                    if (nota.getIdCurs() == curs.getId()) {
                        notecurs.add(nota);
                    }
                }
                if (!notecurs.isEmpty()) {
                    double media = student.calculeazaMedia(notecurs);
                    if (media < 5) {
                        output.append("Ești restant la cursul ").append(curs.getNume())
                                .append(", media ta este ").append(media).append("\n");
                        cursurigasite = true;
                    }
                }
            }
        }

        if (!cursurigasite) {
            output.append("Nu ai restante... încă.");
        }

        outputArea.setText(output.toString());
    }

    @FXML
    private void inscrieLaCurs() {
        try {
            int cursID = Integer.parseInt(cursIdField.getText());
            List<Curs> cursuri = managerCursuri.getCursuri();
            for (Curs curs : cursuri) {
                if (curs.getId() == cursID) {
                    if (curs.getAn() == student.getAn()) {
                        curs.adaugareStudenti(student);
                        student.getIdinscrierecurs().add(curs.getId());
                        outputArea.setText("Te-ai înscris cu succes la cursul " + curs.getNume());
                    } else {
                        outputArea.setText("Nu te poți înscrie la cursuri din alți ani.");
                    }
                    return;
                }
            }
            outputArea.setText("Cursul cu ID-ul " + cursID + " nu a fost găsit.");
        } catch (NumberFormatException e) {
            outputArea.setText("Introduceți un ID valid.");
        }
    }

    @FXML
    private void cursuriValabile() {
        outputArea.clear();
        List<Curs> cursuri = managerCursuri.getCursuri();
        int an = student.getAn();
        boolean cursurigasite = false;
        StringBuilder output = new StringBuilder("Cursuri valabile pentru anul " + an + ":\n");

        for (Curs curs : cursuri) {
            if (curs.getAn() == an) {
                cursurigasite = true;
                output.append("ID: ").append(curs.getId())
                        .append(", Nume: ").append(curs.getNume())
                        .append(", Descriere: ").append(curs.getDescriere()).append("\n");
            }
        }

        if (!cursurigasite) {
            output.append("Nu există cursuri valabile pentru anul " + an + ".");
        }

        outputArea.setText(output.toString());
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
