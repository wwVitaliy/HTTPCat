package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.example.Utils.DIRECTORY_FOR_SAVE;
import static org.example.Utils.EXTENSION;
import static org.junit.jupiter.api.Assertions.*;

class HttpStatusImageDownloaderTest {

    public static HttpStatusImageDownloader downloader;

    @BeforeAll
    static void beforeAll(){
        downloader = new HttpStatusImageDownloader();
    }

    @Test
    void testImageDownloadingForCode100() throws Exception {
        int code = 100;
        downloader.downloadStatusImage(code);

        String pathForSave = DIRECTORY_FOR_SAVE + code + EXTENSION;
        Path imageToSave = Path.of(pathForSave);

        assertTrue(imageToSave.toFile().exists());

        Files.delete(imageToSave);
    }

    @Test
    void testImageAlreadyExistForCode100() throws Exception{
        int code = 100;
        String pathForSave = DIRECTORY_FOR_SAVE + code + EXTENSION;
        Path imageToSave = Path.of(pathForSave);

        Files.createFile(imageToSave);

        assertThrows(Exception.class, () -> {
            downloader.downloadStatusImage(100);
        });

        Files.delete(imageToSave);
    }

    @Test
    void testImageDownloadingForWrongCode1000() throws Exception{

        assertThrows(Exception.class, () -> {
            downloader.downloadStatusImage(1000);
        });
    }
}