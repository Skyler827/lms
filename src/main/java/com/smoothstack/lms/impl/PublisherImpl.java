package com.smoothstack.lms.impl;

import java.util.List;
import java.util.ArrayList;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;

public class PublisherImpl implements PublisherDao {

    public static List<PublisherDao> getAll() {
        return new ArrayList<PublisherDao>();
    }
    public static PublisherDao getById(String s) {
        return null;
    };
    public static void putPublisher(PublisherDao p) {};
    public static void deletePublisher(PublisherDao p) {};

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String s) {

    }

    @Override
    public List<BookDao> getBooks() {
        return null;
    }

    @Override
    public void addBook(BookDao b) {

    }

    @Override
	public void removeBook(BookDao b) {
		
	}
    
}