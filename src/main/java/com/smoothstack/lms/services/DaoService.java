package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.util.List;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;

public interface DaoService<T extends BaseModel> {
    public void add(BufferedReader br);
    public void add(T data);
    
    public void printAll();
    public List<Dao<T>> list();

    public void findById(BufferedReader br);
    public Dao<T> findById(int id);

    public void search(BufferedReader br);
    public List<Dao<T>> search(String s);
    
    public void update(BufferedReader br);
    public void update(int id, T data);

    public void delete(BufferedReader br);
    public void delete(int id);
}