package com.smoothstack.lms;

import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.smoothstack.lms.services.AuthorService;
import com.smoothstack.lms.services.BookService;
import com.smoothstack.lms.services.PublisherService;
/**
 * Hello world!
 * 
 * @param <AuthorService>
 *
 */
public class App {
    public static void main(String[] args) {
        Parser.parse(args);
    }
    
    static void help(String[] args) {
        try {
            List<String> usageData = Files.readAllLines(Paths.get("resources", "usage.txt"));
            for (String line : usageData) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private static AuthorService as;
    public static AuthorService getAuthorService() {
        if (as == null) {
            synchronized(App.class) {
                if (as == null) {
                    as = new AuthorService();
                }
            }
        }
        return as;
    }
    private static BookService bs;
    public static BookService getBookService() {
        if (bs == null) {
            synchronized (App.class) {
                if (bs == null) {
                    bs = new BookService();
                }
            }
        }
        return bs;
    }
    private static PublisherService ps;
    public static PublisherService getPublisherService() {
        if (ps == null) {
            synchronized (App.class) {
                ps = new PublisherService();
            }
        }
        return ps;
    }
}
