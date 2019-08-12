package com.smoothstack.lms.models;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class Author extends BaseModel{
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String s) {
        firstName = s;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String s) {
        lastName = s;
    }

    public Author() {}
    public Author(String firstName_, String lastName_) {
        firstName = firstName_;
        lastName = lastName_;
    }

    public List<Dao<Book>> getBooks(DaoRepository<Book> repo, int id) {
        return repo.getAll().stream()
        .filter(b -> b.getData().getAuthorId() == id)
                .collect(Collectors.toList());
    }

}
