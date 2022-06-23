package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.Data.Student;
import com.nrtk.bur1y.gendoc.JavaFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class EditP {

    public TextField fio;
    public TextField pp;
    public TextField up;
    public TextField kp;
    public TextField mdk3;
    public TextField mdk2;
    public TextField mdk1;

    @FXML
    public void initialize() {
        fio.setText(ResultPController.selectionStudent.getSelectedItem().fio);
        pp.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().pp));
        up.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().up));
        kp.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().kp));
        mdk1.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().mdk0101));
        mdk2.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().mdk0102));
        mdk3.setText(String.valueOf(ResultPController.selectionStudent.getSelectedItem().mdk0103));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        ResultPController.editP.close();
    }

    public void save(ActionEvent actionEvent) throws IOException {
        if (!fio.getText().isBlank()
                && !mdk1.getText().isBlank()
                && !mdk2.getText().isBlank()
                && !mdk3.getText().isBlank()
                && !kp.getText().isBlank()
                && !pp.getText().isBlank()
                && !up.getText().isBlank()) {
            MainController.sGroup.studentList.set(ResultPController.selectionStudent.getSelectedIndex(),
                    new Student(
                            fio.getText(),
                            Integer.valueOf(mdk1.getText()),
                            Integer.valueOf(mdk2.getText()),
                            Integer.valueOf(mdk3.getText()),
                            Integer.valueOf(kp.getText()),
                            Integer.valueOf(up.getText()),
                            Integer.valueOf(pp.getText()),
                            MainController.sGroup.studentList.get(ResultPController.selectionStudent.getSelectedIndex()).test,
                            MainController.sGroup.studentList.get(ResultPController.selectionStudent.getSelectedIndex()).task,
                            MainController.sGroup.studentList.get(ResultPController.selectionStudent.getSelectedIndex()).idRecord
                            ));
            ResultPController.editP.close();
        } else {
            Alerts.errorAlert("Поля не должны быть пустые", "Ошибка записи", "Заполните все поля");
        }
    }
}
