package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.entities.Publisher;

public class PublisherImpl implements PublisherDao {
    private static final String PUB_CSV_FILE_PATH = "resources/publishers.csv";

    private Publisher p;
    private int id;

    public PublisherImpl(String CsvRow) {
        String[] data = CsvRow.split(",");
        p = new Publisher();
        id = Integer.parseInt(data[0]);
        p.setName(data[1]);
        p.setFoundingYear(Integer.parseInt(data[2]));
    }
    public static List<PublisherDao> getAll() {
        ArrayList<PublisherDao> publishers =  new ArrayList<PublisherDao>();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PUB_CSV_FILE_PATH));
            String row;
            while ((row = csvReader.readLine()) != null) {
                publishers.add(new PublisherImpl(row));
            }
            csvReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return publishers;
    }
    public static PublisherDao getById(String s) {
        return null;
    };
    public static void putPublisher(PublisherDao p) {};
    public static void deletePublisher(PublisherDao p) {};

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public List<BookDao> getBooks() {
        return null;
    }

    @Override
    public void addBook(BookDao b) {

    }

    @Override
	public void removeBook(BookDao b) {
		
	}

    @Override
    public int getFoundingYear() {
        return 0;
    }

    @Override
    public void setFoundingYear(int y) {

    }
    @Override
    public String toString() {
        return "Publisher "+id+":"+p.getName();
    }
    public String csvFilePath() {
        return PUB_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return id;
    }
}
