package com.smoothstack.lms.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.dao.DaoRepository;

public class DaoServiceImpl<T extends BaseModel> implements DaoService<T> {
    protected DaoRepository<T> repo;

    public DaoServiceImpl(DaoRepository<T> repo_) {
        repo = repo_;
	}


    @Override
    public void add(T data) {
        repo.create(data);
    }

    @Override
    public void printAll() {
		for (Dao<T> a : list()) {
			System.out.println(a);
		}
		System.out.println("");
    }

    @Override
    public List<Dao<T>> list() {
        return repo.getAll();
    }

    @Override
    public void findById(BufferedReader br) {
        printAll();
        // step 1: print list of items
        // step 2: print "enter an id to select an item"
        System.out.println("Enter an ID to select an item");
        // step 3:
        Dao<T> result = null;
        do {
            try {
                int id = Integer.parseInt(br.readLine());
                result = findById(id);
                if (result == null) {
                    System.out.println("please enter a number corresponding with a listed item");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("please enter a number");
            }
        } while (result == null);
    }

    @Override
    public Dao<T> findById(int id) {
        return repo.getById(id);
    }

    @Override
    public void search(BufferedReader br) {

    }

    @Override
    public List<Dao<T>> search(String s) {
        return null;
    }

    @Override
    public void update(BufferedReader br) {

    }

    @Override
    public void update(int id, T data) {
        repo.update(id, data);
    }

    @Override
    public void delete(BufferedReader br) {

    }

	@Override
	public void delete(int id) {
		
	}

    @Override
    public void add(BufferedReader br) {
        // loop over string values
        // T.getStringFieldNames();
        // loop over integer values
		System.out.println("Enter an author first name");
		// try {
		// 	add(firstName, lastName);
		// } catch (IOException e) {
		// 	System.out.println(e);
		// }
    }

}