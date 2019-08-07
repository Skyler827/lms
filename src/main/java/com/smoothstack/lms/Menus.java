package com.smoothstack.lms;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menus {
    static void mainMenu() {
        int choice = -1;
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
            for (String s : menu) {System.out.println(s);}
            try (Scanner in = new Scanner(System.in)) {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter an integer from 0 to 5");
                continue;
            }
            switch (choice) {
            case 1: list(); break;
            case 2: search(); break;
            case 3: add(); break;
            case 4: update(); break;
            case 5: delete(); break;
            case 0: break;
            default:
                System.out.println("Please enter an integer from 0 to 5");
            }
        } while (choice != 0);
        System.out.println("Goodbye");
    }
    static void list() {}
    static void add() {}
    static void search() {}
    static void update() {}
    static void delete() {}
}
