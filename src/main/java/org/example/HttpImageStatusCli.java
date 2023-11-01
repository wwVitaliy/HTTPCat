package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.Utils.CLI_WRONG_CODE_MESSAGE;

public class HttpImageStatusCli {

    void askStatus() throws Exception {
        //Read and check code status from console
        try {
            int code = readCodeFromConsole();
            var downloader = new HttpStatusImageDownloader();
            downloader.downloadStatusImage(code);
        } catch (InputMismatchException e) {
            System.out.println(CLI_WRONG_CODE_MESSAGE);
        }
    }

    private int readCodeFromConsole() throws InputMismatchException {
        //Read code status from console
        return new Scanner(System.in).nextInt();
    }
}
