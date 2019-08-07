package com.smoothstack.lms.models;

import java.nio.file.Path;

import com.smoothstack.lms.dao.CsvSerializable;

public class PublisherData implements CsvSerializable {
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
        return null;
    }

    @Override
    public void populate(String csvRow) {

    }

    @Override
    public Path csvFilePath() {
        return null;
    }

    @Override
    public Path nextIdFilePath() {
		return null;
	}
}
