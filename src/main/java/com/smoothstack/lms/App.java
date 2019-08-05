package com.smoothstack.lms;

import java.util.List;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.impl.AuthorImpl;
import com.smoothstack.lms.impl.BookImpl;
import com.smoothstack.lms.impl.PublisherImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            usage();
            return;
        }
        switch (args[0]) {
        case "list":
            if (args.length == 1) {usage(); return;}
            switch (args[1]) {
                case "authors": listAuthors(); break;
                case "books": listBooks(); break;
                case "publishers": listPublishers(); break;
                default: usage(); return;
            }
            break;
        case "search":
            System.out.println("not implemented yet");
            break;
        case "add":
            System.out.println("not implemented yet");
            if (args.length == 1) {usage(); return;}
            switch (args[1]) {
                case "author": addAuthor(); break;
                case "book": addBook(); break;
                case "publisher": addPublisher(); break;
            }
            break;
        case "update":
            System.out.println("not implemented yet");
            break;
        case "delete":
            System.out.println("not implemented yet");
            break;
        default:
            usage();
            return;
        }
    }
    
    private static void usage() {
        try {
            List<String> usageData = Files.readAllLines(Paths.get("resources", "usage.txt"));
            for (String line : usageData) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static void listBooks() {
        for (BookDao b : BookImpl.getAll()) {
            System.out.println(b);
        }
    }
    private static void listAuthors() {
        for (AuthorDao a : AuthorImpl.getAuthors()) {
            System.out.println(a);
        }
    }
    private static void listPublishers() {
        for (PublisherDao p : PublisherImpl.getAll()) {
            System.out.println(p);
        }
    }
    private static void addAuthor() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        try {
            System.out.println("Enter Author first name:");
            String fName = reader.readLine();
            System.out.println("Enter Author Last name:");
            String lName = reader.readLine();
            int id = AuthorImpl.getNewId();
            AuthorImpl a = new AuthorImpl();
            a.setFirstName(fName);
            a.setLastName(lName);
            a.setId
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static void addBook() {}
    private static void addPublisher() {}
}
