package com.example.sixletterapi.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ImportUtil {
    private static final ArrayList<String> words = new ArrayList<>();


    public static ArrayList<String> importer(File filename) throws IOException{
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            while(reader.ready()){
                words.add(reader.readLine());
            }
        }
        return words;
    }

}
