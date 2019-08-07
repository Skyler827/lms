package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    private static final String NEXT_ID_FILE_PATH = "resources/nextId/author.txt";

    private static ArrayList<AuthorDao> authors;
    private Author a;
    private int id;
    public AuthorImpl() {
        a = new Author();
        id = getNewId();
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
        ArrayList<AuthorDao> authors = new ArrayList<AuthorDao>();
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
    public static String csvFilePath() {
        return AUTHOR_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String csvRow() {
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
	private static synchronized int getNewId() {
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
    private static void writeNextId(int nextId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NEXT_ID_FILE_PATH))) {
            writer.write(new Integer(nextId+1).toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static int recoverNextIdFile() {
        int nextId = 1;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if (currAuthor.id >= nextId) {
                    nextId = currAuthor.id + 1;
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
    public void save() {
        authors = new ArrayList<AuthorDao>();
        boolean noExceptions = true;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH))) {
            String row;
            boolean found = false;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if (currAuthor.getId() == this.getId()) {
                    authors.add(this);
                    found = true;
                    System.out.println("Overwriting Author record...");
                } else {
                    authors.add(currAuthor);
                }
            }
            if (!found) {
                authors.add(this);
                System.out.println("Creating new Author...");
            }
        } catch (FileNotFoundException e) {
            noExceptions = false;
            System.out.println(e);
        } catch (IOException e) {
            noExceptions = false;
            System.out.println(e);
        }
        try (FileWriter csvWriter = new FileWriter(AUTHOR_CSV_FILE_PATH)) {
            for (AuthorDao ad : authors) {
                csvWriter.write(ad.csvRow()+"\n");
            }
        } catch (FileNotFoundException e) {
            noExceptions = false;
            System.out.println(e);
        } catch (IOException e) {
            noExceptions = false;
            System.out.println(e);
        }
        if (noExceptions) {
            System.out.println("New Author Saved succesfully");
        } else {
            System.out.println("Error when saving new author");
        }
    }

    @Override
    public void delete() {
        authors = new ArrayList<AuthorDao>();
        boolean found = false;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(AUTHOR_CSV_FILE_PATH))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                AuthorImpl currAuthor = new AuthorImpl(row);
                if (currAuthor.getId() != this.getId()) {
                    authors.add(currAuthor);
                } else {
                    found = true;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        try (FileWriter csvWriter = new FileWriter(AUTHOR_CSV_FILE_PATH)) {
            for (AuthorDao ad : authors) {
                csvWriter.write(ad.csvRow()+"\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        if (!found) {
            System.out.println("Author was found and removed");
        } else {
            System.out.println("Author was not present, nothing was removed");
        }

    }
}
