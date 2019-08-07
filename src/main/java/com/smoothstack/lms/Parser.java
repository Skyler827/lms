package com.smoothstack.lms;

import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.App;
import com.smoothstack.lms.Menus;

import com.smoothstack.lms.services.AuthorService;
import com.smoothstack.lms.services.BookService;
import com.smoothstack.lms.services.PublisherService;

public class Parser {
    static void parse(String[] args) {
        if (args.length == 0) {Menus.mainMenu(); return;}
        switch (args[0]) {
        case "help":   App.help(args); break;
        case "list":   list(args); break;
        case "search": search(args); break;
        case "add":    add(args); break;
        case "update": update(args); break;
        case "delete": delete(args); break;
        default: App.help(args); break;
        }
    }
    private static void list(String[] args) {
        if (args.length == 1) {Menus.list(); return;}
        switch (args[1]) {
            case "authors": AuthorService.list(); break;
            case "books": BookService.list(); break;
            case "publishers": PublisherService.list(); break;
            default: App.help(args);
        }
    }
    private static void add(String[] args) {
        if (args.length == 1) {Menus.add(); return;}
        switch (args[1]) {
        case "author":
            List<String> nameArr;
            switch (args.length) {
            case 2: AuthorService.add(Menus.getBufferedReader()); break;
            case 3:
                String fullName = args[2];
                nameArr = Arrays.asList(args[2].split(" "));
                if (nameArr.size() == 1) AuthorService.add("", fullName);
                else {
                    String lastWord = nameArr.get(nameArr.size()-1);
                    nameArr.remove(nameArr.size()-1);
                    String otherWords = nameArr.stream().reduce(
                        "", (String s1, String s2) -> s1+" "+s2);
                    AuthorService.add(otherWords, lastWord);
                }
                break;
            case 4:
                AuthorService.add(args[2],args[3]);
                break;
            default:
                nameArr = Arrays.asList(args);
                nameArr.remove(0); //remove "add"
                nameArr.remove(0); //remove "author"
                String lastWord = nameArr.remove(nameArr.size()-1);
                nameArr.remove(nameArr.size()-1);
                String otherWords = nameArr.stream().reduce(
                    "", (String s1, String s2) -> s1+" "+s2);
                AuthorService.add(otherWords, lastWord);
            break;
        }
        case "book":
            switch (args.length) {
            case 2:
                BookService.add(Menus.getBufferedReader());
                break;
            case 6:
                String title = args[2];
                int authorId = Integer.parseInt(args[3]);
                int publisherId = Integer.parseInt(args[4]);
                int publicationYear = Integer.parseInt(args[5]);
                BookService.add(title, authorId, publisherId, publicationYear);
                break;
            default:
                System.out.println("lms add book requires 4 parameters as follows:");
                System.out.println(
                    "lms add book $TITLE $AUTHOR_ID $PUBLISHER_ID $PUBLICATION_YEAR");
            }
            break;
        case "publisher":
            switch (args.length) {
            case 2:
                PublisherService.add(Menus.getBufferedReader());
                break;
            case 3:
                PublisherService.add(args[2]);
                break;
            default:
                List<String> publisherNameArr = Arrays.asList(args);
                publisherNameArr.remove(0);
                publisherNameArr.remove(0);
                publisherNameArr.stream().reduce("", (String s1,String s2) -> s1+" "+s2);
            }
            break;
        default:
            System.out.println("must be one of either \"author\", "+
            "\"book\", or \"publisher\".");
            Menus.add();
        }
    }
    private static void search(String[] args) {
        if (args.length == 1) {Menus.search(); return;}
        switch (args[1]) {
        case "authors":
            if (args.length == 2) AuthorService.search();
            else AuthorService.search(args[2]);
            break;
        case "books":
            if (args.length == 2) BookService.search();
            else BookService.search(args[2]);
            break;
        case "publishers":
            if (args.length == 2) PublisherService.search();
            else PublisherService.search(args[2]);
            break;
        }
    }
    private static void update(String[] args) {
        if (args.length == 1) {Menus.update(); return;}
        switch (args[1]) {
        case "author":
            switch (args.length) {
            case 2:
                AuthorService.update();
                break;
            case 5:
                int id = Integer.parseInt(args[2]);
                String firstName = args[3];
                String lastName = args[4];
                AuthorService.update(id, firstName, lastName);
                break;
            default:
                System.out.println("To update the author, you must provide "+
                "the following arguments:");
                System.out.println(
                    "lms update author $AUTHOR_ID $FIRST_NAME $LAST_NAME");
                break;
            }
            break;
        case "book":
            switch (args.length) {
            case 2:
                BookService.update();
                break;
            case 4:
                int id = Integer.parseInt(args[2]);
                String name = args[3];
                BookService.update(id, name);
                break;
            default:
                System.out.println(
                    "To update a book you must provide the following arguments:");
                System.out.println("lms update book $ISBN $NAME");
                break;
            }
            break;
        case "publisher":
            switch (args.length) {
            case 2:
                PublisherService.update();
                break;
            case 4:
                break;
            default:
                System.out.println("To update a publisher you "+
                    "must provide the following arguments:");
                System.out.println("lms update publisher $PUBLISHER_ID "+
                    "$PUBLISHER_NAME");
                break;
            }
        }
    }
    private static void delete(String[] args) {}

}
