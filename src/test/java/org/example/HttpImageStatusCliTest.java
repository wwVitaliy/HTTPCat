package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;

import static com.github.stefanbirkner.systemlambda.SystemLambda.tapSystemOut;
import static com.github.stefanbirkner.systemlambda.SystemLambda.withTextFromSystemIn;
import static org.example.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

class HttpImageStatusCliTest {
    static HttpImageStatusCli CLI;

    @BeforeAll
    static void beforeAll() {
        CLI = new HttpImageStatusCli();
    }

    @Test
    void testCode100() throws Exception {
        int code = 100;
        String pathForSave = DIRECTORY_FOR_SAVE + code + EXTENSION;
        Path imageToSave = Path.of(pathForSave);

        withTextFromSystemIn(String.valueOf(code))
                .execute(() -> {
                    CLI.askStatus();
                });

        assertTrue(imageToSave.toFile().exists());
        Files.delete(imageToSave);
    }

    @Test
    void testWrongCode1000() throws Exception {
        int code = 1000;

        String text = tapSystemOut(() -> {
            withTextFromSystemIn(String.valueOf(code))
                    .execute(() -> {
                        CLI.askStatus();
                    });
        });

        assertEquals(WRONG_CODE_MESSAGE + code, text.trim());
    }

    @Test
    void testWrongCodeText() throws Exception {
        String code = "someText";

        assertThrows(InputMismatchException.class, ()->{
            withTextFromSystemIn(String.valueOf(code))
                    .execute(() -> {
                        CLI.readCodeFromConsole();
                    });
        });
    }

}