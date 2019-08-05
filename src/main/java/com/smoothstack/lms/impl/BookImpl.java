package com.smoothstack.lms.impl;

import java.util.List;
import java.util.ArrayList;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.entities.Book;

public class BookImpl implements BookDao {
    private static final String CSV_FILENAME = "resources/books.csv";
    Book b;

    public BookImpl(String CsvRow) {

    }
    public static List<BookDao> getAll() {
        return new ArrayList<BookDao>();
    }
    public static List<BookDao> searchByName(String s) {
        return new ArrayList<BookDao>();
    }
    public static void putBook(BookDao b) {};
    public static void deleteBook(BookDao b) {};

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String s) {

    }

    @Override
    public AuthorDao getAuthor() {
        return null;
    }

    @Override
    public void setAuthor(AuthorDao a) {

    }

    @Override
    public PublisherDao getPublisher() {
        return null;
    }

    @Override
    public void setPublisher(PublisherDao p) {
		
	}

    @Override
    public int getPublicationYear() {
        return b.getPublicationYear();
    }

    @Override
    public void setPublicationYear(int y) {
        b.setPublicationYear(y);
    }

}
