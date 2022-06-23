package com.nrtk.bur1y.gendoc.Controllers;

import com.nrtk.bur1y.gendoc.API.Export.GenerateDocument;
import com.nrtk.bur1y.gendoc.API.Import.*;
import com.nrtk.bur1y.gendoc.Data.*;
import com.nrtk.bur1y.gendoc.JavaFX;
import com.nrtk.bur1y.gendoc.Properties;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.nrtk.bur1y.gendoc.JavaFX.loadFXML;

public class MainController {

    public ComboBox<Group> selectGroup;
    public ComboBox<Chairman> chairman;
    public Button result;
    public Button exam;
    public DatePicker dateExam;
    public ComboBox<PM> pm;
    public static Group sGroup = null;
    public ComboBox<Expert> expert2;
    public ComboBox<Expert> expert3;
    public ComboBox<Expert> expert4;
    public ComboBox<Expert> expert5;
    public Button proto;
    public Button vedom;
    public RadioButton doc;
    public RadioButton odt;

    public static Stage resultP;

    public void importList(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel, Calc, CSV", "*.xls", "*.xlsx", "*.ods", "*.csv")
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

        if (path != null) {

            Pattern r = Pattern.compile("[\\.].{3}.?$");
            Matcher m = r.matcher(path);

            while (m.find()) {

                switch (m.group(0)) {
                    case ".xls", ".xlsx" -> {
                        selectGroup.setItems((FXCollections.observableArrayList(GetGroups.getGroupList(path))));
                        selectGroup.setDisable(false);
                    }
                    case ".ods" -> {
                        selectGroup.setItems((FXCollections.observableArrayList(GetGroups.getGroupList(path))));
                        selectGroup.setDisable(false);
                    }
                    case ".csv" -> {
                        System.out.printf("csv");
                        selectGroup.setItems(FXCollections.observableArrayList(GetGroups.getGroupListCSV(path)));
                        selectGroup.setDisable(false);
                    }
                    default ->
                            Alerts.errorAlert("Файл не был импортирован", "Ошибка импорта файла", "Неверный формат файла");
                }
            }
        }
    }

    public void toHelp(ActionEvent actionEvent) throws IOException, URISyntaxException {
        Desktop.getDesktop().browse(new URL("http://bs-store.me/").toURI());
    }

    public void toSettings(ActionEvent actionEvent) throws IOException {
        Stage settings = new Stage();

        settings.initOwner(JavaFX.s);
        settings.setTitle("Настройки");
        settings.setScene(new Scene(loadFXML("Settings"), 800, 500));
        settings.initModality(Modality.APPLICATION_MODAL);
        settings.showAndWait();
        initialize();
    }

    public void setGroup(ActionEvent actionEvent) {
        result.setDisable(false);
        exam.setDisable(false);
        proto.setDisable(true);
        vedom.setDisable(true);
        pm.setDisable(true);
        pm.setItems(null);

        List<PM> pmListFx = null;

        for (PM pms : GetPM.getPM()) {

            Pattern r = Pattern.compile("([а-яА-Я]{2,4})");
            Matcher m = r.matcher(selectGroup.getSelectionModel().getSelectedItem().name);

            while (m.find()) {

                if (m.group(0).equals(pms.groupName)) {
                    pmListFx = new ArrayList<>();
                    pmListFx.add(pms);
                }
            }
        }
        try {
            pm.setItems(FXCollections.observableArrayList(pmListFx));
            pm.setDisable(false);
            sGroup = selectGroup.getValue();
        } catch (Exception e){
            e.printStackTrace();
            Alerts.errorAlert("Программные модули для данной специальности не найдены","Данные отсутствуют","Проверьте коректность пути до файла или данные в файле по ПМ");
        }

    }

    public void selectPM(ActionEvent actionEvent) {
        proto.setDisable(false);
        vedom.setDisable(false);
    }

    public void createProto(ActionEvent actionEvent) throws Exception {
            if (!chairman.getSelectionModel().isEmpty()
                    && !expert2.getSelectionModel().isEmpty()
                    && !expert3.getSelectionModel().isEmpty()
                    && !expert4.getSelectionModel().isEmpty()
                    && !expert5.getSelectionModel().isEmpty()
            ) {
                if (dateExam.getValue() != null) {
                    GenerateDocument.generateDocPro(sGroup, pm.getValue(), dateExam.getValue(),
                            new Commission(chairman.getValue()
                                    , expert2.getValue()
                                    , expert3.getValue()
                                    , expert4.getValue()
                                    , expert5.getValue()
                            )
                            , getEx()
                    );
                } else {
                    Alerts.errorAlert("Выберите дату проведения экзамена", "Ошибка", "Введите дату проведения экзамена");
                }
            } else {
                Alerts.errorAlert("Выберите комиссию", "Ошибка", "Выберите всех экспертов и заместителя");
            }

    }

    public void createVedom(ActionEvent actionEvent) throws Exception {
        if (!chairman.getSelectionModel().isEmpty()
                && !expert2.getSelectionModel().isEmpty()
                && !expert3.getSelectionModel().isEmpty()
                && !expert4.getSelectionModel().isEmpty()
                && !expert5.getSelectionModel().isEmpty()
        ) {
            if (dateExam.getValue() != null) {
                GenerateDocument.generateDocVed(sGroup, pm.getValue(), dateExam.getValue(),
                        new Commission(chairman.getValue()
                                , expert2.getValue()
                                , expert3.getValue()
                                , expert4.getValue()
                                , expert5.getValue()
                        )
                        , getEx()
                );
            } else {
                Alerts.errorAlert("Выберите дату проведения экзамена", "Ошибка", "Введите дату проведения экзамена");
            }
        } else {
            Alerts.errorAlert("Выберите комиссию", "Ошибка", "Выберите всех экспертов и заместителя");
        }
    }

    public void resultPM(ActionEvent actionEvent) throws IOException {
        Stage resultP = new Stage();

        resultP.initOwner(JavaFX.s);
        resultP.setTitle("Просмотр оценок по ПМ");
        resultP.setScene(new Scene(loadFXML("ResultP"), 800, 500));
        resultP.initModality(Modality.APPLICATION_MODAL);
        resultP.showAndWait();
    }
    public void resultExam(ActionEvent actionEvent) throws IOException {
        Stage resultE = new Stage();

        resultE.initOwner(JavaFX.s);
        resultE.setTitle("Просмотр оценок за экзамен");
        resultE.setScene(new Scene(loadFXML("ResultExam"), 800, 500));
        resultE.initModality(Modality.APPLICATION_MODAL);
        resultE.showAndWait();
    }

    @FXML
    public void initialize() {
        try {
            Properties.loadProper();
            chairman.setItems(FXCollections.observableArrayList(GetChairmans.getChairmans()));
            List<Expert> expertList = GetExperts.getExperts();
            expert2.setItems(FXCollections.observableArrayList(expertList));
            expert3.setItems(FXCollections.observableArrayList(expertList));
            expert4.setItems(FXCollections.observableArrayList(expertList));
            expert5.setItems(FXCollections.observableArrayList(expertList));
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.errorAlert("Ошибка чтения файла с комисиией", "Ошибка чтения файла", "Проверьте путь до файла в настройках");
        }
    }

    public void selectOdt(ActionEvent actionEvent) {
        doc.setSelected(false);
        odt.setSelected(true);
    }

    public void selectDoc(ActionEvent actionEvent) {
        odt.setSelected(false);
        doc.setSelected(true);
    }

    public String getEx() {
        if (odt.isSelected()) {
            return ".odt";
        } else {
            return ".doc";
        }
    }
}
