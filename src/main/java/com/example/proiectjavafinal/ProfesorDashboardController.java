package com.example.proiectjavafinal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.List;

public class ProfesorDashboardController {

    private Profesor profesor;
    private ManagerCursuri managerCursuri;

    @FXML
    public void initialize() {
        profesor = ProfesorSession.getProfesorCurent();
        managerCursuri = ManagerCursuri.getInstance();

        System.out.println("Profesor curent: " + (profesor != null ? profesor.getId() : "null"));
        System.out.println("Cursuri manager: " + managerCursuri.getCursuri());
    }
    @FXML
    public void afiseazaCursuriSalvate() {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        StringBuilder message = new StringBuilder("Cursurile salvate sunt:\n");

        for (Curs curs : cursuri) {
            message.append("- ").append(curs.getNume()).append("\n");
        }

        if (cursuri.isEmpty()) {
            message.append("Nu există cursuri salvate.");
        }

        System.out.println(message);
    }


    @FXML
    private void afiseazaCursPredat    () {
        List<Curs> cursuri = managerCursuri.getCursuri();
        StringBuilder message = new StringBuilder("Cursurile predate de dumneavoastră sunt:\n");
        boolean existaCursuri = false;

        for (Curs curs : cursuri) {
            if (curs.getProfesor() != null && curs.getProfesor().getId() == profesor.getId()) {
                message.append("- ").append(curs.getNume()).append("\n");
                existaCursuri = true;
            }
        }

        if (!existaCursuri) {
            message.append("Nu predați niciun curs în acest moment.");
        }

        showAlert(Alert.AlertType.INFORMATION, "Cursuri Predate", message.toString());
    }

    @FXML
    private void afiseazaTotiStudentii() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introdu ID-ul cursului");
        dialog.setHeaderText(null);
        dialog.setContentText("ID-ul cursului:");

        dialog.showAndWait().ifPresent(idCursString -> {
            try {
                int idCurs = Integer.parseInt(idCursString);
                List<Curs> cursuri = managerCursuri.getCursuri();
                StringBuilder message = new StringBuilder();

                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs && curs.getProfesor() != null && curs.getProfesor().getId() == profesor.getId()) {
                        message.append("Lista studenților de la cursul ").append(curs.getNume()).append(":\n");
                        for (Student student : curs.getStudenti()) {
                            message.append("- ").append(student.getNume()).append(" ").append(student.getPrenume()).append("\n");
                        }
                        showAlert(Alert.AlertType.INFORMATION, "Lista Studenților", message.toString());
                        return;
                    }
                }
                showAlert(Alert.AlertType.ERROR, "Eroare", "Cursul nu a fost găsit sau nu este predat de dumneavoastră.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Eroare", "ID-ul cursului trebuie să fie un număr.");
            }
        });
    }

    @FXML
    private void noteazaStudent() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introdu ID-ul cursului");
        dialog.setHeaderText(null);
        dialog.setContentText("ID-ul cursului:");

        dialog.showAndWait().ifPresent(idCursString -> {
            try {
                int idCurs = Integer.parseInt(idCursString);
                List<Curs> cursuri = managerCursuri.getCursuri();

                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs && curs.getProfesor() != null && curs.getProfesor().getId() == profesor.getId()) {
                        TextInputDialog studentDialog = new TextInputDialog();
                        studentDialog.setTitle("Introdu ID-ul studentului");
                        studentDialog.setHeaderText(null);
                        studentDialog.setContentText("ID-ul studentului:");

                        studentDialog.showAndWait().ifPresent(idStudentString -> {
                            try {
                                int idStudent = Integer.parseInt(idStudentString);
                                for (Student student : curs.getStudenti()) {
                                    if (student.getId() == idStudent) {
                                        TextInputDialog notaDialog = new TextInputDialog();
                                        notaDialog.setTitle("Introdu Nota");
                                        notaDialog.setHeaderText(null);
                                        notaDialog.setContentText("Nota:");

                                        notaDialog.showAndWait().ifPresent(notaString -> {
                                            try {
                                                int nota = Integer.parseInt(notaString);
                                                curs.actualizeazaNota(student, nota);
                                                showAlert(Alert.AlertType.INFORMATION, "Succes", "Nota a fost adăugată cu succes.");
                                            } catch (NumberFormatException e) {
                                                showAlert(Alert.AlertType.ERROR, "Eroare", "Nota trebuie să fie un număr.");
                                            }
                                        });
                                    }
                                }
                            } catch (NumberFormatException e) {
                                showAlert(Alert.AlertType.ERROR, "Eroare", "ID-ul studentului trebuie să fie un număr.");
                            }
                        });
                        return;
                    }
                }
                showAlert(Alert.AlertType.ERROR, "Eroare", "Cursul nu a fost găsit sau nu este predat de dumneavoastră.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Eroare", "ID-ul cursului trebuie să fie un număr.");
            }
        });
    }
    @FXML
    private void handleBackButton(ActionEvent event) {
        try {
            Main main = new Main();
            main.schimba("prima-pagina.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void inscrieLaCursProfesor(ActionEvent actionEvent) {
        // Logica pentru înscrierea profesorului la un curs
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Introdu ID-ul cursului");
        dialog.setHeaderText(null);
        dialog.setContentText("ID-ul cursului:");

        dialog.showAndWait().ifPresent(idCursString -> {
            try {
                int cursID = Integer.parseInt(idCursString);
                List<Curs> cursuri = managerCursuri.getCursuri();

                for (Curs curs : cursuri) {
                    if (curs.getId() == cursID) {
                        if (curs.getProfesor() != null && curs.getProfesor().getId() != profesor.getId()) {
                            showAlert(Alert.AlertType.ERROR, "Eroare", "Acest curs are deja un alt profesor asignat.");
                            return;
                        }

                        if (curs.getAn() == profesor.getAn()) {
                            curs.setProfesor(profesor);
                            ProfesorSession.getProfesorCurent().getIdinscrierecursprof().add(curs.getId());
                            showAlert(Alert.AlertType.INFORMATION, "Succes", "V-ați înscris cu succes la cursul " + curs.getNume());
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Eroare", "Nu puteți preda acest curs deoarece este destinat altui an: " + curs.getAn());
                        }
                        return;
                    }
                }
                showAlert(Alert.AlertType.ERROR, "Eroare", "Cursul cu ID-ul " + cursID + " nu a fost găsit.");
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Eroare", "ID-ul cursului trebuie să fie un număr.");
            }
        });
    }
}

