package com.smoothstack.lms.services;

import java.io.BufferedReader;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;

public class BookService extends DaoServiceImpl<Book>{
    private static DaoRepository<Book> dr;
    public BookService(DaoRepository<Book> repo) {
        super(repo);
    }

}