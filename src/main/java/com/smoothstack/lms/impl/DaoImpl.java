package com.smoothstack.lms.impl;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;

public class DaoImpl<T extends CsvSerializable> implements Dao<T>, CsvSerializable {

    private int _id;
    private T _data;

    public DaoImpl(int id, T data) {
        this._id = id;
        this._data = data;
    }

    @Override
    public int getId() {
        return _id;
    }

    @Override
    public T getData() {
        return _data;
    }

    @Override
    public void setData(T data) {
        _data = data;
    }

    @Override
    public void save() {
        //call repository
    }

    @Override
    public void delete() {
        // call repository
    }

    @Override
    public String toCsvRow() {
        return String.valueOf(_id)+ ","+_data.toCsvRow();
    }

    @Override
    public void populate(String csvRow) {
        List<String> data = Arrays.asList(csvRow.split(","));
        _id = Integer.parseInt(data.remove(0));
        _data.populate(data.stream().reduce("",(s1,s2) -> s1+","+s2));
    }

    @Override
    public Path csvFilePath() {
        return _data.csvFilePath();
    }

    @Override
    public Path nextIdFilePath() {
        return _data.nextIdFilePath();
	}

}