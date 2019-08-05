package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.entities.Author;

public class AuthorImpl implements AuthorDao {
    private static final String AUTHOR_CSV_FILE_PATH = "resources/authors.csv";

    private static ArrayList<AuthorDao> authors;
    private Author a;
    private int id;
    public AuthorImpl() {
        a = new Author();
    }
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
    public static void putAuthor(AuthorDao a) {
        authors = new ArrayList<AuthorDao>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH));
            String row;
            boolean found = false;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if (currAuthor.getId() == a.getId()) {
                    authors.add(a);
                    found = true;
                } else {
                    authors.add(currAuthor);
                }
            }
            if (!found) {
                authors.add(a);
            }
            csvReader.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {}
        try {
            FileWriter csvWriter = new FileWriter(AUTHOR_CSV_FILE_PATH);
            for (AuthorDao ad : authors) {
                csvWriter.write(ad.csv()+"\n");
            }
            csvWriter.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {}
    };
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
    public String csvFilePath() {
        return AUTHOR_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String csv() {
        return id+","+a.getFirstName()+","+a.getLastName();
    }

    @Override
    public String getFirstName() {
        return a.getFirstName();
    }

    @Override
    public void setFirstName(String fName) {
        a.setFirstName(fName);
    }

    @Override
    public String getLastName() {
        return a.getLastName();
    }

    @Override
    public void setLastName(String lName) {
        a.setLastName(lName);
    }
	public static int getNewId() {
		return 0;
	}
}
