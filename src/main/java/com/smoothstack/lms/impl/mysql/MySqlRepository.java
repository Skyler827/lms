package com.smoothstack.lms.impl.mysql;

import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class MySqlRepository<T extends BaseModel> implements DaoRepository<T> {

    @Override
    public Class<T> getType() {
        return null;
    }

    @Override
    public List<Dao<T>> getAll() {
        return null;
    }

    @Override
    public Dao<T> getById(int id) {
        return null;
    }

    @Override
    public List<Dao<T>> getManyById(List<Integer> l) {
        return null;
    }

    @Override
    public List<Dao<T>> searchByName(String s) {
        return null;
    }

    @Override
    public void create(T data) {

    }

    @Override
    public void createMany(List<T> data) {

    }

    @Override
    public void update(int id, T data) {

    }

    @Override
    public void updateMany(List<Integer> ids, List<T> data) {

    }

    @Override
    public void overwriteAll(List<Dao<T>> data) {

    }

    @Override
    public void delete(int id) throws NoSuchElementException {

    }

    @Override
    public void deleteMany(List<Integer> ids) throws NoSuchElementException {

    }

	@Override
	public void deleteAll() {
		
    }
}
