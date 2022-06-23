package com.nrtk.bur1y.gendoc;

import com.nrtk.bur1y.gendoc.Controllers.Alerts;

import java.io.*;

public class Properties {
    public static String pathPM;
    public static String pathExperts;
    public static String pathMDK;
    public static String pathGroups;

    public static void loadProper() {
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new FileReader("properties.csv"));
            while ((line = br.readLine()) != null) {
                String[] employee = line.split(splitBy);
                pathExperts = employee[0];
                pathGroups = employee[1];
                pathPM = employee[2];
                pathMDK = employee[3];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateProper(String pathExperts, String pathGroups, String pathMDK, String pathPM){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("properties.csv", false));
            writer.write(pathExperts + ";" + pathGroups + ";" + pathPM + ";" + pathMDK);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
            Alerts.errorAlert("Ошибка записи файла настроек","Ошибка чтения файла","Перезапустите программу или обратитесь к администратору");
        }

    }

}
