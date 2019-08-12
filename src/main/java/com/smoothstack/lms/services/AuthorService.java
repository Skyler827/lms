package com.smoothstack.lms.services;

import com.smoothstack.lms.models.Author;

import java.util.List;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;
import com.smoothstack.lms.dao.DaoServiceImpl;
import com.smoothstack.lms.impl.csv.CsvRepository;
import com.smoothstack.lms.models.Book;

public class AuthorService extends DaoServiceImpl<Author> {
	private static AuthorService _service;
	public static AuthorService getAuthorService() {
		if (_service == null) {
			synchronized (AuthorService.class) {
				if (_service == null) {
					_service = new AuthorService(
						new CsvRepository<Author>("", ""),
						new CsvRepository<Book>("","")
					);
				}
			}
		}
		return _service;
	}
	DaoRepository<Book> bookRepo;
	public AuthorService(DaoRepository<Author> repo, DaoRepository<Book>_bookRepo) {
		super(repo);
		bookRepo = _bookRepo;
	}
	public List<Dao<Book>> getBooks(Dao<Author> a) {
		return a.getData().getBooks(bookRepo, a.getId());
	}
}
