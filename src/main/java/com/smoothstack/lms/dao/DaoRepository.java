package com.smoothstack.lms.dao;

import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;

public interface DaoRepository<T extends BaseModel> {
    public Class<T> getType();
    public List<Dao<T>> getAll();
    public Dao<T> getById(int id);
    public List<Dao<T>> getManyById(List<Integer> l);
    public List<Dao<T>> searchByName(String s);

    public void create(T data);
    public void createMany(List<T> data);

    public void update(int id, T data);
    public void updateMany(List<Integer> ids, List<T> data);
    public void overwriteAll(List<Dao<T>> data);

    public void delete(int id) throws NoSuchElementException;
    public void deleteMany(List<Integer> ids) 
        throws NoSuchElementException;
    public void deleteAll();
}
