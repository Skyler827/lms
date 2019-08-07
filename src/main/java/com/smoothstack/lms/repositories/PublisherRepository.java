package com.smoothstack.lms.repositories;

import java.util.List;

import com.smoothstack.lms.dao.Publisher;

public interface PublisherRepository {
    public List<Publisher> getAuthors();
    public List<Publisher> searchByName(String s);
    public void create(String name, int foundingYear);
    public void update(int id, String name, int foundingYear);
    public void delete(int id);

}