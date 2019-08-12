package com.smoothstack.lms.repositories;

import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;

public interface DaoRepository<T extends BaseModel> {
    public List<Dao<T>> getAll();
    public Dao<T> getById(int id) throws NoSuchElementException;
    public List<Dao<T>> getManyById(List<Integer> l)
        throws NoSuchElementException;
    public List<Dao<T>> searchByName(String s);

    public void create(T data);
    public void createMany(List<T> data);

    public void update(Dao<T> data);
    public void updateMany(List<Dao<T>> data);
    public void overwriteAll(List<Dao<T>> data);

    public void delete(int id) throws NoSuchElementException;
    public void deleteMany(List<Integer> ids) 
        throws NoSuchElementException;
    public void deleteAll();
}
