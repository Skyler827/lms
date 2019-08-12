package com.smoothstack.lms.menus;

import java.io.BufferedReader;

import com.smoothstack.lms.services.LibraryService;

public class Lib2 implements IMenu {

    @Override
    public void call(BufferedReader br) {
        LibraryService ls = LibraryService.getLibraryService();
    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}