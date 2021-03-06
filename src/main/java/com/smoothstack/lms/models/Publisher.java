package com.smoothstack.lms.models;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class Publisher extends BaseModel {
    private String name;
    private int foundingYear;

    public Publisher() {}
    public Publisher(String name_, int foundingYear_) {
        name = name_;
        foundingYear = foundingYear_;
    }
    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }
    public int getFoundingYear() {
        return foundingYear;
    }
    public void setFoundingYear(int y) {
        foundingYear = y;
    }

    public List<Dao<Book>> getBooks(DaoRepository<Book> repo, int pubisherId) {
        return repo.getAll().stream()
        .filter(b -> b.getData().getPublisherId() == pubisherId)
        .collect(Collectors.toList());
    }
}
