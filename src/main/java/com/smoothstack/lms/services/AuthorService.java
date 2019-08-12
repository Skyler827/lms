package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.repositories.DaoRepository;

public class AuthorService extends DaoServiceImpl<Author> {
	public AuthorService(String csvFilePath, String nextIdFilePath) {
		super(csvFilePath, nextIdFilePath);
	}

	@Override
    public void add(BufferedReader br) {
	}

	public void update(BufferedReader br) {
		String firstName = null;
		String lastName = null;
		do {
			try {
				// look up author id
				System.out.println("Enter the ID of the Author you wish to update");
				String line = br.readLine();
				int authorId = Integer.parseInt(line);
				try {
					Dao<Author> a = repo.getById(authorId);
					// if exists, print info and prompt for updated info
					System.out.println("Found author with id "+authorId);
					// then rewrite file
				} catch (NoSuchElementException e) {
					// else, notify user of invalid id and re-prompt

				}
			} catch (IOException e) {
				System.out.println();
			}
		} while (firstName != null && lastName != null);
	}

	public void delete(BufferedReader bufferedReader) {
	}

	public void search(BufferedReader bufferedReader) {
	}

	public void add(String string, String fullName) {
	}

	public void update(int id, String firstName, String lastName) {
	}

	@Override
	public void add(Author data) {

	}

	@Override
	public void findById(BufferedReader br) {

	}

	@Override
	public Dao<Author> findById(int id) {
		return null;
	}

	@Override
	public List<Dao<Author>> search(String s) {
		return null;
	}

	@Override
	public void update(int id, Author data) {

	}

	@Override
	public void delete(int id) {

	}
	

}