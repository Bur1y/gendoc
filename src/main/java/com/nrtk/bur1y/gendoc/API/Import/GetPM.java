package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Controllers.Alerts;
import com.nrtk.bur1y.gendoc.Data.PM;
import com.nrtk.bur1y.gendoc.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetPM {

    public static List<PM> getPM() {
        List<PM> pmList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = SpreadSheet.book(Properties.pathPM);

            Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();

            Row row = rowIterator.next();

            while (rowIterator.hasNext()) {

                row = rowIterator.next();

                Iterator<Cell> cellIterator = row.cellIterator();

                pmList.add(new PM(
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue(),
                        cellIterator.next().getStringCellValue())
                );
            }
        } catch (Exception e){

        }

        return pmList;
    }
}
