package com.smoothstack.lms.dao;

public abstract class Dao<T extends BaseModel> extends BaseModel{
    public abstract int getId();

    
    public abstract T getData();
    public abstract void setData(T data);

    public abstract void save();
    public abstract void delete();

}