package com.smoothstack.lms.dao;

public interface Dao<T extends CsvSerializable> {
    public int getId();

    public T getData();
    public void setData(T data);

    public void save();
    public void delete();
}