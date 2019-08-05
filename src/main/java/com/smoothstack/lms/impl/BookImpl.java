package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.entities.Book;

public class BookImpl implements BookDao {
    public static final String BOOK_CSV_FILE_PATH = "resources/books.csv";
    Book b;

    public BookImpl(String csvRow) {
        b = new Book();
        String[] data = csvRow.split(",");
        b.setIsbn(Integer.parseInt(data[0]));
        b.setTitle(data[1]);
        b.setPublicationYear(Integer.parseInt(data[4]));
    }
    public static List<BookDao> getAll() {
        List<BookDao> books = new ArrayList<BookDao>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(BOOK_CSV_FILE_PATH));
            String row;
            while ((row = csvReader.readLine()) != null) {
                books.add(new BookImpl(row));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return books;
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
    @Override
    public String toString() {
        return "Book "+b.getIsbn()+": "+b.getTitle();
    }
    public String csvFilePath() {
        return BOOK_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return b.getIsbn();
    }
}
