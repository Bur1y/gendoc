package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Data.Student;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GetStudents {
    public static List<Student> getListStudents(Sheet groups) {
        List<Student> studentList = new ArrayList<>();

        Iterator<Row> rowIterator = groups.iterator();

        Row row = rowIterator.next();

        row = rowIterator.next();

        while (rowIterator.hasNext()) {

            row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            studentList.add(new Student(
                    cellIterator.next().getStringCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (double) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue())
            );
        }
        return studentList;
    }

    public static List<Student> getListStudentsCSV(String path) {
        List<Student> studentList = new ArrayList<>();

        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                studentList.add(new Student(
                        employee[0],
                        Integer.parseInt(employee[3]),
                        Integer.parseInt(employee[4]),
                        Integer.parseInt(employee[5]),
                        Integer.parseInt(employee[6]),
                        Integer.parseInt(employee[7]),
                        Integer.parseInt(employee[8]),
                        Double.parseDouble(employee[1]),
                        Integer.parseInt(employee[2]),
                        Integer.parseInt(employee[9]))
                );
               }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return studentList;
    }
}
