package com.smoothstack.lms.services;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.BookSupply;
import com.smoothstack.lms.models.Library;

public class BookSupplyService extends DaoServiceImpl<BookSupply> {
    private DaoRepository<Book> bookRepo;
    private DaoRepository<Library> libraryRepo;
    public BookSupplyService(
        DaoRepository<BookSupply> repo_,
        DaoRepository<Book> bookRepo,
        DaoRepository<Library> libraryRepo
    ) {
        super(repo_);
        this.bookRepo = bookRepo;
        this.libraryRepo = libraryRepo;
    }
    public static BookSupplyService getBookSupplyService() {
        return null;
    }
    public Dao<Book> getBook(Dao<BookSupply> bs) {
        return bookRepo.getById(bs.getData().getBookId());
    }
    public Dao<Library> getLibrary(Dao<BookSupply> supply) {
        return libraryRepo.getById(supply.getData().getLibraryId());
    }

    public List<Dao<BookSupply>> listAllFromLibrary(int libraryId) {
        return repo.getAll().stream().filter(s -> (s.getData().getLibraryId() == libraryId)).collect(Collectors.toList());
	}
}
