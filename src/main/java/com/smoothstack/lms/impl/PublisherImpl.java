package com.smoothstack.lms.impl;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.smoothstack.lms.dao.Book;
import com.smoothstack.lms.dao.Publisher;
import com.smoothstack.lms.dataclasses.PublisherData;

public class PublisherImpl implements Publisher {
    private static final String PUB_CSV_FILE_PATH = "resources/publishers.csv";

    private PublisherData p;
    private int id;

    public PublisherImpl(String CsvRow) {
        String[] data = CsvRow.split(",");
        p = new PublisherData();
        id = Integer.parseInt(data[0]);
        p.setName(data[1]);
        p.setFoundingYear(Integer.parseInt(data[2]));
    }
    public PublisherData getData() {
        return p;
    }
    public void setData(PublisherData pd) {
        p = pd;
    }
    public static List<Publisher> getAll() {
        ArrayList<Publisher> publishers =  new ArrayList<Publisher>();
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
    public static Publisher getById(String s) {
        return null;
    };
    public static void putPublisher(Publisher p) {};
    public static void deletePublisher(Publisher p) {};

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public void addBook(Book b) {

    }

    @Override
	public void removeBook(Book b) {
		
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
        return "Publisher "+id+": "+p.getName();
    }
    public static String csvFilePath() {
        return PUB_CSV_FILE_PATH;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String csvRow() {
        return null;
    }
}
