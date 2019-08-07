package com.smoothstack.lms.repoimpl;

import java.util.List;

import com.smoothstack.lms.dao.Book;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dataclasses.BookData;
import com.smoothstack.lms.repositories.BookRepository;
import com.smoothstack.lms.repositories.DaoRepo;

public class BookRepositoryImpl implements DaoRepo<BookData> {

    @Override
    public List<Dao<BookData>> searchByName(String s) {
        return null;
    }



    @Override
	public void delete(int id) {
		
    }

    @Override
    public List<Dao<BookData>> getAll() {
        return null;
    }

    }

    @Override
    public void create(BookData data) {

    }

    @Override
    public void update(int id, BookData data) {

	}

}