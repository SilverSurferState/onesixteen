package com.example.sixletterapi.controllers;

import com.example.sixletterapi.helpers.ConverterUtil;
import com.example.sixletterapi.helpers.ImportUtil;
import com.example.sixletterapi.model.WordPuzzler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class Api {


    @RequestMapping("/api/file")
    public ResponseEntity<String> returnListOfCombinedWords(@RequestBody MultipartFile file) throws IOException {
        WordPuzzler puzzler;
        try {
            File convertedFile = ConverterUtil.convert(file);
            ArrayList<String> words = ImportUtil.importer(convertedFile);
            puzzler = new WordPuzzler(words);
            puzzler.fillHashMap(words);

        } catch (Exception e) {

            return new ResponseEntity<>("File incorrect or not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(puzzler.printCombo(), HttpStatus.OK);
    }

}
