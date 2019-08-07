package com.smoothstack.lms.repoimpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.impl.DaoImpl;
import com.smoothstack.lms.repositories.DaoRepository;

public class DaoRepositoryImpl<T extends CsvSerializable> implements DaoRepository<T> {

    String _csvFilePath;
    String _idCounterFilePath;
    public DaoRepositoryImpl(String csvFilePath, String nextIdFilePath) {
        _csvFilePath = csvFilePath;
        _idCounterFilePath = nextIdFilePath;
    }
    @Override
    public List<Dao<T>> getAll() {
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                records.add(new DaoImpl<T>(row));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return records;
    }

    @Override
    public void writeAll(List<Dao<T>> data) {
        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(_csvFilePath))){
            for (Dao<T> obj : data) {
                csvWriter.write(obj.toCsvRow());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Dao<T>> searchByName(String s) {
        // TODO: finish this method
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                records.add(new DaoImpl<T>(row));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return records;
    }
    @Override
    public Dao<T> searchById(int id) {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                Dao<T> x = new DaoImpl<T>(row);
                if (x.getId() == id) {
                    return x;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        throw new NoSuchElementException();
    }

    @Override
    public void create(T data) {
        List<Dao<T>> records = getAll();
        Dao<T> newObj = new DaoImpl<T>(getNewId(), data);
        records.add(newObj);
        writeAll(records);
    }

    @Override
    public void update(int id, T data) {
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                Dao<T> x = new DaoImpl<T>(row);
                if (x.getId() == id) {
                    x.setData(data);
                }
                records.add(x);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        writeAll(records);
    }

    @Override
	public void delete(int id) {
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                Dao<T> x = new DaoImpl<T>(row);
                if (x.getId() != id) {
                    records.add(x);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        writeAll(records);
    }
    private synchronized int getNewId() {
        int nextId = -1;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(_idCounterFilePath))) {
            String data = fileReader.readLine();
            nextId = Integer.parseInt(data);
            writeNextId(nextId);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return recoverNextIdFile();
        } catch (IOException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            return recoverNextIdFile();
        }
        return nextId;
    }
    private void writeNextId(int nextId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(_idCounterFilePath))) {
            writer.write(new Integer(nextId+1).toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private int recoverNextIdFile() {
        int nextId = 1;
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                Dao<T> currObj = new DaoImpl<T>(row);
                if (currObj.getId() >= nextId) {
                    nextId = currObj.getId() + 1;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        writeNextId(nextId);
        return nextId;
    }
}
