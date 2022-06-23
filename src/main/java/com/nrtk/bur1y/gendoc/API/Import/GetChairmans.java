package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Data.Chairman;
import com.nrtk.bur1y.gendoc.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetChairmans {

    public static List<Chairman> getChairmans() {
        List<Chairman> chairmanList = new ArrayList<>();

        XSSFWorkbook workbook = SpreadSheet.book(Properties.pathExperts);

        Iterator<Row> rowIterator = workbook.getSheetAt(1).iterator();

        Row row = rowIterator.next();

        while (rowIterator.hasNext()) {

            row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            chairmanList.add(new Chairman(
                    cellIterator.next().getStringCellValue(),
                    cellIterator.next().getStringCellValue())
            );
        }

        return chairmanList;
    }
}
