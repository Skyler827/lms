package com.smoothstack.lms.dao;

import java.nio.file.Path;

public interface CsvSerializable {
    public String toCsvRow();
    public void populate(String csvRow);
    public Path csvFilePath();
    public Path nextIdFilePath();
}