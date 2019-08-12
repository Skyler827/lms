package com.smoothstack.lms.impl.csv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.BaseModel;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.impl.csv.CsvDao;
import com.smoothstack.lms.repositories.DaoRepository;

public class CsvRepository<T extends BaseModel> implements DaoRepository<T> {

    private Class<T> type;
    private String _csvFilePath;
    private String _idCounterFilePath;

    public CsvRepository(String csvFilePath, String nextIdFilePath) {
        _csvFilePath = csvFilePath;
        _idCounterFilePath = nextIdFilePath;
    }
    @Override
    public List<Dao<T>> getAll() {
        List<Dao<T>> records = new ArrayList<Dao<T>>();
        List<String> invalidRows = new ArrayList<String>();
        List<String> invalidRowErrorMessages = new ArrayList<String>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                try {
                    records.add(new CsvDao<T>(row, type));
                } catch (ParseException e) {
                    invalidRows.add(row);
                    invalidRowErrorMessages.add(e.getMessage());
                }
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
                try {
                    CsvDao<T> record = new CsvDao<T>(row, type);
                    if (record.getId() == id) return record;
                } catch (ParseException e) {}
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        throw new NoSuchElementException("No element found with id = "+id+".");
    }

    public int getNewId(int n) {
        try {
            // read from file
            FileReader reader = new FileReader(_idCounterFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int nextId;
            try {
                nextId = Integer.parseInt(line);
                reader.close();
            } catch (NumberFormatException e) {
                reader.close();
                // recover the file
                int maxId = 1;
                try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
                    String row;
                    while ((row = csvReader.readLine()) != null) {
                        try {
                            CsvDao<T> obj = new CsvDao<T>(row, type);
                            if (obj.getId() > maxId) maxId = obj.getId();
                        } catch (ParseException e2) {}
                    }
                }
                nextId = maxId+1;
            }
            FileWriter writer = new FileWriter(_idCounterFilePath);
            writer.write(String.valueOf(nextId+n));
            writer.write(System.lineSeparator());
            writer.close();
            return nextId;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public int getNewId() {
        return getNewId(1);
    }

    @Override
    public List<Dao<T>> getManyById(List<Integer> l) {
        Map<Integer, Dao<T>> map = new HashMap<Integer, Dao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                try {
                    CsvDao<T> record = new CsvDao<T>(row, type);
                    map.put(record.getId(), record);
                } catch (ParseException e) {}
            }
            return l.stream().map(id -> map.get(id)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Dao<T>> searchByName(String s) {
        List<Field> fields = Arrays.asList(type.getFields());
        List<Dao<T>> results = new LinkedList<Dao<T>>();
        // if there is field called name...
        if (fields.stream().anyMatch(f -> f.getName()=="name")) {
            Field objName;
            try {
                objName = type.getField("name");
            } catch (SecurityException | NoSuchFieldException e) {return null;}
            // ...match against it against the given string
            try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
                String row;
                while ((row = csvReader.readLine()) != null) {
                    try {
                        CsvDao<T> record = new CsvDao<T>(row, type);
                        if (objName.get(record).toString().contains(s)) {
                            results.add(record);
                        }
                    } catch (ParseException e) {}
                    catch (IllegalAccessException e) {}
                }
                return results;
            } catch (IOException e) {
                System.out.println(e);
                return null;
            }
        } 
        // otherwise, if there is a firstName and a lastName field...
        else if (
            fields.stream().anyMatch(f -> f.getName()=="firstName") && 
            fields.stream().anyMatch(f -> f.getName()=="lastName")
            ) {
                // ...match against firstName+" "+lastName
                Field fname = fields.stream().filter(f->f.getName()=="firstName").findFirst().get();
                Field lname = fields.stream().filter(f->f.getName()=="lastName").findFirst().get();
                try {
                    for (Dao<T> obj : getAll()) {
                        String fullName = fname.get(obj).toString()+" "+lname.get(obj).toString();
                        if (fullName.contains(s)) {
                            results.add(obj);
                        }
                    }
                } catch (IllegalAccessException e) {}
            return results;
        } else {
            return results;
        }
    }

    @Override
    public void create(T data) {
        int id = getNewId(1);
        CsvDao<T> obj = new CsvDao<T>(id, data);
        ArrayList<CsvDao<T>> L = new ArrayList<CsvDao<T>>(1);
        L.add(obj);
        appendCsvData(L);
    }

    @Override
    public void createMany(List<T> dataList) {
        List<CsvDao<T>> newObjects = new LinkedList<CsvDao<T>>();
        Iterator<T> dataIterator = dataList.iterator();
        int nextId = getNewId(dataList.size());
        for (int id = nextId; id < nextId+dataList.size(); id++) {
            newObjects.add(new CsvDao<T>(id, dataIterator.next()));
        }
        appendCsvData(newObjects);
    }

    @Override
    public void update(Dao<T> data){
        List<CsvDao<T>> updatedData = new ArrayList<CsvDao<T>>();
        try {
            updatedData = 
            Files.lines(Paths.get(_csvFilePath)).map(line -> {try {
                CsvDao<T> obj = new CsvDao<T>(line, type);
                if (obj.getId() == data.getId()) {obj.setData(data.getData());}
                return obj;
            } catch (ParseException e) {return null;}
            }).filter(record -> record != null)
            .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        overwriteCsvData(updatedData);
    }

    @Override
    public void updateMany(List<Dao<T>> updatedData) {
        Map<Integer, T> map = new HashMap<Integer, T>();
        for (Dao<T> obj : updatedData) {
            map.put(obj.getId(), obj.getData());
        }
        List<CsvDao<T>> dataToWrite = new LinkedList<CsvDao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                try {
                    CsvDao<T> obj = new CsvDao<T>(row, type);
                    int n = obj.getId();
                    if (map.containsKey(n)) {
                        obj.setData(map.get(n));
                    }
                    dataToWrite.add(obj);
                } catch (ParseException e) {
                    System.out.println("error parsing row: "+row);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        overwriteCsvData(dataToWrite);
    }

    @Override
    public void overwriteAll(List<Dao<T>> updatedData) {
        List<CsvDao<T>> csvUpdatedData = updatedData.stream()
            .map(obj -> new CsvDao<T>(obj.getId(), obj.getData()))
            .collect(Collectors.toList());
        overwriteCsvData(csvUpdatedData);
    }
    private void appendCsvData(List<CsvDao<T>> dataToWrite) {
        writeCsvData(dataToWrite, true);
    }
    private void overwriteCsvData(List<CsvDao<T>> dataToWrite) {
        writeCsvData(dataToWrite, false);
    }
    private void writeCsvData(List<CsvDao<T>> dataToWrite, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(_csvFilePath, append))) {
            for (CsvDao<T> obj : dataToWrite) {
                writer.write(obj.getCsvRow());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        List<CsvDao<T>> dataToWrite = new LinkedList<CsvDao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                CsvDao<T> obj = new CsvDao<T>(row, type);
                if (obj.getId() != id) {
                    dataToWrite.add(obj);
                }
            }
            overwriteCsvData(dataToWrite);
        } catch (ParseException e) {
            e.printStackTrace();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMany(List<Integer> ids) {
        Set<Integer> deletedIds = new HashSet<Integer>();
        for (int id : ids) {
            deletedIds.add(id);
        }
        List<CsvDao<T>> dataToWrite = new LinkedList<CsvDao<T>>();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(_csvFilePath))) {
            String row;
            while ((row = csvReader.readLine()) != null) {
                CsvDao<T> obj = new CsvDao<T>(row, type);
                if (deletedIds.contains(obj.getId())) {
                    dataToWrite.add(obj);
                }
            }
            overwriteCsvData(dataToWrite);
        } catch (ParseException e) {
            e.printStackTrace();            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(_csvFilePath, false))) {
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
