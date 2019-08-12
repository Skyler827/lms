package com.smoothstack.lms.menus;

import java.io.BufferedReader;
import java.io.IOException;

import com.smoothstack.lms.services.BorrowerService;

public class BorrowerPrompt implements IMenu {

    @Override
    public void call(BufferedReader br) {
      System.out.println("Enter your card number:");
      try {
        String cardNumber = br.readLine();
        BorrowerService.getBorrowerService();
      } catch (IOException e) {
        e.printStackTrace();
        return;
      }
    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}