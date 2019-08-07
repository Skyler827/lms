package com.smoothstack.lms.models;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;

public class Publisher implements CsvSerializable {
    private String name;
    private int foundingYear;

    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }
    public int getFoundingYear() {
        return foundingYear;
    }
    public void setFoundingYear(int y) {
        foundingYear = y;
    }

    @Override
    public String toCsvRow() {
        return name+","+String.valueOf(foundingYear);
    }

    @Override
    public void populate(String csvRow) {
        List<String> data = Arrays.asList(csvRow.split(","));
    }

}
