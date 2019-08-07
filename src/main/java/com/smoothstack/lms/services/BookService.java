package com.smoothstack.lms.services;

import java.io.BufferedReader;

import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.impl.BookImpl;

public class BookService {
    public static void list() {
        for (BookDao b : BookImpl.getAll()) {
            System.out.println(b);
        }
    }
    public static void search(BufferedReader r) {}
    public static void search(String s) {}
    public static void add(BufferedReader r) {}
    public static void add(String title, int authorId, int publisherId, int publicationYear) {}
    public static void update(BufferedReader r) {}
    public static void update(int isbn, String name) {}
    public static void delete(BufferedReader r) {}
    public static void delete(int isbn) {}
}
