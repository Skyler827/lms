package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.repoimpl.DaoRepositoryImpl;
import com.smoothstack.lms.repositories.DaoRepository;

public class AuthorService {
    private static DaoRepository<Author> dr;
    public AuthorService(String csvFilePath, String nextIdFilePath) {
        dr = new DaoRepositoryImpl<Author>(csvFilePath, nextIdFilePath);
    }
	public void list() {
        dr.getAll();
    }

	public void add(BufferedReader bufferedReader) {
		System.out.println("Enter an author first name");
		String firstName;
		String lastName;
		try {
			firstName = bufferedReader.readLine();
			lastName = bufferedReader.readLine();
			add(firstName, lastName);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void update(BufferedReader bufferedReader) {
		System.out.println("Enter the ID of the Author you wish to update");
		try {
			bufferedReader.readLine();
		} catch (IOException e) {
			System.out.println();
		}
	}

	public void delete(BufferedReader bufferedReader) {
	}

	public void search(BufferedReader bufferedReader) {
	}

	public void add(String string, String fullName) {
	}

	public void search(String string) {
	}

	public void update(int id, String firstName, String lastName) {
	}
}