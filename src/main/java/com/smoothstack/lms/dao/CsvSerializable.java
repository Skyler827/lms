package com.smoothstack.lms.dao;

public interface CsvSerializable {
    public String toCsvRow();
    public void populate(String csvRow);
}