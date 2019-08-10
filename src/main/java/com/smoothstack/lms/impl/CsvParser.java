package com.smoothstack.lms.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<String> parse(String csvRow) throws ParseException {
        ArrayList<String> entries = new ArrayList<String>();
        StringBuilder currentEntry = new StringBuilder();
        char[] csvRowChars = csvRow.toCharArray();
        boolean inAQuote = false;
        for (int i=0; i< csvRowChars.length; i++) {
            if (inAQuote) {
                switch (csvRowChars[i]) {
                case '"':
                    switch (csvRowChars[i+1]) {
                    case '"':
                        currentEntry.append('"');
                        i++;
                        break;
                    case ',':
                        entries.add(currentEntry.toString());
                        currentEntry.delete(0, currentEntry.length());
                        i++;
                        break;
                    default: throw new ParseException(
                        "Invalid csv around position "+String.valueOf(i)+
                        ": Quote within a string is not followed by a Quote or a comma.", i);
                    }
                    break;
                default:
                    currentEntry.append(csvRowChars[i]);
                    break;
                }
            } else {
                switch (csvRowChars[i]) {
                case '"':
                    if (currentEntry.length() == 0) {
                        inAQuote = true;
                        break;
                    } else {
                        throw new ParseException("Invalid csv around position "+i+
                        ": quote char appearing in middle of unquoted sequence.", i);
                    }
                case ',':
                    entries.add(currentEntry.toString());
                    currentEntry.delete(0, currentEntry.length());
                    break;
                default:
                    currentEntry.append(csvRowChars[i]);
                    break;
                }
            }
        }
        if (currentEntry.length() > 0) {
            entries.add(currentEntry.toString());
        }
        return entries;
    }

}