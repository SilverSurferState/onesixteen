package com.example.sixletterapi;

import com.example.sixletterapi.helpers.ImportUtil;
import com.example.sixletterapi.model.WordPuzzler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SixletterapiApplicationTests {
    File testInput = new File("src/main/resources/testData/input.txt");
    WordPuzzler puzzler = new WordPuzzler(ImportUtil.importer(testInput));

    SixletterapiApplicationTests() throws IOException {
    }

    @Test
    void testIfImportUtilWorks() throws IOException {
        ArrayList<String> importedWords =  ImportUtil.importer(testInput);
        assertFalse(importedWords.isEmpty());
    }

    @Test
    void testIfImportUtilWorksWithData() throws IOException {
        ArrayList<String> importedWords =  ImportUtil.importer(testInput);
        ArrayList<String> comparedWords = new ArrayList<>();
        comparedWords.add("osine");
        comparedWords.add("them");
        comparedWords.add("narro");
        comparedWords.add("es");
        assertTrue(importedWords.containsAll(comparedWords));
    }

    @Test
    void getXLetterWords() {
        ArrayList<String> sixLetterWords = (ArrayList<String>) puzzler.getXLetterWords(puzzler.getAllWords(), 6);
        assertTrue((sixLetterWords.get(1).length() == 6));
        assertTrue((sixLetterWords.get(5).length() == 6));
        assertTrue((sixLetterWords.get(10).length() == 6));
        assertTrue((sixLetterWords.get(sixLetterWords.size()-1).length() == 6));
    }

    @Test
    void getPartialWords() {
        ArrayList<String> partials = (ArrayList<String>) puzzler.getPartialWords(puzzler.getAllWords(), 6);
        assertTrue((partials.get(1).length() < 6));
        assertTrue((partials.get(5).length() < 6));
        assertTrue((partials.get(10).length() < 6));
        assertTrue((partials.get(partials.size()-1).length() < 6));
    }

    @Test
    void fillHashMap(){
        puzzler.fillHashMap(puzzler.getAllWords());
        HashMap<String, HashSet<String>> collectedWords = puzzler.getCollection();
        System.out.println(collectedWords);
        assertTrue(collectedWords.containsKey("leader"));
        assertTrue(collectedWords.get("leader").contains("le"));
    }

//    @Test
//    void getComboBrute() {
//        ArrayList<String> words = (ArrayList<String>) puzzler.getXLetterWords(puzzler.getAllWords(), 6);
//        ArrayList<String> partials = (ArrayList<String>) puzzler.getPartialWords(puzzler.getAllWords(), 6);
//        ArrayList<String> list = puzzler.findComboBrute(words, partials);
//        System.out.println(list);
//    }


      @Test
      void testCombo(){
          puzzler.fillHashMap(puzzler.getAllWords());
          System.out.println(puzzler.printCombo());
      }



}
