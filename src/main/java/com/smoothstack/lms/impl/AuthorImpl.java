package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.entities.Author;

public class AuthorImpl implements AuthorDao {
    private static final String AUTHOR_CSV_FILE_PATH = "resources/authors.csv";

    private Author a;
    private int id;
    AuthorImpl(String CsvRow) {
        String[] data = CsvRow.split(",");
        id = Integer.parseInt(data[0]);
        a = new Author();
        a.setFirstName(data[1]);
        a.setLastName(data[2]);
    }
    public static List<AuthorDao> getAuthors() {
        ArrayList<AuthorDao> authors = new ArrayList<AuthorDao>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH));
            String row;
            while ((row = csvReader.readLine()) != null) {
                authors.add(new AuthorImpl(row));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

        return authors;
    }
    public static List<AuthorDao> searchByName(String s) {
        return new ArrayList<AuthorDao>();
    }
    public static void putAuthor(AuthorDao a) {};
    public static void deleteAuthor(AuthorDao a) {};

    @Override
    public void putBook(BookDao b) {

    }

    @Override
    public void removeBook(BookDao b) {

    }

    @Override
	public List<BookDao> getBooks() {
		return null;
    }
    @Override
    public String toString() {
        return "Author "+id+": "+a.getFirstName()+" "+a.getLastName();
    }

}
