package com.smoothstack.lms.dao;

import com.smoothstack.lms.dao.Book;
import com.smoothstack.lms.dataclasses.AuthorData;

import java.util.List;

public interface Author {
    public int getId();

    public AuthorData getData();
    public void setData(AuthorData ad);

    public String getFirstName();
    public void setFirstName(String fName);
    public String getLastName();
    public void setLastName(String lName);

    public String csvRow();

    public void save();
    public void delete();

    public List<Book> getBooks();
}