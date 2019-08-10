package com.smoothstack.lms.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.impl.CsvDao;
import com.smoothstack.lms.repositories.DaoRepository;

public class CsvDaoRepository<T extends BaseModel> implements DaoRepository<T> {

    String _csvFilePath;
    String _idCounterFilePath;

    public CsvDaoRepository(String csvFilePath, String nextIdFilePath) {
        _csvFilePath = csvFilePath;
        _idCounterFilePath = nextIdFilePath;
    }
    @Override
    public List<Dao<T>> getAll() {
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                records.add(new CsvDao<T>(row));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return records;
    }

    @Override
    public Dao<T> getById(int id) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                CsvDao<T> record = new CsvDao<T>(row);
                if (record.getId() == id) return record;
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        throw new NoSuchElementException("no element found with id \""+id+"\".");
    }

    @Override
    public List<Dao<T>> getManyById(List<Integer> l) {
        return null;
    }

    @Override
    public List<Dao<T>> searchByName(String s) {
        return null;
    }

    @Override
    public void create(T data) {

    }

    @Override
    public void createMany(List<T> data) {

    }

    @Override
    public void update(Dao<T> data) {

    }

    @Override
    public void updateMany(List<Dao<T>> data) {

    }

    @Override
    public void overwriteAll(List<Dao<T>> data) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteMany(List<Integer> ids) {

    }

    @Override
    public void deleteAll() {

    }


}
