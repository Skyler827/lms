package com.smoothstack.lms.models;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.smoothstack.lms.dao.CsvSerializable;

public class BookData implements CsvSerializable {
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
    public void populate(String csvRow) {

    }

    @Override
    public Path csvFilePath() {
        return Paths.get("resources", "books.csv");
    }

    @Override
    public Path nextIdFilePath() {
		return Paths.get("resources","nextId","book.txt");
	}
}
