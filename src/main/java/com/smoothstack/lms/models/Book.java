package com.smoothstack.lms.models;

import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.repositories.DaoRepository;

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

    public Dao<Author> getAuthor(DaoRepository<Author> repo) {
        return repo.searchById(authorId);
    }
    public Dao<Publisher> getPublisher(DaoRepository<Publisher> repo) {
        return repo.searchById(publisherId);
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
