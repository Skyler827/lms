package com.smoothstack.lms.models;

import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.models.Loan;
import com.smoothstack.lms.models.Book;

public class Borrower extends BaseModel {

    public List<Dao<Loan>> getLoans(DaoRepository<Loan> loanRepo, int id) {
        loanRepo.getAll().stream()
        .filter(l -> l.getData().getBorrowerId() == id)
        .collect(Collectors.toList());
        return null;
    }
    public List<Dao<Book>> getBorrowedBooks(
        DaoRepository<Loan> loanRepo, DaoRepository<Book> bookRepo, int id
    ) {
        return bookRepo.getManyById(
            loanRepo.getAll().stream()
            .filter(l -> (l.getData().getBorrowerId() == id))
            .map(l -> l.getData().getBookId())
            .collect(Collectors.toList())
        );
    }
}
