package com.smoothstack.lms.dao;

import java.util.List;

public interface PublisherDao {

    public int getId();
    public String getName();
    public void setName(String s);
    public int getFoundingYear();
    public void setFoundingYear(int y);

    public List<BookDao> getBooks();
    public void addBook(BookDao b);
    public void removeBook(BookDao b);

    public String csvRow();
}