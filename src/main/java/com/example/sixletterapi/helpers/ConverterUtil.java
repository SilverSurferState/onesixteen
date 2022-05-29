package com.example.sixletterapi.helpers;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

//Convert Multipartfile to file for use in import
public class ConverterUtil {


    public static File convert(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream outputStream = new FileOutputStream(convertedFile);
        outputStream.write(file.getBytes());
        outputStream.close();
        return convertedFile;
    }
}
