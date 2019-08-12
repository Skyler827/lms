package com.smoothstack.lms;

import java.util.List;

import com.smoothstack.lms.services.AuthorService;
import com.smoothstack.lms.services.BookService;
import com.smoothstack.lms.services.PublisherService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public static AuthorService getAuthorService() {
		return null;
	}

	public static BookService getBookService() {
		return null;
	}

	public static PublisherService getPublisherService() {
		return null;
    }
}
