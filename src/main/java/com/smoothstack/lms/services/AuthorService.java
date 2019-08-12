package com.smoothstack.lms.services;

import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;

public class AuthorService extends DaoServiceImpl<Author> {
	public AuthorService(DaoRepository<Author> repo) {
		super(repo);
	}
}