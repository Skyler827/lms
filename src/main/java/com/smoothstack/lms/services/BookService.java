package com.smoothstack.lms.services;


import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Publisher;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;

public class BookService extends DaoServiceImpl<Book>{
    private DaoRepository<Author> _authorRepo;
    private DaoRepository<Publisher> _publisherRepo;

    private static BookService _service;
    public static BookService getBookService() {
        if (_service == null) {
            synchronized (BookService.class) {
                if (_service == null) {
                    _service = new BookService(
                        new CsvRepository<Book>("resources/csvData/books.csv", "resources/csvData/nextId/book.txt"),
                        new CsvRepository<Author>("resources/csvData/authors.csv","resource/csvData/nextId/author.txt"),
                        new CsvRepository<Publisher>("resources/csvData/publishers.csv","resources/csvData/nextId/publisher.txt")
                    );
                }
            }
        }
		return _service;
	}

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
