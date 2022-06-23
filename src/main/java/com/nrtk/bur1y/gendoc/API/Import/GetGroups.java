package com.nrtk.bur1y.gendoc.API.Import;

import com.nrtk.bur1y.gendoc.Controllers.Settings;
import com.nrtk.bur1y.gendoc.Data.Group;
import com.nrtk.bur1y.gendoc.Properties;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class GetGroups {
    public static List<Group> getGroupList(String path) {

        List<Group> groupList = new ArrayList<>();

        XSSFWorkbook workbook = SpreadSheet.book(path);

        for (int i = 1; i < workbook.getNumberOfSheets(); i++) {

            groupList.add(
                    new Group(GetStudents.
                            getListStudents(workbook.getSheetAt(i)),
                            workbook.getSheetAt(i).getSheetName(),
                            Integer.parseInt(workbook.getSheetAt(i).getSheetName().split("\\W{2,4}[-]\\d{2}[-]\\d")[0]))
            );
        }
        return groupList;
    }

    public static List<Group> getGroupListCSV(String path) {
        List<Group> groupList = new ArrayList<>();

        String[] name = (path.split("[\\/]"));
        String[] nameGroup = name[name.length - 1].split(".[c][s][v]$");
        String[] course = nameGroup[nameGroup.length - 1].split("\\W{2,4}[-]\\d{2}[-]\\d");

        groupList.add(new Group(GetStudents.getListStudentsCSV(path), nameGroup[0], Integer.parseInt(course[0])));

        return groupList;
    }


}