package com.smoothstack.lms.services;

import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Publisher;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;

public class PublisherService extends DaoServiceImpl<Publisher>{
    private DaoRepository<Book> _bookRepo;
    private static PublisherService _service;
    public static PublisherService getPublisherService() {
        if (_service == null) {
            synchronized(PublisherService.class) {
                if (_service == null) {
                    _service = new PublisherService(
                        new CsvRepository<Publisher>("resources/csvData/publishers.csv","resources/csvData/nextId/publisher.txt"),
                        new CsvRepository<Book>("resources/csvData/books.csv","resources/csvData/nextId/book.txt")
                    );
                }
            }
        }
		return _service;
	}
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
