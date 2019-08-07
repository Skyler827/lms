package com.smoothstack.lms.repositories;

import java.util.List;

import com.smoothstack.lms.dao.Author;

public interface AuthorRepository {
    public List<Author> getAuthors();
    public List<Author> searchByName(String s);
    public Author create(String firstName, String lastName);
    public void update(int id, String firstName, String lastName);
    public void delete(int id);
}