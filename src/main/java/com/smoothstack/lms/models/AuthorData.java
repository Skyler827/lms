package com.smoothstack.lms.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;

public class AuthorData implements CsvSerializable{
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String s) {
        firstName = s;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String s) {
        lastName = s;
    }
    @Override
    public String toCsvRow() {
        return firstName+","+lastName;
    }
    @Override
    public void populate(String csvRow) {
        List<String> split = Arrays.asList(csvRow.split(","));
        lastName = split.remove(split.size()-1);
        firstName = split.stream().reduce("", (s1, s2) -> s1+","+s2);
    }

    public AuthorData(String csvRow) {
        this.populate(csvRow);
    }

    @Override
    public Path csvFilePath() {
        return Paths.get("resources", "authors.csv");
    }

    @Override
    public Path nextIdFilePath() {
		return Paths.get("resources","nextId","author.txt");
	}
}