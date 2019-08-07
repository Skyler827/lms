package com.smoothstack.lms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.smoothstack.lms.services.AuthorService;
import com.smoothstack.lms.services.BookService;
import com.smoothstack.lms.services.PublisherService;

public class Menus {
    private static BufferedReader _reader;
    public static BufferedReader getBufferedReader() {
        if (_reader == null) {
            synchronized(Menus.class) {
                if (_reader == null) {
                    _reader =  new BufferedReader(new InputStreamReader(System.in));
                }
            }
        }
        return _reader;
    }
    static void mainMenu() {
        mainMenu(-1);
    }
    static void mainMenu(int n) {
        mainMenu(n, -1);
    }
    static void mainMenu(int mainChoice, int subChoice) {
        /**
         * the choice integer represents the menu choices available to a user
         * choice =  0 means exiting the menu
         * choice = -1 means the user has not made a choice and must be prompted
         * choice > 1 means the user does not need to be prompted, and will 
         *            immediately got to the next menu
         */
        int choice = mainChoice;
        System.out.println("Welcome to the Library Management System");
        String[] menu = new String[] {
            "Enter a number to select an operation:/n",
            "1.) List all Authors/Books/Publishers",
            "2.) Search Authors/Books/Publishers by name",
            "3.) Add a new Author/Book/Publisher",
            "4.) Update an existing Author/Book/Publisher",
            "5.) Delete an existing Author/Book/Publisher",
            "0.) Exit"
        };
        do {
            if (choice < 0) {
                for (String s : menu) {System.out.println(s);}
                try {
                    String s = getBufferedReader().readLine();
                    choice = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer from 0 to 5");
                    continue;
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            switch (choice) {
            case 1: list(subChoice); break;
            case 2: search(subChoice); break;
            case 3: add(subChoice); break;
            case 4: update(subChoice); break;
            case 5: delete(subChoice); break;
            case 0: break;
            default:
                System.out.println("Please enter an integer from 0 to 5");
            }
            if (choice != 0) choice = -1;
        } while (choice != 0);
        System.out.println("Goodbye");
    }
    static void list() {
        mainMenu(1, -1);
    }
    static void list(int choice) {
        String[] menu = new String[] {
            "Select one of the following to list (enter a number):",
            "1.) List Authors",
            "2.) List Books",
            "3.) List Publishers",
            "0.) Go back to Main Menu"
        };
        do {
            for (String s : menu) {System.out.println(s);}
            try {
                String s = getBufferedReader().readLine();
                choice = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer from 0 to 3");
                continue;
            } catch (IOException e) {
                System.out.println(e);
            }
            switch (choice) {
            case 0: break;
            case 1: AuthorService.list(); break;
            case 2: BookService.list(); break;
            case 3: PublisherService.list(); break;
            default: System.out.println("Selection out of range\n");
            }
        } while (choice != 0);
    }
    static void search() {
        mainMenu(2, -1);
    }
    static void search(int choice) {
        String[] menu = new String[] {
            "Select one of the following to search (enter a number):",
            "1.) Search Authors",
            "2.) Search Books",
            "3.) Search Publishers",
            "0.) Back to main menu"
        };
        do {
            for (String s : menu) {System.out.println(s);}
            try {
                String s = getBufferedReader().readLine();
                choice = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer from 0 to 3");
                continue;
            } catch (IOException e) {
                System.out.println(e);
            }
            switch (choice) {
            case 0: break;
            case 1: AuthorService.search(); break;
            case 2: BookService.search(); break;
            case 3: PublisherService.search(); break;
            default: System.out.println("Selection out of range");
            }
        } while (choice != 0);
    }
    static void add() {
        mainMenu(3, -1);
    }
    static void add(int choice) {
        String[] menu = new String[] {
            "Select one of the following to create (enter a number):",
            "1.) Create Author",
            "2.) Create Book",
            "3.) Create Publisher",
            "0.) Back to main menu"
        };
        do {
            for (String s : menu) {System.out.println(s);}
            try {
                String s = getBufferedReader().readLine();
                choice = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer from 0 to 3");
                continue;
            } catch (IOException e) {
                System.out.println(e);
            }
            switch (choice) {
            case 0: break;
            case 1: AuthorService.add(getBufferedReader()); break;
            case 2: BookService.add(getBufferedReader()); break;
            case 3: PublisherService.add(getBufferedReader()); break;
            default: System.out.println("Selection out of range.");
            }
        } while (choice != 0);
    }
    static void update() {
        mainMenu(4, -1);
    }
    static void update(int choice) {
        String[] menu = new String[] {
            "Select one of the following to update (enter a number):",
            "1.) Update Author",
            "2.) Update Book",
            "3.) Update Publisher",
            "0.) Back to main menu"
        };
        do {
            for (String s : menu) {System.out.println(s);}
            try {
                String s = getBufferedReader().readLine();
                choice = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer from 0 to 3");
                continue;
            } catch (IOException e) {
                System.out.println(e);
            }
            switch (choice) {
                case 0: break;
                case 1: AuthorService.update();
                case 2: BookService.update();
                case 3: PublisherService.update();
            }
        } while (choice != 0);
    }
    static void delete() {
        mainMenu(5, -1);
    }
    static void delete(int choice) {
        String[] menu = new String[] {
            "Select one of the following to delete (enter a number):",
            "1.) Delete Author",
            "2.) Delete Book",
            "3.) Delete Publisher",
            "0.) Back to main menu"
        };
        do {
            for (String s : menu) {System.out.println(s);}
            try {
                String s = getBufferedReader().readLine();
                choice = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer from 0 to 3");
                continue;
            } catch (IOException e) {
                System.out.println(e);
            }
            switch (choice) {
            case 0: break;
            case 1: AuthorService.delete(); break;
            case 2: BookService.delete(); break;
            case 3: PublisherService.delete(); break;
            default: System.out.println("Selection out of range");
            }
        } while (choice != 0);
    }
}
