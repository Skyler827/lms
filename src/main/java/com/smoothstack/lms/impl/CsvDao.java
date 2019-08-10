package com.smoothstack.lms.impl;

import java.io.InvalidObjectException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;

public class CsvDao<T extends BaseModel> extends Dao<T> {

    private int _id;
    private T _data;
    private List<Field> _fields;

    public CsvDao(int id, T data) {
        this._id = id;
        this._data = data;
        this._fields = Arrays.asList(_data.getClass().getFields());
        _fields.sort((Field f1, Field f2) -> f1.getName().compareTo(f2.getName()));
    }
    public CsvDao(String csvRow) throws ParseException {
        List<String> results = CsvParser.parse(csvRow);
        
    }

    public String getCsvRow() {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.valueOf(_id));
            for (Field f : _fields) {
                sb.append(f.get(_data));
            }
            return sb.toString();
        } catch (IllegalAccessException e) {
            // this shouldn't ever happen
            System.err.println(e);
            return "";
        }
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

}