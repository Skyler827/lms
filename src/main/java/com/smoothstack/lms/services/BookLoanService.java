package com.smoothstack.lms.services;

import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Borrower;
import com.smoothstack.lms.models.Library;
import com.smoothstack.lms.models.Loan;

public class BookLoanService extends DaoServiceImpl<Loan> {
    private static BookLoanService _service;

    private DaoRepository<Book> bookRepo;
    private DaoRepository<Borrower> borrowerRepo;
    private DaoRepository<Library> libraryRepo;

    public static BookLoanService getBookLoanService() {
        if (_service == null) {
            synchronized (BookLoanService.class) {
                if (_service == null) {
                    _service = new BookLoanService(
                        new CsvRepository<Loan>(
                            "resources/csvData/loans.csv","resources/csvData/nextId/loan.txt"),
                        new CsvRepository<Book>(
                            "resources/csvData/books.csv","resources/csvData/nextId/library.txt"),
                        new CsvRepository<Library>(
                            "resources/csvData/libraries.csv",
                            "resources/csvData/nextId/library.txt")
                    );
                }
            }
        }
        return _service;
    }

    public BookLoanService(
        DaoRepository<Loan> repo_, DaoRepository<Book> br,
        DaoRepository<Library> lr
    ) {
        super(repo_);
        bookRepo = br;
        libraryRepo = lr;
    }

}
