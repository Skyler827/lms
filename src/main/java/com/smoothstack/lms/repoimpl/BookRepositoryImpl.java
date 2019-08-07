package com.smoothstack.lms.repoimpl;

import java.util.List;

import com.smoothstack.lms.dao.Book;
import com.smoothstack.lms.repositories.BookRepository;

public class BookRepositoryImpl implements BookRepository {

    @Override
    public List<Book> getAuthors() {
        return null;
    }

    @Override
    public List<Book> searchByName(String s) {
        return null;
    }

    @Override
    public void create(String title, int authorId, int publisherId, int publishYear) {

    }

    @Override
    public void update(int id, String title, int authorId, int publisherId, int publishYear) {

    }

    @Override
	public void delete(int id) {
		
	}

}