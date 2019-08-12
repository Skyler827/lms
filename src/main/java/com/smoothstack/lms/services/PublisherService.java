package com.smoothstack.lms.services;

import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Publisher;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;

public class PublisherService extends DaoServiceImpl<Publisher>{
    private DaoRepository<Book> _bookRepo;

    public PublisherService(DaoRepository<Publisher> repo, DaoRepository<Book> bookRepo) {
        super(repo);
        _bookRepo = bookRepo;
    }
    public List<Dao<Book>> getBooks(Dao<Publisher> p) {
        return _bookRepo.getAll().stream()
        .filter(b -> b.getData().getPublisherId() == p.getId())
        .collect(Collectors.toList());
    }
}
