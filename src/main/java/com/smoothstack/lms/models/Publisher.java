package com.smoothstack.lms.models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.smoothstack.lms.dao.CsvSerializable;
import com.smoothstack.lms.dao.Dao;
import com.smoothstack.lms.repositories.DaoRepository;

public class Publisher implements CsvSerializable {
    private String name;
    private int foundingYear;

    public String getName() {
        return name;
    }
    public void setName(String s) {
        name = s;
    }
    public int getFoundingYear() {
        return foundingYear;
    }
    public void setFoundingYear(int y) {
        foundingYear = y;
    }

    @Override
    public String toCsvRow() {
        return name+","+String.valueOf(foundingYear);
    }

    @Override
    public void populate(String csvRow) {
        List<String> data = Arrays.asList(csvRow.split(","));
        name = data.get(0);
        foundingYear = Integer.parseInt(data.get(1));
    }
    public List<Dao<Book>> getBooks(DaoRepository<Book> repo, int pubisherId) {
        return repo.getAll().stream()
        .filter(b -> b.getData().getPublisherId() == pubisherId)
        .collect(Collectors.toList());
    }
}
