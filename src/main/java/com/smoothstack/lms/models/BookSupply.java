package com.smoothstack.lms.models;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class BookSupply extends BaseModel {
    private int bookId;
    private int libraryId;
    private int stock;

    public int getBookId() {
        return bookId;
    }
    public Dao<Book> getBook(DaoRepository<Book> bookRepo) {
        return bookRepo.getById(bookId);
    }
    public int getLibraryId() {
        return libraryId;
    }
    public Dao<Library> getLibrary(DaoRepository<Library> libraryRepo) {
        return libraryRepo.getById(libraryId);
    }
    public int getStock() {
        return stock;
    }
}