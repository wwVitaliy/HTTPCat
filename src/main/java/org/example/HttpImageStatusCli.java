package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.Utils.CLI_WRONG_CODE_MESSAGE;
import static org.example.Utils.WRONG_CODE_MESSAGE;

public class HttpImageStatusCli {

    void askStatus() {
        int code = 0;
        //Read and check code status from console
        boolean consoleEntryIsOk = false;
        while (!consoleEntryIsOk) {
            try {
                code = readCodeFromConsole();
                consoleEntryIsOk = true;
            } catch (InputMismatchException e) {
                System.out.println(CLI_WRONG_CODE_MESSAGE);
            }
        }

        try {
            var downloader = new HttpStatusImageDownloader();
            downloader.downloadStatusImage(code);
        } catch (Exception e) {
            System.out.println(WRONG_CODE_MESSAGE + code);
        }
    }

    public int readCodeFromConsole() throws InputMismatchException {
        //Read code status from console
        return new Scanner(System.in).nextInt();
    }
}
