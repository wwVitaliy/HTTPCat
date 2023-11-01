package org.example;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.Utils.*;

public class HttpStatusImageDownloader {

    public void downloadStatusImage(int code) throws Exception {
        //create URL to cat Image
        String catLink = new HttpStatusChecker().getStatusImage(code);
        URL catURL = new URL(catLink);

        //Create image directory
        Files.createDirectories(Path.of(DIRECTORY_FOR_SAVE));

        //download image
        try (InputStream is = catURL.openStream()) {
            String pathForSave = DIRECTORY_FOR_SAVE + code + EXTENSION;
            Path imageToSave = Path.of(pathForSave);

            if (!imageToSave.toFile().exists()) {
                Files.copy(is, imageToSave);
            }else{
                throw new Exception(FILE_ALREADY_EXIST + code);
            }
        }
    }

}
