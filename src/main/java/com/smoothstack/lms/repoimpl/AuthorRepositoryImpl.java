package com.smoothstack.lms.repoimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.smoothstack.lms.dao.Author;
import com.smoothstack.lms.impl.AuthorImpl;
import com.smoothstack.lms.repositories.AuthorRepository;

public class AuthorRepositoryImpl implements AuthorRepository {
    public static final String AUTHOR_CSV_FILE_PATH = "resources/authors.csv";
    public static final String NEXT_ID_FILE_PATH = "resources/nextId/author.txt";

    public List<Author> getAuthors() {
        ArrayList<Author> authors = new ArrayList<Author>();
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
    public void writeAuthors(List<Author> authors) {
        try (FileWriter csvWriter = new FileWriter(AUTHOR_CSV_FILE_PATH)) {
            for (Author ad : authors) {
                csvWriter.write(ad.csvRow()+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public List<Author> searchByName(String s) {
        ArrayList<Author> authors = new ArrayList<Author>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH));
            String row;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if ((currAuthor.getFirstName() + currAuthor.getLastName()).contains(s)) {
                    authors.add(currAuthor);
                }
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return authors;
    }
	private synchronized int getNewId() {
        int nextId = -1;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(NEXT_ID_FILE_PATH))) {
            String data = fileReader.readLine();
            nextId = Integer.parseInt(data);
            writeNextId(nextId);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return recoverNextIdFile();
        } catch (IOException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            return recoverNextIdFile();
        }
        return nextId;
    }
    private void writeNextId(int nextId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NEXT_ID_FILE_PATH))) {
            writer.write(new Integer(nextId+1).toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private int recoverNextIdFile() {
        int nextId = 1;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if (currAuthor.getId() >= nextId) {
                    nextId = currAuthor.getId() + 1;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        writeNextId(nextId);
        return nextId;
    }

    @Override
    public Author create(String firstName, String lastName) {
        AuthorImpl a = new AuthorImpl(getNewId());
        List<Author> authors = getAuthors();
        authors.add(a);
        writeAuthors(authors);
        return a;
    }

    @Override
    public void update(int id, String firstName, String lastName) {

    }

    @Override
    public void delete(int id) {

    }
}
