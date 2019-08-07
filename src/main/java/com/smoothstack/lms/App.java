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
            if (args.length == 1) {
                usage();
            }
            switch (args[1]) {
            case "authors":
                if (args.length == 2) searchAuthor();
                else searchAuthor(args[2]);
                break;
            case "books":
                if (args.length == 2) searchBook();
                else searchBook(args[2]);
                break;
            case "publishers":
                if (args.length == 2) searchPublisher();
                else searchPublisher(args[2]);
                break;
            }
            break;
        case "add":
            if (args.length == 1) {usage(); return;}
            switch (args[1]) {
                case "author": addAuthor(); break;
                case "book": addBook(); break;
                case "publisher": addPublisher(); break;
                default:
                    System.out.println("must be one of either \"author\", "+
                    "\"book\", or \"publisher\".");

            }
            break;
        case "update":
            break;
        case "delete":
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
        String fName;
        String lName;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter Author first name:");
            fName = reader.readLine();
            System.out.println("Enter Author Last name:");
            lName = reader.readLine();
            addAuthor(fName, lName);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static void addAuthor(String firstName, String lastName) {
        AuthorImpl a = new AuthorImpl();
        a.setFirstName(firstName);
        a.setLastName(lastName);
        a.save();
    }
    private static void addBook() {}
    private static void addPublisher() {}

    private static void searchBook() {}
    private static void searchAuthor() {}
    private static void searchPublisher() {}

    private static void searchBook(String s) {}
    private static void searchAuthor(String s) {}
    private static void searchPublisher(String s) {}

    private static void updateBook() {}
    private static void updateAuthor() {}
    private static void updatePublisher() {}

    private static void updateBook(BookDao b) {}
    private static void updateAuthor(AuthorDao a) {}
    private static void updatePublisher(PublisherDao p) {}

    private static void deleteBook() {}
    private static void deleteAuthor() {}
    private static void deletePublisher() {}

    private static void deleteBook(int isbn) {}
    private static void deleteAuthor(int id) {}
    private static void deletePublisher(int id) {}
}
