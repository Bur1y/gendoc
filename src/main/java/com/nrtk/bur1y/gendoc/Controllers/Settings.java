package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.JavaFX;
import com.nrtk.bur1y.gendoc.Properties;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Settings {


    public TextField pathExperts;
    public TextField pathGroups;
    public TextField pathPM;
    public TextField pathMDK;


    @FXML
    public void initialize(){
        pathGroups.setText(Properties.pathGroups);
        pathExperts.setText(Properties.pathExperts);
        pathPM.setText(Properties.pathPM);
        pathMDK.setText(Properties.pathMDK);
    }
    public void newPM() {
        pathPM.setText(getPath());
        Properties.updateProper(pathExperts.getText(), pathGroups.getText(), pathMDK.getText(), pathPM.getText());
    }

    public void newGroups() {
        pathGroups.setText(getPath());
        Properties.updateProper(pathExperts.getText(), pathGroups.getText(), pathMDK.getText(), pathPM.getText());
    }

    public void newExperts(ActionEvent actionEvent) {
        pathExperts.setText(getPath());
        Properties.updateProper(pathExperts.getText(), pathGroups.getText(), pathMDK.getText(), pathPM.getText());
    }

    public void newMDK(ActionEvent actionEvent) {
        pathMDK.setText(getPath());
        Properties.updateProper(pathExperts.getText(), pathGroups.getText(), pathMDK.getText(), pathPM.getText());
    }

    public String getPath() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel, Calc", "*.xls", "*.xlsx", "*.ods")
        );


        String path = null;

        try {
            path = new String(

                    new File(fc
                            .showOpenDialog(JavaFX.s)
                            .getPath())
                            .getAbsolutePath()
                            .getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            Alerts.errorAlert("Файл не был выбран", "Окно было закрыто", "Выберите файл");
        }
        return path;
    }

}
