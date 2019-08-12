package com.smoothstack.lms.menus;

import java.io.BufferedReader;

import com.smoothstack.lms.dao.BaseModel;

public class AddUpdateDeleteMenu<T extends BaseModel> implements IMenu {

    @Override
    public void call(BufferedReader br) {

    }

    @Override
    public IMenu parentMenu(BufferedReader br) {
		return null;
	}

}