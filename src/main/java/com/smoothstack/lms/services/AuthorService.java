package com.smoothstack.lms.services;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.dao.Author;
import com.smoothstack.lms.impl.AuthorImpl;
import com.smoothstack.lms.repoimpl.AuthorRepositoryImpl;
import com.smoothstack.lms.repositories.AuthorRepository;

public class AuthorService {
    private static AuthorRepository _ar;
    public static AuthorRepository getAR() {
        if (_ar == null) {
            synchronized(AuthorService.class) {
                if (_ar == null) {
                    _ar = new AuthorRepositoryImpl();
                }
            }
        }
        return _ar;
    }
    public static void list() {
        for (Author a : getAR().getAuthors()) {
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
    public static void search(BufferedReader r) {
        System.out.println("Enter all or part of an author's first or last name");
        try {
            String query = r.readLine();
            search(query);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void search(String s) {
        AuthorImpl.getAuthors().stream().filter((Author a) -> 
            (a.getFirstName().toLowerCase()+" "+a.getLastName().toLowerCase())
            .contains(s.toLowerCase())
        ).forEach(System.out::println);

    }
    public static void update(BufferedReader r) {}
    public static void update(int id, String firstName, String lastName) {
        
    }
    public static void delete(BufferedReader r) {}
    public static void delete(int id) {}
}
