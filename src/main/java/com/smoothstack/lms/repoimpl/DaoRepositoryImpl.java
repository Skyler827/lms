package com.smoothstack.lms.repoimpl;

import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.repositories.DaoRepository;

public class DaoRepositoryImpl<T extends CsvSerializable> implements DaoRepository<T> {

    @Override
    public List<Dao<T>> getAll() {
        return null;
    }

    @Override
    public void writeAll(List<Dao<T>> data) {

    }

    @Override
    public List<Dao<T>> searchByName(String s) {
        return null;
    }

    @Override
    public void create(T data) {

    }

    @Override
    public void update(int id, T data) {

    }

    @Override
	public void delete(int id) {
		
    }
}