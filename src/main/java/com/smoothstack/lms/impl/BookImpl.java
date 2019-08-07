package com.smoothstack.lms.impl;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.models.AuthorData;
import com.smoothstack.lms.models.BookData;
import com.smoothstack.lms.models.PublisherData;

public class BookImpl implements Dao<BookData> {
    public static final String BOOK_CSV_FILE_PATH = "resources/books.csv";
    BookData b;

    public BookImpl(String csvRow) {
        b = new BookData();
        String[] data = csvRow.split(",");
        b.setIsbn(Integer.parseInt(data[0]));
        b.setTitle(data[1]);
        b.setPublicationYear(Integer.parseInt(data[4]));
    }

    public BookData getData() {
        return b;
    }
    public void setData(BookData bd) {
        b = bd;
    }

    public String getTitle() {
        return null;
    }

    public void setTitle(String s) {

    }

    public Dao<AuthorData> getAuthor() {
        return null;
    }

    public void setAuthor(Dao<AuthorData> a) {

    }

    public Dao<PublisherData> getPublisher() {
        return null;
    }

    public void setPublisher(Dao<PublisherData> p) {
		
	}

    public int getPublicationYear() {
        return b.getPublicationYear();
    }

    public void setPublicationYear(int y) {
        b.setPublicationYear(y);
    }
    @Override
    public String toString() {
        return "Book "+b.getIsbn()+": "+b.getTitle();
    }
    public static String csvFilePath() {
        return BOOK_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return b.getIsbn();
    }

    @Override
    public String csvRow() {
        return null;
    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }
}
