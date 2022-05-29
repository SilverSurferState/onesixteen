package com.example.sixletterapi.model;

import java.util.*;
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

//    public ArrayList<String> findComboBrute(List<String> targets, List<String> partials){
//        ArrayList<String> combo = new ArrayList<>();
//        for(String word : targets){
//            for (String partOne : partials) {
//                for (int j = 1; j < targets.size(); j++) {
//                    String partTwo = partials.get(j);
//                    if ((partOne + partTwo).equals(word)) {
//                        combo.add(partOne + " + " + partTwo + " = " + word);
//                    }
//                }
//            }
//        }
//       return combo;
//    }


    public ArrayList<String> findCombo(String word, HashSet<String> partials){
        Set<String> firstPart = partials.stream().filter(word::startsWith).collect(Collectors.toSet());
        ArrayList<String> combos = new ArrayList<>();
        StringBuilder concatForPrint = new StringBuilder();
        StringBuilder concatForCompare = new StringBuilder();
        findFirstPart(word, partials, firstPart, combos, concatForPrint, concatForCompare);
        return combos;
    }

    private void findFirstPart(String word, HashSet<String> partials, Set<String> firstPart, ArrayList<String> combos, StringBuilder concatForPrint, StringBuilder concatForCompare){
        for(String first : firstPart){
            concatForCompare.append(first);
            concatForPrint.append(first).append(" + ");
            while(concatForCompare.length() <= word.length()){
                if(concatForCompare.toString().equals(word)){
                    combos.add(concatForPrint.toString());
                    concatForCompare.setLength(0);
                    concatForPrint.setLength(0);
                    break;
                }
                String last = word.substring(first.length());
                findSecondPart(word, partials, concatForCompare, concatForPrint, last);
            }
        }
    }

    private void findSecondPart(String word, HashSet<String> partials, StringBuilder concatForCompare, StringBuilder concatForPrint, String last){
        if(partials.contains(last)){
            concatForCompare.append(last);
            if(!word.endsWith(last)) concatForPrint.append(last).append(" + ");
            else concatForPrint.append(last).append(" = ").append(word);
        }
    }

    public String printCombo(){
        StringBuilder printer = new StringBuilder();
        for(String word: collection.keySet()){
            printer.append(word).append("\n");
            printer.append(getCollection().get(word)).append("\n");
            for(String combo : findCombo(word, getCollection().get(word))){
                printer.append(combo).append("\n");
            }
            printer.append("------").append("\n");
        }
        return printer.toString();
    }



}
