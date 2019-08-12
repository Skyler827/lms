package com.smoothstack.lms.services;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Borrower;
import com.smoothstack.lms.models.Loan;

public class BorrowerService extends DaoServiceImpl<Borrower> {
    private static BorrowerService _service;

    private DaoRepository<Book> bookRepo;
    private DaoRepository<Loan> loanRepo;
    public BorrowerService(DaoRepository<Borrower> borrowerRepo, DaoRepository<Book> bookRepo, DaoRepository<Loan> loanRepo) {
        super(borrowerRepo);
        this.bookRepo = bookRepo;
        this.loanRepo = loanRepo;
    }
    public static BorrowerService getBorrowerService() {
        if (_service == null) {
            synchronized (BorrowerService.class) {
                if (_service == null) {
                    _service = new BorrowerService(
                        new CsvRepository<Borrower>(
                            "resources/csvData/borrowers.csv",
                            "resources/csvData/nextId/borrower.txt"),
                        new CsvRepository<Book>(
                            "resources/csvData/books.csv",
                            "resources/csvData/nextId/book.txt"),
                        new CsvRepository<Loan>(
                            "resources/csvData/loans.csv",
                            "resources/csvData/nextId/loan.txt")
                    );
                }
            }
        }
        return _service;
    }
    public List<Dao<Book>> getBooks(Dao<Borrower> b) {
        return loanRepo.getAll().stream()
        .filter(l -> l.getData().getBorrowerId() == b.getId())
        .map(l -> l.getData().getBook(bookRepo))
        .collect(Collectors.toList());
    }
    public List<Dao<Loan>> getLoans(Dao<Borrower> b) {
        return loanRepo.getAll().stream()
        .filter(l -> l.getData().getBorrowerId() == b.getId())
        .collect(Collectors.toList());
    }
}
