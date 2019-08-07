package com.smoothstack.lms.services;

import java.io.BufferedReader;

import com.smoothstack.lms.models.Publisher;
import com.smoothstack.lms.repoimpl.DaoRepositoryImpl;
import com.smoothstack.lms.repositories.DaoRepository;

public class PublisherService {
    private DaoRepository<Publisher> dr;
    public PublisherService(String csvFilePath, String nextIdFilePath) {
        dr = new DaoRepositoryImpl<Publisher>(csvFilePath, nextIdFilePath);
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

	public void add(String string) {
	}

	public void search(String string) {
	}
}