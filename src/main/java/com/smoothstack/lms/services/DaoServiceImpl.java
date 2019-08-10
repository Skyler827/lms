package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.repoimpl.CsvDaoRepository;
import com.smoothstack.lms.repositories.DaoRepository;

public class DaoServiceImpl<T extends BaseModel> implements DaoService<T> {
    protected DaoRepository<T> dr;

    public DaoServiceImpl(String csvFilePath, String nextIdFilePath) {
        dr = new CsvDaoRepository<T>(csvFilePath, nextIdFilePath);
	}


    @Override
    public void add(T data) {

    }

    @Override
    public void printAll() {
		for (Dao<T> a : list()) {
			System.out.println(a);
		}
		System.out.println("");
    }

    @Override
    public List<Dao<T>> list() {
        return dr.getAll();
    }

    @Override
    public void findById(BufferedReader br) {

    }

    @Override
    public Dao<T> findById(int id) {
        return null;
    }

    @Override
    public void search(BufferedReader br) {

    }

    @Override
    public List<Dao<T>> search(String s) {
        return null;
    }

    @Override
    public void update(BufferedReader br) {

    }

    @Override
    public void update(int id, T data) {

    }

    @Override
    public void delete(BufferedReader br) {

    }

	@Override
	public void delete(int id) {
		
	}

    @Override
    public void add(BufferedReader br) {
        // loop over string values
        T.getStringFieldNames();
        // loop over integer values
		System.out.println("Enter an author first name");
		// try {
		// 	add(firstName, lastName);
		// } catch (IOException e) {
		// 	System.out.println(e);
		// }
    }

}