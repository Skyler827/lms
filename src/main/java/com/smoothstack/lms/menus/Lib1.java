package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.menus.IMenu;

public class Lib1 implements IMenu {

    @Override
    public void call(BufferedReader br) {
        System.out.println("1. Enter Branch you manage");
        System.out.println("2. Quit to previous");
        System.out.println("");
        int option = 0;
        while (option == 0) {
            try {
                option = Integer.parseInt(br.readLine());
                if (option > 2 || option < 1) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                System.out.println("must be a number, either 1 or 2");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
	public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}