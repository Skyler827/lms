package com.smoothstack.lms.services;


import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Publisher;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;

public class BookService extends DaoServiceImpl<Book>{
    private DaoRepository<Author> _authorRepo;
    private DaoRepository<Publisher> _publisherRepo;
    private static DaoRepository<Book> dr;
    public BookService(DaoRepository<Book> repo, DaoRepository<Author> authorRepo, DaoRepository<Publisher> publisherRepo) {
        super(repo);
        _authorRepo = authorRepo;
        _publisherRepo = publisherRepo;
    }
    public Dao<Author> getAuthor(Dao<Book> b) {
        return b.getData().getAuthor(_authorRepo);
    }

    public Dao<Publisher> getPublisher(Dao<Book> b) {
        return b.getData().getPublisher(_publisherRepo);
    }

}
