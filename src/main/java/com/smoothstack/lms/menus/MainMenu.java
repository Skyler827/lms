package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

public class MainMenu implements MenuI {

    @Override
    public void call(BufferedReader br) {
        String[] prompt = new String[] {
            "Welcome to the GCIT Library Management System. Which category of a user are you",
            "1) Librarian",
            "2) Administrator",
            "3) Borrower",
            "4) Exit"
        };
        for (String s : prompt) {System.out.println(s);}
        int choice = 0;
        while (choice == 0) {
            try {
                choice = Integer.parseInt(br.readLine());
                if (choice > 3 || choice < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 3");
            } catch (IOException e) {
                e.printStackTrace();
			}
        }
        switch (choice) {
            case 1:
            case 2:
            case 3:
            case 4:
                System.out.println("have a nice day");
        }
    }

    @Override
    public MenuI parentMenu(BufferedReader br) {
		return null;
	}

}