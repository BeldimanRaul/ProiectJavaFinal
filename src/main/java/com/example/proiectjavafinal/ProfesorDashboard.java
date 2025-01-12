package com.example.proiectjavafinal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ProfesorDashboard {

    private int profesorId;
    private Profesor profesor;
    @FXML
    private TextField cursIDField;
    private Stage dialogStage;
    public ProfesorDashboard(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    private int getId() {
        return profesorId;

    }

    @FXML
    public void handleAfiseazaCursuriPredate(ActionEvent actionEvent) {
        List<Curs> cursuri = ManagerCursuri.getCursuri();
        StringBuilder cursuriPredate = new StringBuilder("Cursurile predate de dvs sunt:\n");

        for (Curs curs : cursuri) {
            if (curs.getIdProfesor() == this.getId()) {
                cursuriPredate.append("- ").append(curs.getNume()).append("\n");
            }
        }

        if (cursuriPredate.toString().equals("Cursurile predate de dvs sunt:\n")) {
            cursuriPredate.append("Nu aveți cursuri predate momentan.");
        }


        alerta("Cursuri Predate", cursuriPredate.toString());


    }

    @FXML
    private void afiseazaTotiStudentii() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("ID Curs");
        dialog.setHeaderText("Introduceți ID-ul cursului:");
        dialog.setContentText("ID Curs:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idCursStr -> {
            try {
                int idCurs = Integer.parseInt(idCursStr);
                List<Curs> cursuri = ManagerCursuri.getCursuri();

                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs && curs.getIdProfesor() == this.getId()) {
                        StringBuilder studenti = new StringBuilder("Lista studenților de la cursul ")
                                .append(curs.getNume()).append(":\n");

                        for (Student student : curs.getStudenti()) {
                            studenti.append("- ").append(student.getNume()).append(" ")
                                    .append(student.getPrenume()).append("\n");
                        }

                        alerta("Studenți la Curs", studenti.toString());
                        return;
                    }
                }
                alerta("Eroare", "Cursul nu a fost găsit sau nu este predat de dvs.");
            } catch (NumberFormatException e) {
                alerta("Eroare", "Introduceți un ID valid.");
            }
        });
    }
@FXML
    public void predalacurs(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("ID Curs");
        dialog.setHeaderText("Introduceți ID-ul cursului:");
        dialog.setContentText("ID Curs:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(idCursStr -> {
            try {
                int idCurs = Integer.parseInt(idCursStr);
                List<Curs> cursuri = ManagerCursuri.getCursuri();

                boolean cursGasit = false;
                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs) {
                        cursGasit = true;


                        if (curs.getProfesor() != null && curs.getProfesor().getId() != Profesor.getId()) {
                            System.out.println("Acest curs are deja un alt profesor asignat.");
                            return;
                        }


                        if (curs.getAn() == Profesor.getAn()) {
                            curs.setProfesor(this.getProfesor());
                            Profesor.getIdinscrierecursprof().add(curs.getId()); // Adăugare în lista de cursuri predate
                            System.out.println("V-ați înscris cu succes la cursul " + curs.getNume());
                        } else {
                            System.out.println("Nu puteți preda acest curs deoarece este destinat altui an: " + curs.getAn());
                        }
                        return;
                    }
                }

                if (!cursGasit) {
                    System.out.println("Cursul cu ID-ul " + idCurs + " nu a fost găsit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID-ul cursului trebuie să fie un număr valid.");
            }
        });
    }


    @FXML
    private void noteazaStudent() {
        TextInputDialog dialogCurs = new TextInputDialog();
        dialogCurs.setTitle("ID Curs");
        dialogCurs.setHeaderText("Introduceți ID-ul cursului:");
        dialogCurs.setContentText("ID Curs:");

        Optional<String> resultCurs = dialogCurs.showAndWait();
        resultCurs.ifPresent(idCursStr -> {
            try {
                int idCurs = Integer.parseInt(idCursStr);
                List<Curs> cursuri = ManagerCursuri.getCursuri();

                for (Curs curs : cursuri) {
                    if (curs.getId() == idCurs && curs.getIdProfesor() == this.getId()) {
                        TextInputDialog dialogStudent = new TextInputDialog();
                        dialogStudent.setTitle("ID Student");
                        dialogStudent.setHeaderText("Introduceți ID-ul studentului:");
                        dialogStudent.setContentText("ID Student:");

                        Optional<String> resultStudent = dialogStudent.showAndWait();
                        resultStudent.ifPresent(idStudentStr -> {
                            try {
                                int idStudent = Integer.parseInt(idStudentStr);

                                for (Student student : curs.getStudenti()) {
                                    if (student.getId() == idStudent) {
                                        TextInputDialog dialogNota = new TextInputDialog();
                                        dialogNota.setTitle("Notă");
                                        dialogNota.setHeaderText("Introduceți nota pentru student:");
                                        dialogNota.setContentText("Notă:");

                                        Optional<String> resultNota = dialogNota.showAndWait();
                                        resultNota.ifPresent(notaStr -> {
                                            try {
                                                int nota = Integer.parseInt(notaStr);
                                                curs.actualizeazaNota(student, nota);

                                                alerta("Succes", "Nota a fost adăugată cu succes!");
                                            } catch (NumberFormatException e) {
                                                alerta("Eroare", "Introduceți o notă validă.");
                                            }
                                        });
                                        return;
                                    }
                                }
                                alerta("Eroare", "Studentul nu a fost găsit la acest curs.");
                            } catch (NumberFormatException e) {
                                alerta("Eroare", "Introduceți un ID valid de student.");
                            }
                        });
                        return;
                    }
                }
                alerta("Eroare", "Cursul nu a fost găsit sau nu este predat de dvs.");
            } catch (NumberFormatException e) {
                alerta("Eroare", "Introduceți un ID valid de curs.");
            }
        });
    }


    private void alerta(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
