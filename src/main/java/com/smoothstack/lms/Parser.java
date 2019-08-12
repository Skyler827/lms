package com.smoothstack.lms;

import java.util.Arrays;
import java.util.List;

import com.smoothstack.lms.App;
import com.smoothstack.lms.Menus;
import com.smoothstack.lms.menus.MainMenu;
import com.smoothstack.lms.menus.IMenu;
import com.smoothstack.lms.models.Author;
import com.smoothstack.lms.models.Book;
import com.smoothstack.lms.models.Publisher;
import com.smoothstack.lms.services.AuthorService;
import com.smoothstack.lms.services.BookService;
import com.smoothstack.lms.services.PublisherService;

public class Parser {
    static void parse(String[] args) {
        if (args.length == 0) {
            IMenu main = new MainMenu();
            main.call(Menus.getBufferedReader());
            return;
        }
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
            case "authors": AuthorService.getAuthorService().list(); break;
            case "books": BookService.getBookService().list(); break;
            case "publishers": PublisherService.getPublisherService().list(); break;
            default: App.help(args);
        }
    }
    private static void add(String[] args) {
        if (args.length == 1) {Menus.add(); return;}
        switch (args[1]) {
        case "author":
            List<String> nameArr;
            switch (args.length) {
            case 2: AuthorService.getAuthorService().add(Menus.getBufferedReader()); break;
            case 3:
                String fullName = args[2];
                nameArr = Arrays.asList(args[2].split(" "));
                if (nameArr.size() == 1) AuthorService.getAuthorService().add(new Author("", fullName));
                else {
                    String lastWord = nameArr.get(nameArr.size()-1);
                    nameArr.remove(nameArr.size()-1);
                    String otherWords = nameArr.stream().reduce(
                        "", (String s1, String s2) -> s1+" "+s2);
                    AuthorService.getAuthorService().add(new Author(otherWords, lastWord));
                }
                break;
            case 4:
                AuthorService.getAuthorService().add(new Author(args[2],args[3]));
                break;
            default:
                nameArr = Arrays.asList(args);
                nameArr.remove(0); //remove "add"
                nameArr.remove(0); //remove "author"
                String lastWord = nameArr.remove(nameArr.size()-1);
                nameArr.remove(nameArr.size()-1);
                String otherWords = nameArr.stream().reduce(
                    "", (String s1, String s2) -> s1+" "+s2);
                AuthorService.getAuthorService().add(new Author(otherWords, lastWord));
            break;
        }
        case "book":
            switch (args.length) {
            case 2:
                BookService.getBookService().add(Menus.getBufferedReader());
                break;
            case 6:
                String title = args[2];
                int authorId = Integer.parseInt(args[3]);
                int publisherId = Integer.parseInt(args[4]);
                int publicationYear = Integer.parseInt(args[5]);
                BookService.getBookService().add(new Book(title, authorId, publisherId, publicationYear));
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
                PublisherService.getPublisherService().add(Menus.getBufferedReader());
                break;
            case 3:
                System.out.println("Enter a name and a founding year to create a publisher.");
                System.out.println("lms add publisher \"The Brooks Company\" 2004");
                break;
            case 4:
                try {
                    PublisherService.getPublisherService().add(
                        new Publisher(args[2], Integer.parseInt(args[3])));
                } catch (NumberFormatException e) {
                    System.out.println("Year must be a valid number");
                }
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
            if (args.length == 2) AuthorService.getAuthorService().search(Menus.getBufferedReader());
            else AuthorService.getAuthorService().search(args[2]);
            break;
        case "books":
            if (args.length == 2) BookService.getBookService().search(Menus.getBufferedReader());
            else BookService.getBookService().search(args[2]);
            break;
        case "publishers":
            if (args.length == 2) PublisherService.getPublisherService().search(Menus.getBufferedReader());
            else PublisherService.getPublisherService().search(args[2]);
            break;
        }
    }
    private static void update(String[] args) {
        if (args.length == 1) {Menus.update(); return;}
        switch (args[1]) {
        case "author":
            switch (args.length) {
            case 2:
                AuthorService.getAuthorService().update(Menus.getBufferedReader());
                break;
            case 5:
                int id = Integer.parseInt(args[2]);
                String firstName = args[3];
                String lastName = args[4];
                Author updatedAuthor = new Author(firstName, lastName);
                AuthorService.getAuthorService().update(id, updatedAuthor);
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
                BookService.getBookService().update(Menus.getBufferedReader());
                break;
            case 6:
                int id = Integer.parseInt(args[1]);
                String name = args[2];
                int authorId = Integer.parseInt(args[3]);
                int publisherId = Integer.parseInt(args[4]);
                int publicationYear = Integer.parseInt(args[5]);
                BookService.getBookService().update(id, new Book(name, authorId, publisherId, publicationYear));
                break;
            default:
                System.out.println(
                    "To update a book via cli you must provide the following arguments:");
                System.out.println("lms update book $ISBN $NAME $PUBLISHER_ID $PUBLICATION_YEAR");
                break;
            }
            break;
        case "publisher":
            switch (args.length) {
            case 2:
                PublisherService.getPublisherService().update(Menus.getBufferedReader());
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
