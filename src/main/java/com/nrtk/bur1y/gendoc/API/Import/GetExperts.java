package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Data.Expert;
import com.nrtk.bur1y.gendoc.Properties;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetExperts {

    public static List<Expert> getExperts(){
        List<Expert> expertList = new ArrayList<>();

        XSSFWorkbook workbook = SpreadSheet.book(Properties.pathExperts);

        Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();

        Row row = rowIterator.next();

        while (rowIterator.hasNext()) {

            row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            expertList.add(new Expert(
                    cellIterator.next().getStringCellValue())
            );
        }

        return expertList;
    }
}
