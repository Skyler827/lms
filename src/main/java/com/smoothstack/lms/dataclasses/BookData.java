package com.smoothstack.lms.dataclasses;

public class BookData {
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
}
