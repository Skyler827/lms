package com.smoothstack.lms.dao;

import java.util.List;

import com.smoothstack.lms.dataclasses.PublisherData;

public interface Publisher {

    public int getId();
    
    public PublisherData getData();
    public void setData(PublisherData pd);
    public String getName();
    public void setName(String s);
    public int getFoundingYear();
    public void setFoundingYear(int y);

    public List<Book> getBooks();
    public void addBook(Book b);
    public void removeBook(Book b);

    public String csvRow();
}