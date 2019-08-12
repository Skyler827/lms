package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu implements IMenu {

    @Override
    public void call(BufferedReader br) {
        String[] prompt = new String[] {
            "Welcome to the GCIT Library Management System. Which category of a user are you",
            "1) Librarian",
            "2) Administrator",
            "3) Borrower",
            "4) Exit"
        };
        int choice = 0;
        while (choice != 4) {
            for (String s : prompt) {System.out.println(s);}
            try {
                choice = Integer.parseInt(br.readLine());
                if (choice > 3 || choice < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 3");
            } catch (IOException e) {
                e.printStackTrace();
			}
            switch (choice) {
                case 0: continue;
                case 1:
                    Lib1 lib1Menu = new Lib1();
                    lib1Menu.call(br);
                    break;
                case 2:
                    AdminMenu adminMenu = new AdminMenu();
                    adminMenu.call(br);
                    break;
                case 3:
                    BorrowerPrompt borrowerMenu = new BorrowerPrompt();
                    borrowerMenu.call(br);
                    break;
                case 4:
                    System.out.println("have a nice day");
            }
        }
    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}