package com.smoothstack.lms.impl.mysql;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;

public class MySqlDao<T extends BaseModel> extends Dao<T> {

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public T getData() {
        return null;
    }

    @Override
    public void setData(T data) {

    }

    @Override
    public void save() {

    }

    @Override
    public void delete() {

    }

}
