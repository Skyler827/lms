package com.smoothstack.lms.models;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class Book extends BaseModel {
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
        return repo.getById(authorId);
    }
    public Dao<Publisher> getPublisher(DaoRepository<Publisher> repo) {
        return repo.getById(publisherId);
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
    
}
