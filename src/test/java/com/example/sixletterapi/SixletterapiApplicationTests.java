package com.example.sixletterapi;

import com.example.sixletterapi.helpers.ImportUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SixletterapiApplicationTests {

    File testInput = new File("src/main/resources/testData/input.txt");

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



}
