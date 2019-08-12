package com.smoothstack.lms.menus;

import java.io.BufferedReader;

import com.smoothstack.lms.services.LibraryService;

public class Lib2 implements MenuI {

    @Override
    public void call(BufferedReader br) {
        LibraryService ls = LibraryService.getLibraryService();
    }

    @Override
    public MenuI parentMenu(BufferedReader br) {
		return null;
	}

}