package com.smoothstack.lms.repositories;

import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;

public interface DaoRepository<T extends CsvSerializable> {
    public List<Dao<T>> getAll();
    public void writeAll(List<Dao<T>> data);
    public Dao<T> searchById(int id);
    public List<Dao<T>> searchByName(String s);
    public void create(T data);
    public void update(int id, T data);
    public void delete(int id);

}
