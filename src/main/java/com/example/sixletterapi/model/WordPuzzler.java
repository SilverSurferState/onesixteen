package com.example.sixletterapi.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class WordPuzzler {
    private final HashMap<String, HashSet<String>> collection = new HashMap<>();
    private ArrayList<String> allWords;

    public WordPuzzler(ArrayList<String> allWords){
        this.allWords = allWords;
    }

    public HashMap<String, HashSet<String>> getCollection() {
        return collection;
    }

    public ArrayList<String> getAllWords() {
        return allWords;
    }

    public List<String> getXLetterWords(ArrayList<String> words, int size) {
        return words.stream().filter(e -> e.length() == size).collect(Collectors.toList());
    }

    public List<String> getPartialWords(ArrayList<String> words, int size){
        return words.stream().filter(e -> e.length() < size).collect(Collectors.toList());
    }

    public void fillHashMap(ArrayList<String> allWords){
        for(String s : getXLetterWords(allWords, 6)){
            collection.put(s, new HashSet<>());
            for(String p : getPartialWords(allWords, 6)){
                if(s.contains(p)){
                    collection.get(s).add(p);
                }
            }
        }
    }



}
