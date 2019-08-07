package com.smoothstack.lms.services;

import java.io.BufferedReader;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.repoimpl.DaoRepositoryImpl;
import com.smoothstack.lms.repositories.DaoRepository;

public class BookService {
    private static DaoRepository<Book> dr;
    public BookService(String csvFilePath, String nextIdFilePath) {
        dr = new DaoRepositoryImpl<Book>(csvFilePath, nextIdFilePath);
    }

	public void list() {
    }

	public void add(BufferedReader bufferedReader) {
	}

	public void update(BufferedReader bufferedReader) {
	}

	public void delete(BufferedReader bufferedReader) {
	}

	public void search(BufferedReader bufferedReader) {
	}

	public void add(String title, int authorId, int publisherId, int publicationYear) {
	}

	public void search(String string) {
	}

	public void update(int id, String name) {
	}

}