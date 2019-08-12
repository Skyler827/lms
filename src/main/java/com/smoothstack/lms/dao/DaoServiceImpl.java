package com.smoothstack.lms.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
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
        printAll();
        Class<T> c = repo.getType();
        System.out.println("Enter the id of the "+c.getSimpleName()+" you wish to update");
        Dao<T> obj = null;
        do {
            try {
                int id = Integer.parseInt(br.readLine());
                obj = repo.getById(id);
                if (obj == null) {
                    System.out.println("Must enter id of valid "+c.getSimpleName()+".");
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } while (obj == null);
        int i=1;
        List<Field> fields;
        try {
            fields = Arrays.asList(c.getDeclaredFields());
        } catch (SecurityException e) {
            e.printStackTrace();
            return;
        }
        fields.sort((f1, f2) -> f1.getName().compareTo(f2.getName()));
        System.out.println("Enter the number of the field you wish to modify:");
        for (Field f : fields) {
            System.out.println(String.valueOf(i)+". "+f.getName());
            i++;
        }
        int option = -1;
        while (option != 0) {
            String input;
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
            if (input == "quit") break;

        }
    }

    @Override
    public void update(int id, T data) {
        repo.update(id, data);
    }

    @Override
    public void delete(BufferedReader br) {
        printAll();
        System.out.println("Enter the id of the "+repo.getType().getSimpleName()+
        " that you wish to delete");
    }

	@Override
	public void delete(int id) {
        repo.delete(id);
	}

    @Override
    public void add(BufferedReader br) {
        Class<T> c = repo.getType();
        System.out.println("Creating new "+c.getSimpleName()+"...");
        Field[] fields = c.getDeclaredFields();
        T newObj;
        try {
            @SuppressWarnings("unchecked")
            Constructor<T> constructor = (Constructor<T>) c.getConstructors()[0];
            newObj = (T)constructor.newInstance();
        } catch (
            InstantiationException | IllegalAccessException | ClassCastException |
            IllegalArgumentException | InvocationTargetException e
        ) {
            e.printStackTrace();
            return;
        }
        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object nextFieldData = null;
                do {
                    System.out.println("Enter the name of this "+c.getSimpleName()+
                    "'s "+f.getName()+":");
                    try {
                        if (f.getType() == String.class) {
                            nextFieldData = br.readLine();
                        }
                        else if (f.getType() == Integer.class) {
                            nextFieldData = Integer.parseInt((String) br.readLine());
                        } else {
                            nextFieldData = f.getType().getConstructor(new Class[]{String.class}).newInstance(br.readLine());
                        }
                    } catch (
                        IOException | InstantiationException | IllegalArgumentException | 
                        InvocationTargetException | NoSuchMethodException | SecurityException |
                        IllegalAccessException e
                    ) {
                        System.out.println("Error, try again:");
                    }
                } while (nextFieldData == null);
                f.getType().cast(nextFieldData);
                f.set(newObj, nextFieldData);
            }
        } catch (IllegalAccessException e) {}
        add(newObj);
    }
}
