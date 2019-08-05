package com.smoothstack.lms.dao;

import java.util.List;

public interface PublisherDao {

    public String getName();
    public void setName(String s);
    public List<BookDao> getBooks();
    public void addBook(BookDao b);
    public void removeBook(BookDao b);
}