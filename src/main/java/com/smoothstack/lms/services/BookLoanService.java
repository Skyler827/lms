package com.smoothstack.lms.services;

import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.models.Loan;

public class BookLoanService extends DaoServiceImpl<Loan> {

    public BookLoanService(DaoRepository<Loan> repo_) {
        super(repo_);
    }
}
