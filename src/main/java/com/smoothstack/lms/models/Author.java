package com.smoothstack.lms.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.repositories.DaoRepository;

public class Author implements CsvSerializable{
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
    @Override
    public String toCsvRow() {
        return firstName+","+lastName;
    }
    @Override
    public void populate(String csvRow) {
        List<String> split = Arrays.asList(csvRow.split(","));
        lastName = split.remove(split.size()-1);
        firstName = split.stream().reduce("", (s1, s2) -> s1+","+s2);
    }

    public Author(String csvRow) {
        this.populate(csvRow);
    }
    public List<Dao<Book>> getBooks(DaoRepository<Book> repo, int id) {
        return repo.getAll().stream()
        .filter(b -> b.getData().getAuthorId() == id)
        .collect(Collectors.toList());
    }

}
