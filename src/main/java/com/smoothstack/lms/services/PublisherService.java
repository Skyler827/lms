package com.smoothstack.lms.services;

import java.io.BufferedReader;

import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.impl.PublisherImpl;

public class PublisherService {
    public static void list() {
        for (PublisherDao p : PublisherImpl.getAll()) {
            System.out.println(p);
        }
    }
    public static void add(BufferedReader bufferedReader) {}
    public static void add(String publisherName) {}
    public static void search(BufferedReader r) {}
    public static void search(String s) {}
    public static void update(BufferedReader r) {}
    public static void update(int id, String name) {}
    public static void delete(BufferedReader r) {}
    public static void delete(int id) {}
}
