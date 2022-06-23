package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.Data.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class EditExam {

    public TextField test;
    public TextField fio;
    public TextField task;

    @FXML
    public void initialize() {
        fio.setText(ResultExamController.selectionStudent.getSelectedItem().fio);
        test.setText(String.valueOf(ResultExamController.selectionStudent.getSelectedItem().test));
        task.setText(String.valueOf(ResultExamController.selectionStudent.getSelectedItem().task));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        ResultExamController.editE.close();
    }

    public void save(ActionEvent actionEvent) throws IOException {
        if (!fio.getText().isBlank()
                && !test.getText().isBlank()
                && !task.getText().isBlank()) {
            MainController.sGroup.studentList.set(ResultExamController.selectionStudent.getSelectedIndex(),
                    new Student(
                            fio.getText(),
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).mdk0101,
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).mdk0102,
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).mdk0103,
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).kp,
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).up,
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).pp,
                            Double.parseDouble(test.getText()),
                            Integer.parseInt(task.getText()),
                            MainController.sGroup.studentList.get(ResultExamController.selectionStudent.getSelectedIndex()).idRecord
                    ));
            ResultExamController.editE.close();
        } else {
            Alerts.errorAlert("Поля не должны быть пустые", "Ошибка записи", "Заполните все поля");
        }
    }
}
