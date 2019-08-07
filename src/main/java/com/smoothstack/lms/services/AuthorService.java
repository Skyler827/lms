package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.impl.AuthorImpl;

public class AuthorService {
    public static void list() {
        for (AuthorDao a : AuthorImpl.getAuthors()) {
            System.out.println(a);
        }
    }
    public static void add(BufferedReader r) {
        String fName;
        String lName;
        try {
            System.out.println("Enter Author first name:");
            fName = r.readLine();
            System.out.println("Enter Author Last name:");
            lName = r.readLine();
            add(fName, lName);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void add(String firstName, String lastName) {
        AuthorImpl a = new AuthorImpl();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.save();
    }
    public static void search() {}
    public static void search(String s) {}
    public static void update() {}
    public static void update(int id, String firstName, String lastName) {}
    public static void delete() {}
    public static void delete(int id) {}
}
