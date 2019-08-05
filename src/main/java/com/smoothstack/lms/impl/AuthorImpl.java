package com.smoothstack.lms.impl;

import java.util.List;
import java.util.ArrayList;
import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;

public class AuthorImpl implements AuthorDao {

    public static List<AuthorDao> getAuthors() {
        return new ArrayList<AuthorDao>();
    }
    public static List<AuthorDao> searchByName(String s) {
        return new ArrayList<AuthorDao>();
    }
    public static void putAuthor(AuthorDao a) {};
    public static void deleteAuthor(AuthorDao a) {};

    @Override
    public void putBook(BookDao b) {

    }

    @Override
    public void removeBook(BookDao b) {

    }

    @Override
	public List<BookDao> getBooks() {
		return null;
	}

}