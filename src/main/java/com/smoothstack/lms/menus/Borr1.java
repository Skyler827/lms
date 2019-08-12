package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

public class Borr1 implements IMenu {
    int borrowerId;

    public Borr1(int id) {
        this.borrowerId = id;
    }
    @Override
    public void call(BufferedReader br) {
        String[] options = new String[]{
            "1. Check out a book",
            "2. Return a book",
            "3. Quit to previous"
        };
        for (String line : options) {
            System.out.println(line);
        }
        int selection = 0;
        do {
            try {
                selection = Integer.parseInt(br.readLine());
                if (selection < 0 || selection > 3) {
                    selection = 0;
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number from 1 to 3");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (selection == 0);
    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}