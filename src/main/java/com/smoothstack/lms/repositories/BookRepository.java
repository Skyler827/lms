package com.smoothstack.lms.repositories;

import java.util.List;

import com.smoothstack.lms.dao.Book;

public interface BookRepository {
    public List<Book> getAuthors();
    public List<Book> searchByName(String s);
    public void create(String title, int authorId, int publisherId, int publishYear);
    public void update(int id, String title, int authorId, int publisherId, int publishYear);
    public void delete(int id);
}
