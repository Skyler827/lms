package com.smoothstack.lms.services;

import com.smoothstack.lms.models.Author;

import java.util.List;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.models.Book;

public class AuthorService extends DaoServiceImpl<Author> {
	DaoRepository<Book> bookRepo;
	public AuthorService(DaoRepository<Author> repo, DaoRepository<Book>_bookRepo) {
		super(repo);
		bookRepo = _bookRepo;
	}
	public List<Dao<Book>> getBooks(Dao<Author> a) {
		return a.getData().getBooks(bookRepo, a.getId());
	}
}
