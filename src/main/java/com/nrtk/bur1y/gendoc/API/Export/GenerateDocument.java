package com.nrtk.bur1y.gendoc.API.Export;

import com.aspose.words.Document;
import com.nrtk.bur1y.gendoc.API.Import.GetGroups;
import com.nrtk.bur1y.gendoc.Controllers.Alerts;
import com.nrtk.bur1y.gendoc.Data.Commission;
import com.nrtk.bur1y.gendoc.Data.Group;
import com.nrtk.bur1y.gendoc.Data.PM;
import com.nrtk.bur1y.gendoc.Data.Student;
import com.nrtk.bur1y.gendoc.JavaFX;
import javafx.stage.DirectoryChooser;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.InvalidNavigationException;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

public class GenerateDocument {

    public static void generateDocVed(Group sGroup, PM pm, LocalDate localDate, Commission commission, String extension) throws Exception {

        TextDocument textdoc = TextDocument.loadDocument(JavaFX.class.getResource("DataSet/Vedomost.odt").getPath());

        replace("%pm%", textdoc, pm.toString());
        replace("%fullName%", textdoc, pm.name);
        replace("%groupName%", textdoc, sGroup.name);
        replace("%dateExam%", textdoc, localDate.toString());
        replace("%expert1fio%", textdoc, commission.getChairman().fio);
        replace("%expert1%", textdoc, commission.getChairman().toString());
        replace("%expert2%", textdoc, commission.getExpert2().fio);
        replace("%expert3%", textdoc, commission.getExpert3().fio);
        replace("%expert4%", textdoc, commission.getExpert4().fio);
        replace("%expert5%", textdoc, commission.getExpert5().fio);

        Table t = textdoc.getTableByName("Таблица1");
        Row row = t.getRowByIndex(1);

        int i = 1;

        for (Student s : sGroup.studentList) {

            replace("%idRecord%", textdoc, String.valueOf(s.idRecord));
            replace("%fio%", textdoc, s.fio);
            replace("%id%", textdoc, String.valueOf(i));
            replace("%total%", textdoc, getMark(s.total));
            i++;

            Cell c = row.getCellByIndex(0);
            c.setStringValue("%id%");
            c = row.getCellByIndex(1);
            c.setStringValue("%fio%");
            c = row.getCellByIndex(2);
            c.setStringValue("%idRecord%");
            c = row.getCellByIndex(3);
            c.setStringValue("%total%");

            row = t.appendRow();
        }
        t.removeRowsByIndex(i, i + 1);

        try {
            if (extension.equals(".odt")) {
                textdoc.save("toOdt");
            Document doc = new Document("toOdt");
            doc.save(whereSave() + "/" + sGroup.name + " Ведомость от " + localDate + ".odt");
                Alerts.infoAlert("Документ был успешно сохранён", "Завершение операции", "Успешно");
            } else {
                textdoc.save(whereSave() + "/" + sGroup.name + " Ведомость от " + localDate + extension);
                Alerts.infoAlert("Документ был успешно сохранён", "Завершение операции", "Успешно");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.errorAlert("Исходная директория не была выбранна", "Окно было закрыто", "Выберите директорию");
        }
    }

    public static void generateDocPro(Group sGroup, PM pm, LocalDate localDate, Commission commission, String extension) throws Exception {

        TextDocument textdoc = TextDocument.loadDocument(JavaFX.class.getResource("DataSet/Protokol.odt").getPath());

        replace("%pm%", textdoc, pm.toString());
        replace("%specialGroup%", textdoc, pm.name);
        replace("%groupName%", textdoc, sGroup.name);
        replace("%dateExam%", textdoc, localDate.toString());
        replace("%expert1fio%", textdoc, commission.getChairman().fio);
        replace("%expert1%", textdoc, commission.getChairman().toString());
        replace("%expert2%", textdoc, commission.getExpert2().fio);
        replace("%expert3%", textdoc, commission.getExpert3().fio);
        replace("%expert4%", textdoc, commission.getExpert4().fio);
        replace("%expert5%", textdoc, commission.getExpert5().fio);
        replace("%expert5%", textdoc, commission.getExpert5().fio);
        replace("%nameGroup%", textdoc, sGroup.name);

        Table t = textdoc.getTableByName("Таблица1");
        Row row = t.getRowByIndex(3);

        int i = 1;

        for (Student s : sGroup.studentList) {
            replace("%idRecord%", textdoc, String.valueOf(s.idRecord));
            replace("%fio%", textdoc, s.fio);
            replace("%id%", textdoc, String.valueOf(i));
            replace("%total%", textdoc, getMark(s.total));
            replace("%mdk01%", textdoc, getMark(s.mdk0101));
            replace("%mdk02%", textdoc, getMark(s.mdk0102));
            replace("%mdk03%", textdoc, getMark(s.mdk0103));

            replace("%kp%", textdoc, getMark(s.kp));
            replace("%up%", textdoc, getMark(s.up));
            replace("%pp%", textdoc, getMark(s.pp));

            replace("%nameGroup%", textdoc, sGroup.name);

            i++;

            Cell c = row.getCellByIndex(0);
            c.setStringValue("%id%");
            c = row.getCellByIndex(1);
            c.setStringValue("%fio%");
            c = row.getCellByIndex(2);
            c.setStringValue("%mdk01%");
            c = row.getCellByIndex(3);
            c.setStringValue("%mdk02%");
            c = row.getCellByIndex(4);
            c.setStringValue("%mdk03%");
            c = row.getCellByIndex(5);
            c.setStringValue("%kp%");
            c = row.getCellByIndex(6);
            c.setStringValue("%up%");
            c = row.getCellByIndex(7);
            c.setStringValue("%pp%");

            for (int j = 8; j < 17; j++) {
                c = row.getCellByIndex(j);
                c.setStringValue("+");
            }
            c = row.getCellByIndex(17);
            c.setStringValue("%total%");
            c = row.getCellByIndex(18);
            c.setStringValue("%total%");

            row = t.appendRow();

        }
        t.removeRowsByIndex(i + 1, i);

        try {
            if (extension.equals(".odt")) {
                textdoc.save("toOdt");
                Document doc = new Document("toOdt");
                doc.save(whereSave() + "/" + sGroup.name + " Протокол от " + localDate + ".odt");
                Alerts.infoAlert("Документ был успешно сохранён", "Завершение операции", "Успешно");

            } else {
                textdoc.save(whereSave() + "/" + sGroup.name + " Протокол от " + localDate + extension);
                Alerts.infoAlert("Документ был успешно сохранён", "Завершение операции", "Успешно");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alerts.errorAlert("Исходная директория не была выбранна", "Окно было закрыто", "Выберите директорию");
        }
    }

    public static String getMark(Integer mark) {

        return String.valueOf("освоен с оценкой " + mark +
                switch (mark) {

                    case 5 -> " (отлично)";
                    case 4 -> " (хорошо)";
                    case 3 -> " (удовлет)";
                    case 2 -> " (неудовлет)";
                    default -> throw new IllegalStateException("Unexpected value: " + mark);
                });
    }

    public static void replace(String pattern, TextDocument textDocument, String text) {
        TextNavigation search = new TextNavigation(pattern, textDocument);

        while (search.hasNext()) {

            TextSelection item = (TextSelection) search.nextSelection();

            try {
                item.replaceWith(text);
                System.out.println("Замена " + pattern + " на " + text);
            } catch (InvalidNavigationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String whereSave() {

        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle("Select Save Folder");

        String path = null;

        try {
            path = new String(

                    new File(dc
                            .showDialog(JavaFX.s)
                            .getPath())
                            .getAbsolutePath()
                            .getBytes(StandardCharsets.UTF_8));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}
