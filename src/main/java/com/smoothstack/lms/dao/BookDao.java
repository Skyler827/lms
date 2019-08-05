package com.smoothstack.lms.dao;

public interface BookDao {
    public String getTitle();
    public void setTitle(String s);

    public AuthorDao getAuthor();
    public void setAuthor(AuthorDao a);

    public PublisherDao getPublisher();
    public void setPublisher(PublisherDao p);

    public int getPublicationYear();
    public void setPublicationYear(int y);
}
