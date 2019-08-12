package com.smoothstack.lms.menus;

import java.io.BufferedReader;

public interface MenuI {
    public void call(BufferedReader br);
    public MenuI parentMenu(BufferedReader br);
}