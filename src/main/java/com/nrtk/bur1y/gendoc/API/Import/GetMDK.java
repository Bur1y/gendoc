package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Controllers.Alerts;
import com.nrtk.bur1y.gendoc.Data.MDK;
import com.nrtk.bur1y.gendoc.Data.PM;
import com.nrtk.bur1y.gendoc.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetMDK {

    public static List<MDK> getMDK (Integer numberCourse){
        List<MDK> mdks = new ArrayList<>();

        try {
            XSSFWorkbook workbook = SpreadSheet.book(Properties.pathMDK);

            Iterator<Row> rowIterator = workbook.getSheetAt(numberCourse).iterator();

            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {

                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                mdks.add(new MDK(
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue())
                );
            }
        } catch (Exception e){
            e.printStackTrace();
            Alerts.errorAlert("Ошибка чтения файла МДК", "Ошибка чтения файла","Проверьте в настройках путь до файла");
        }

        return mdks;
    }
}
