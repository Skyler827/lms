package com.smoothstack.lms.models;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

import com.smoothstack.lms.models.Library;

public class Loan extends BaseModel{
    private int bookId;
    private int borrowerId;
    private int libraryId;

    public int getBookId() {
        return bookId;
    }
    public Dao<Book> getBook(DaoRepository<Book> repo) {
        return repo.getById(bookId);
    }
    public int getBorrowerId() {
        return borrowerId;
    }
    public int getLibraryId() {
        return libraryId;
    }
    public Dao<Library> getLibrary(DaoRepository<Library> libraryRepo) {
        return libraryRepo.getById(libraryId);
    }
}