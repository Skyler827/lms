package com.smoothstack.lms;

import java.util.List;

import com.smoothstack.lms.dao.AuthorDao;
import com.smoothstack.lms.dao.BookDao;
import com.smoothstack.lms.dao.PublisherDao;
import com.smoothstack.lms.impl.AuthorImpl;
import com.smoothstack.lms.impl.BookImpl;
import com.smoothstack.lms.impl.PublisherImpl;

import java.io.IOException;
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
            if (args.length == 1) {
                usage();
                return;
            }
            switch (args[1]) {
                case "authors": listAuthors(); break;
                case "books": listBooks(); break;
                case "publishers": listPublishers(); break;
            }
            break;
        }
    }
    public static void usage() {
        try {
            List<String> usageData = Files.readAllLines(Paths.get("resources", "usage.txt"));
            for (String line : usageData) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void listBooks() {
        for (BookDao b : BookImpl.getAll()) {
            System.out.println(b);
        }
    }
    public static void listAuthors() {
        for (AuthorDao a : AuthorImpl.getAuthors()) {
            System.out.println(a);
        }
    }
    public static void listPublishers() {
        for (PublisherDao p : PublisherImpl.getAll()) {
            System.out.println(p);
        }
    }
}
