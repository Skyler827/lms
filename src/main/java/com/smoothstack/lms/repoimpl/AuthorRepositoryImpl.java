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
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.impl.AuthorImpl;
import com.smoothstack.lms.repositories.AuthorRepository;
import com.smoothstack.lms.repositories.DaoRepo;

public class AuthorRepositoryImpl<AuthorData> implements DaoRepo<AuthorData> {
    public static final String AUTHOR_CSV_FILE_PATH = "resources/authors.csv";
    public static final String NEXT_ID_FILE_PATH = "resources/nextId/author.txt";

    public List<Dao<AuthorData>> getAuthors() {
        ArrayList<Dao<AuthorData>> authors = new ArrayList<Dao<AuthorData>>();
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
    public void writeAuthors(List<Dao<AuthorData>> authors) {
        try (FileWriter csvWriter = new FileWriter(AUTHOR_CSV_FILE_PATH)) {
            for (Dao<AuthorData> ad : authors) {
                csvWriter.write(ad.csvRow()+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
    public List<Dao<AuthorData>> searchByName(String s) {
        ArrayList<Dao<AuthorData>> authors = new ArrayList<Dao<AuthorData>>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH));
            String row;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if ((currAuthor.getData().getFirstName() + currAuthor.getData().getLastName())
                        .contains(s)) {
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
    public void delete(int id) {

    }

    @Override
    public List<Dao<AuthorData>> getAll() {
        return null;
    }

    @Override
    public void writeAll(List<Dao<AuthorData>> data) {

    }

    @Override
    public void create(AuthorData data) {

    }

    @Override
    public void update(int id, AuthorData data) {

	}
}
