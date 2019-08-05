package com.smoothstack.lms;

import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length == 0) {
            usage();
        } else {
            System.out.println("hello world, here are your args:");
            for (int i=0; i< args.length; i++) {
                System.out.println(args[i]);
            }
        }
    }
    public static void usage() {
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
