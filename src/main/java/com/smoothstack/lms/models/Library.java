package com.smoothstack.lms.models;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class Library extends BaseModel {
    private String name;
    public String getName() {
        return name;
    }
    List<Dao<Book>> getBooks(DaoRepository<Book> bookRepo, int id) {
        return bookRepo.getAll().stream()
        .filter(b -> b.getData().getLibraryId() == id)
        .collect(Collectors.toList());
    }
}