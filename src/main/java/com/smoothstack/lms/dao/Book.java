package com.smoothstack.lms.dao;

import com.smoothstack.lms.dataclasses.BookData;

public interface Book {

    public int getId();
    
    public BookData getData();
    public void setData(BookData bd);
    
    public String getTitle();
    public void setTitle(String s);

    public Author getAuthor();
    public void setAuthor(Author a);

    public Publisher getPublisher();
    public void setPublisher(Publisher p);

    public int getPublicationYear();
    public void setPublicationYear(int y);

    public String csvRow();
}
