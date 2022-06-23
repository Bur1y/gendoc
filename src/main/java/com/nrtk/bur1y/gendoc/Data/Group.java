package com.nrtk.bur1y.gendoc.Data;

import java.util.List;

public class Group {

    public List<Student> studentList;
    public String name;
    public Integer numberCourse;

    public Group(String name){
        this.name = name;
    }

    public Group(List<Student> studentList, String name, Integer numberCourse) {
        this.studentList = studentList;
        this.name = name;
        this.numberCourse = numberCourse;
    }


    @Override
    public String toString(){

        return name;
    }
}