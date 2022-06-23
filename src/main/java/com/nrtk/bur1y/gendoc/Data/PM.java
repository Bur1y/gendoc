package com.nrtk.bur1y.gendoc.Data;

public class PM {

    public String reduction;
    public String fullName;
    public String groupName;
    public String name;

    public PM(String reduction, String fullName, String groupName, String name){
        this.reduction = reduction;
        this.fullName = fullName;
        this.groupName = groupName;
        this.name = name;
    }

    public String toString(){
        return reduction + " - " + fullName;
    }
}