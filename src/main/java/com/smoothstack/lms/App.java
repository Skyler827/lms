package com.smoothstack.lms;

import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Parser.parse(args);
    }
    
    static void help(String[] args) {
        try {
            List<String> usageData = Files.readAllLines(Paths.get("resources", "usage.txt"));
            for (String line : usageData) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
