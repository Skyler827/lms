package com.smoothstack.lms.services;

import java.io.BufferedReader;

import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.models.Publisher;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;

public class PublisherService extends DaoServiceImpl<Publisher>{
    public PublisherService(DaoRepository<Publisher> repo) {
        super(repo);
	}

}