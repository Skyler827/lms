package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.Author;
import com.smoothstack.lms.dao.Book;
import com.smoothstack.lms.dao.Publisher;
import com.smoothstack.lms.dataclasses.BookData;

public class BookImpl implements Book {
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

    public static List<Book> getAll() {
        List<Book> books = new ArrayList<Book>();
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
    public static List<Book> searchByName(String s) {
        return new ArrayList<Book>();
    }
    public static void putBook(Book b) {};
    public static void deleteBook(Book b) {};

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void setTitle(String s) {

    }

    @Override
    public Author getAuthor() {
        return null;
    }

    @Override
    public void setAuthor(Author a) {

    }

    @Override
    public Publisher getPublisher() {
        return null;
    }

    @Override
    public void setPublisher(Publisher p) {
		
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
}
