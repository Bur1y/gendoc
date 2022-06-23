package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.Data.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

import static com.nrtk.bur1y.gendoc.JavaFX.loadFXML;

public class ResultExamController {


    public TableColumn<Student, String> fio, test, task;
    public TableView<Student> viewTable;

    @FXML
    public void initialize() {

        List<Student> studentList = MainController.sGroup.studentList;

        fio.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().fio));
        });

        test.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().test));
        });

        task.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().task));
        });


        ObservableList<Student> students = FXCollections.observableList(studentList);
        viewTable.setItems(students);
    }

    public static Stage editE;

    public static SelectionModel<Student> selectionStudent;

    public void edit(ActionEvent actionEvent) {
        try {
            selectionStudent = viewTable.getSelectionModel();
            editE = new Stage();
            editE.initOwner(MainController.resultP);
            editE.setTitle("Редактирование");
            editE.setScene(new Scene(loadFXML("EditExam"), 800, 500));
            editE.initModality(Modality.APPLICATION_MODAL);
            editE.showAndWait();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.errorAlert("Ошибка редактирования", "Ошибка выбора элемента", "Выберите студента которого хотите изменить");
        }
    }
}
