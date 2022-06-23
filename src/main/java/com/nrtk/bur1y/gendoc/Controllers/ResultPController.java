package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.Data.Student;
import com.nrtk.bur1y.gendoc.JavaFX;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.List;

import static com.nrtk.bur1y.gendoc.JavaFX.loadFXML;

public class ResultPController {

    public TableColumn<Student, String> fio, mdk01, mdk02, mdk03, kp, up, pp;
    public TableView<Student> viewTable;

    @FXML
    public void initialize() {

        List<Student> studentList = MainController.sGroup.studentList;

        fio.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().fio));
        });
        mdk01.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().mdk0101));
        });
        mdk02.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().mdk0102));
        });
        mdk03.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().mdk0103));
        });
        kp.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().kp));
        });
        up.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().up));
        });
        pp.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> cd) -> {
            return new SimpleStringProperty(String.valueOf(cd.getValue().pp));
        });

        ObservableList<Student> students = FXCollections.observableList(studentList);
        viewTable.setItems(students);
    }

    public static Stage editP;

    public static SelectionModel<Student> selectionStudent;

    public void edit(ActionEvent actionEvent) throws IOException {

        try {
            selectionStudent = viewTable.getSelectionModel();
            editP = new Stage();
            editP.initOwner(MainController.resultP);
            editP.setTitle("Редактирование");
            editP.setScene(new Scene(loadFXML("EditP"), 800, 500));
            editP.initModality(Modality.APPLICATION_MODAL);
            editP.showAndWait();
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.errorAlert("Ошибка редактирования", "Ошибка выбора элемента", "Выберите студента которого хотите изменить");
        }
    }
}
