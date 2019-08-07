package com.smoothstack.lms.models;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;

public class Book implements CsvSerializable {
    private String title;
    private int authorId;
    private int publisherId;
    private int isbn;
    private int publicationYear;

    public String getTitle() {
        return title;
    }
    public void setTitle(String s) {
        title = s;
    }

    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int n) {
        authorId = n;
    }
    public int getPublisherId() {
        return publisherId;
    }
    public void setPublisherId(int n) {
        publisherId = n;
    }
    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int n) {
        isbn = n;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public void setPublicationYear(int y) {
        publicationYear = y;
    }

    @Override
    public String toCsvRow() {
        return null;
    }

    @Override
    public void populate(String csvRow) throws NumberFormatException {
        List<String> data = Arrays.asList(csvRow.split(","));
        title = data.get(0);
        authorId = Integer.parseInt(data.get(1));
        publisherId = Integer.parseInt(data.get(2));
        publicationYear = Integer.parseInt(data.get(3));
    }

}
