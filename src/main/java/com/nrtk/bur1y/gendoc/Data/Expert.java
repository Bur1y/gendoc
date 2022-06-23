package com.nrtk.bur1y.gendoc.Data;

public class Expert {

    public String fio;

    public Expert(String fio){
        this.fio = fio;
    }

    @Override
    public String toString(){

        return fio;
    }

}