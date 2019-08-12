package com.smoothstack.lms.services;

import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.models.Borrower;

public class BorrowerService extends DaoServiceImpl<Borrower> {

    public BorrowerService(DaoRepository<Borrower> repo_) {
        super(repo_);
	}}