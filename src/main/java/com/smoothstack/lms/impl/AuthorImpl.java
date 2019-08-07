package com.smoothstack.lms.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.models.AuthorData;
import com.smoothstack.lms.models.BookData;
import com.smoothstack.lms.repoimpl.AuthorRepositoryImpl;
import static com.smoothstack.lms.repoimpl.AuthorRepositoryImpl.AUTHOR_CSV_FILE_PATH;

public class AuthorImpl implements Dao<AuthorData> {

    private static AuthorRepositoryImpl repo;
    private AuthorData a;
    private int id;
    public AuthorImpl(int id) {
        a = new AuthorData();
        this.id = id;
    }
    public AuthorImpl(String CsvRow) {
        String[] data = CsvRow.split(",");
        id = Integer.parseInt(data[0]);
        a = new AuthorData();
        a.setFirstName(data[1]);
        a.setLastName(data[2]);
    }
    @Override
    public AuthorData getData() {
        return a;
    }
    public void setData(AuthorData ad) {
        a = ad;
    }

	public List<Dao<BookData>> getBooks() {
        // read books
        // for each book record, record it if this is it's author
        return BookImpl.getAll().stream()
        .filter((Book b) -> b.getAuthor().getId()==this.id)
        .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        return "Author "+id+": "+a.getFirstName()+" "+a.getLastName();
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String csvRow() {
        return id+","+a.getFirstName()+","+a.getLastName();
    }

    public String getFirstName() {
        return a.getFirstName();
    }

    public void setFirstName(String fName) {
        a.setFirstName(fName);
    }

    public String getLastName() {
        return a.getLastName();
    }

    public void setLastName(String lName) {
        a.setLastName(lName);
    }


    public void save() {
        List<Author> authors = repo.getAuthors();
        for (Author a : authors) {
            if (a.getId() == this.id) {
                a.setData(this.a);
            }
        }
        repo.writeAuthors(authors);
    }

    @Override
    public void delete() {
        List<Author> authors = new ArrayList<Author>();
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
            for (Author ad : authors) {
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
