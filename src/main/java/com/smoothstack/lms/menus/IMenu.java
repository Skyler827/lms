package com.smoothstack.lms.menus;

import java.io.BufferedReader;

public interface IMenu {
    public void call(BufferedReader br);
    public IMenu parentMenu(BufferedReader br);
}