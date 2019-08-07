package com.smoothstack.lms.services;

import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.impl.PublisherImpl;

public class PublisherService {
    public static void list() {
        for (PublisherDao p : PublisherImpl.getAll()) {
            System.out.println(p);
        }
    }
    public static void add() {}
    public static void add(String publisherName) {}
    public static void search() {}
    public static void search(String s) {}
    public static void update() {}
    public static void update(int id, String name) {}
    public static void delete() {}
    public static void delete(int id) {}
}
